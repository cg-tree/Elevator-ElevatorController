package messages;

import common.BusMessage;
import common.Topics;

public record DoorStateMessage(boolean isOpen)implements BusMessage {
    @Override
    public int topic() {
        return Topics.DOOR_STATE;
    }

    @Override
    public int body() {
        if(isOpen){
            return 1;
        }
        return 0;
    }

    @Override
    public DoorStateMessage fromBody(int body) {
        return new DoorStateMessage(1 == body);
    }
}
