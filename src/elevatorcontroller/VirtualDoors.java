package elevatorcontroller;

import bus.Message;
import bus.SoftwareBus;
import common.SoftwareBusApi;
import common.Topics;
import messages.*;

import java.util.UUID;

public class VirtualDoors implements DoorApi{
    private boolean isOpen = false;
    private boolean obstructed = false;
    private boolean overloaded = false;
    private SoftwareBusApi bus;
    private final UUID id;
    private final int elevatorNum;
    public VirtualDoors(SoftwareBusApi busApi, int elevatorNum){
        bus = busApi;
        id = bus.register();
        this.elevatorNum = elevatorNum;
        var openMsg = new DoorStateMessage(false);
        bus.subscribe(id,openMsg.topic(),elevatorNum);
        var obstructedMsg = new ObstructedMessage(false);
        bus.subscribe(id,obstructedMsg.topic(),elevatorNum);
        var overloadedMsg = new OverweightMessage(false);
        bus.subscribe(id,overloadedMsg.topic(),elevatorNum);
    }
    /**
     * @return True if the door is partially or fully open. False if the door is fully closed.
     */
    @Override
    public boolean isDoorOpen() {
        updateIsOpen();
        return isOpen;
    }
    private void updateIsOpen(){
        messages.DoorStateMessage doorStateMessage = new DoorStateMessage(isOpen);
        Message msg = bus.getMsg(id, doorStateMessage.topic(), elevatorNum);
        while(msg != null){
            isOpen = ( doorStateMessage.fromBody( msg.body() )).isOpen();
            msg = bus.getMsg(id, doorStateMessage.topic(), elevatorNum);
        }
    }

    /**
     * @return True if the door's obstruction sensor is currently triggered, false otherwise.
     */
    @Override
    public boolean isObstructed() {
        updateObstructed();
        return obstructed;
    }

    private void updateObstructed() {
        ObstructedMessage obstructedMessage = new ObstructedMessage(obstructed);
        Message msg = bus.getMsg(id,obstructedMessage.topic(),elevatorNum);
        while(msg != null){
            obstructed = (obstructedMessage.fromBody(msg.body())).isObstructed();
            msg = bus.getMsg(id,obstructedMessage.topic(),elevatorNum);
        }
    }

    /**
     * @return True if the overloaded sensors is currently triggered, false otherwise.
     */
    @Override
    public boolean isOverloaded() {
        updateOverloaded();
        return overloaded;
    }

    private void updateOverloaded() {
        OverweightMessage overloadedMessage = new OverweightMessage(overloaded);
        Message msg = bus.getMsg(id,overloadedMessage.topic(),elevatorNum);
        while(msg != null) {
            overloaded = (overloadedMessage.fromBody(msg.body())).isOverweight();
            msg = bus.getMsg(id, overloadedMessage.topic(), elevatorNum);
        }
    }

    /**
     * Open the door.
     */
    @Override
    public void openDoor() {
        MoveDoorMessage moveDoorMessage = new MoveDoorMessage(MoveDoorMessage.DOOR_CMD.OPEN);
        bus.publish(id, moveDoorMessage.toMsg(elevatorNum));
    }

    /**
     * Close the door.
     */
    @Override
    public void closeDoor() {
        MoveDoorMessage moveDoorMessage = new MoveDoorMessage(MoveDoorMessage.DOOR_CMD.CLOSE);
        bus.publish(id, moveDoorMessage.toMsg(elevatorNum));
    }
}
