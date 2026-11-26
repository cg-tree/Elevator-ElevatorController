package elevatorcontroller;

// Misc rules. Might need to update, keeping it simple for now.
// - Always close doors and confirm closed before moving.
// - Only open the doors when arriving at a service destination.
// - Publish: Arrival, ArrivalBeep, OverweightBuzzer.
// - Finishes processing one command completely - cannot abort a command.
public class ElevatorController {
    private final int DOOR_WAIT_MS = 3 * 1000;
    private final ControlCenterApi controlCenter;
    private final CabinApi cabin;
    private final DoorApi door;

    public ElevatorController(ControlCenterApi controlCenter, CabinApi cabin, DoorApi door) {
        this.controlCenter = controlCenter;
        this.cabin = cabin;
        this.door = door;
    }

    public void run() {
        while (true) {
            switch (controlCenter.receiveCommand().orElse(null)) {
                case null -> {}
                case ElevatorCommand.Service cmd -> handleService(cmd);
                case ElevatorCommand.GoTo cmd -> handleGoTo(cmd);
            }
        }
    }

    private void handleGoTo(ElevatorCommand.GoTo cmd) {
        // Close the door, even if we think it should already be closed.
        closeDoor();
        cabin.goToFloor(cmd.floor());
        waitForArrival();
    }

    private void handleService(ElevatorCommand.Service cmd) {
        handleGoTo(new ElevatorCommand.GoTo(cmd.floor()));
        door.openDoor();
        try {
            Thread.sleep(DOOR_WAIT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        closeDoor();
    }

    private void closeDoor() {
        if (!door.isDoorOpen()) {
            return;
        }
        while (door.isObstructed() || door.isOverloaded()) {}
        door.closeDoor();
        while (door.isDoorOpen()) {}
    }

    private void waitForArrival() {
        while (!arrived()) {}
    }

    private boolean arrived() {
        return cabin.stopped() && cabin.currentLocation() == cabin.destination();
    }
}
