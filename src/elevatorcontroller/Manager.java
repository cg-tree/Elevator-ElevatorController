package elevatorcontroller;

import common.SoftwareBusApi;
import common.SoftwareBusWrapper;

/**
 * Initializes the elevator controller and virtual devices by wiring up the correct references.
 * */
public class Manager {
    /// The number (1-4) of the elevator this Manager is responsible for.
    private int elevatorNumber;

    public Manager(int elevatorNumber) {
        if (elevatorNumber < 1 || elevatorNumber > 4) {
            System.err.println("[Manager] WARNING: elevator controller manager received elevatorNumber = "
            + elevatorNumber + ", which is not in the expected range of 1-4.");
        }
        this.elevatorNumber = elevatorNumber;
        SoftwareBusApi bus = new SoftwareBusWrapper();
        ControlCenterApi controlCenter = new VirtualControlCenter(bus, elevatorNumber);
        CabinApi cabin = new VirtualCabin(bus, elevatorNumber);
        DoorApi door = new VirtualDoors(bus, elevatorNumber);
        ElevatorController elevatorController = new ElevatorController(controlCenter, cabin, door);
        elevatorController.run();
    }

    static void main() {
        new Manager(1);
    }
}
