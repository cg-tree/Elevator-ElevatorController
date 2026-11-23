package messages;

import common.BusMessage;
import common.Topics;

public record OverLoadedMessage(boolean isOverweight)implements BusMessage {

    public static class Body{
        public final static int OVERWEIGHT = 1;
        public final static int NOT_OVERWEIGHT = 0;
    }
    @Override
    public int topic() {
        return Topics.OVERWEIGHT;
    }

    @Override
    public int body() {
        if(isOverweight){
            return Body.OVERWEIGHT;
        }
        return Body.NOT_OVERWEIGHT;
    }

    @Override
    public OverLoadedMessage fromBody(int body) {
        if(body == Body.OVERWEIGHT){
            return new OverLoadedMessage(true);
        }
        return new OverLoadedMessage(false);
    }
}
