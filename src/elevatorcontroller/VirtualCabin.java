package elevatorcontroller;

import bus.Message;
import bus.SoftwareBus;
import common.Direction;
import common.MotionCommand;
import common.SoftwareBusApi;

import java.util.UUID;

public class VirtualCabin{

    private boolean poweredOn=false;
    private final SoftwareBusApi bus;
    private final UUID id;
    private final int elevatorNum;// 1-4

    private int currentFloor = 1;
    private double t = 0;
    private Action nextAction = null;
    private enum Action { STOP, WAIT }
    private Double nextActionAt = null;
    private Integer destinationFloor = null;


    public VirtualCabin(SoftwareBusApi bus, int elevatorNum){
        this.bus = bus;
        id = bus.register();
        this.elevatorNum = elevatorNum;
    }

    private void send(MotionCommand cmd){
        bus.publish(id, new Message(cmd.topic().encode(), elevatorNum, (int)cmd.body()));
    }
    private void travel(Direction dir, int nFloors) {
        if (nextAction != null) {
            // Disallow a new action until the current one has finished.
            return;
        }

        if(dir == Direction.UP){
            send(MotionCommand.START_UP);

        }else if(dir == Direction.DOWN){
            send(MotionCommand.START_DOWN);
        }
        nextAction = Action.STOP;
        nextActionAt = t + nFloors;
    }

    private void travelToFloor(int destFloor) {
        if (destFloor < 1 || 10 < destFloor || destFloor == currentFloor) {
            return;
        }
        destinationFloor = destFloor;
        int nFloors = Math.abs(currentFloor - destFloor);
        Direction dir = (currentFloor - destFloor) > 0 ? Direction.DOWN : Direction.UP;
        travel(dir, nFloors);
    }


}
