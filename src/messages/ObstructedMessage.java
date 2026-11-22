package messages;

import common.BusMessage;
import common.Topics;

public record ObstructedMessage(boolean isObstructed) implements BusMessage {

    @Override
    public int topic() {
        return Topics.OBSTRUCTION;
    }

    @Override
    public int body() {
        if(isObstructed){
            return 1;
        }
        return 0;//0 or 1
    }

    @Override
    public ObstructedMessage fromBody(int body) {
        return new ObstructedMessage(false);
    }
}
