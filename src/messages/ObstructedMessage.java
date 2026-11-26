package messages;

import common.BusMessage;

public record ObstructedMessage(boolean isObstructed) implements BusMessage {
    public static class Body{
        public final static int OBSTRUCTED = 1;
        public final static int NOT_OBSTRUCTED = 0;
    }
    @Override
    public int topic() {
        return Topics.OBSTRUCTION;
    }

    @Override
    public int body() {
        if(isObstructed){
            return Body.OBSTRUCTED;
        }
        return Body.NOT_OBSTRUCTED;
    }

    @Override
    public ObstructedMessage fromBody(int body) {
        return new ObstructedMessage(body == Body.OBSTRUCTED);
    }
}
