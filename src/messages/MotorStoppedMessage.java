package messages;

import common.BusMessage;
import common.Topics;

public record MotorStoppedMessage() implements BusMessage {
    @Override
    public int topic() {
        return Topics.STOPPED;
    }

    @Override
    public int body() {
        return 0;
    }

    public MotorStoppedMessage fromBody(int body) {
        return new MotorStoppedMessage();
    }
}
