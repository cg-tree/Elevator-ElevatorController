package elevatorcontroller;

import bus.Message;
import common.BusMessage;
import common.Direction;
import common.SoftwareBusApi;
import common.Topics;
import messages.ArrivalBeepMessage;
import messages.CabinArrivalMessage;
import messages.MotionCommandMessage;
import messages.MotorStoppedMessage;

import java.util.UUID;

public class VirtualCabin implements CabinApi{

    private boolean poweredOn=false;
    private final SoftwareBusApi bus;
    private final UUID id;
    private final int elevatorNum;// 1-4

    private int currentFloor = 1;
    private double t = 0;
    private Action nextAction = null;
    private Direction direction = Direction.DOWN;
    public boolean isStopped = true;
    /**
     * Check if the cabin is stopped.
     * state tracking Do Not Republish
     */
    @Override
    public boolean stopped() {
        updateStopped();
        return isStopped;
    }
    /**
     * pops all stopped messages from the queue and updates isStopped
     * */
    private void updateStopped(){
        MotorStoppedMessage motorStoppedMessage = new MotorStoppedMessage(isStopped);
        Message msg = bus.getMsg(id, Topics.STOPPED, elevatorNum);
        while(msg != null){
            isStopped = ( motorStoppedMessage.fromBody( msg.body() )).isStopped();
        }
    }
    /**
     * Get the floor where the cabin is, according to the midpoint of the cabin.
     */
    @Override
    public int currentLocation() {
        return currentFloor;
    }

    /**
     * Direct the cabin to travel to the indicated floor.
     *
     * @param floor
     */
    @Override
    public void goToFloor(int floor) {
        travelToFloor(floor);
    }

    /**
     * @return The floor number of cabin's current destination. If the cabin has already arrived at its destination,
     * the current floor number is returned.
     */
    @Override
    public int destination() {
        return destinationFloor;
    }

    private enum Action { STOP, WAIT }
    private Double nextActionAt = null;
    private Integer destinationFloor = null;


    public VirtualCabin(SoftwareBusApi bus, int elevatorNum){
        this.bus = bus;
        id = bus.register();
        this.elevatorNum = elevatorNum;
    }

    private void send(MotionCommandMessage cmd){
        // TODO: UNIMPLEMENTED! We need to encode the MotorCommand to a SoftwareBus message.
        bus.publish(id, cmd.toMsg(elevatorNum));
    }
    private void travel(Direction dir, int nFloors) {
        if (nextAction != null) {
            // Disallow a new action until the current one has finished.
            return;
        }
        //DIRECTION STATE is updated HERE!!!
        direction = dir;
        if(dir == Direction.UP){

            send(MotionCommandMessage.START_UP);

        }else if(dir == Direction.DOWN){
            send(MotionCommandMessage.START_DOWN);
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

    /**
     * Tell the Control Center that the elevator cabin arrived at a floor. The cabin may have stopped at
     * <code>floor</code> or it may have passed by without stopping.
     * @param floor The floor number that the cabin arrived at.
     * @param stopped True if the elevator stopped at <code>floor</code>, false if the elevator passed without stopping.
     * @param directionOfTravel The direction (UP or DOWN) that the elevator was traveling in before arrival.
     */
    void notifyArrival(int floor, boolean stopped, Direction directionOfTravel) {

        //Should this be responsible for beeps? might have to move this later
        bus.publish(id, (new ArrivalBeepMessage(floor)).toMsg(elevatorNum));
        //CabinArrival
        bus.publish(id, (new CabinArrivalMessage(floor,stopped,directionOfTravel)).toMsg(elevatorNum));
    };

    void notifyArrival(){
        notifyArrival( currentLocation(), stopped(), direction );
    }
}
