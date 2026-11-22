package messages;

import common.BusMessage;
import common.Topics;

public record OverweightBuzzerMessage() implements BusMessage {


    @Override
    public int topic() {
        return Topics.BUZZER;
    }

    @Override
    public int body() {
        return 0;
    }

    public OverweightBuzzerMessage fromBody(int body){
        return new OverweightBuzzerMessage();
    }
}
