package messages;

import common.BusMessage;

public record DoorStateMessage(boolean isOpen)implements BusMessage {

    public static class Body{
        public static int FULLY_OPEN = 1;
        public static int FULLY_CLOSED = 0;
    }
    @Override
    public int topic() {
        return Topics.DOOR_STATE;
    }

    @Override
    public int body() {
        if(isOpen){
            return Body.FULLY_OPEN;
        }
        return Body.FULLY_CLOSED;
    }

    @Override
    public DoorStateMessage fromBody(int body) {
        return new DoorStateMessage(Body.FULLY_OPEN == body);
    }
}
