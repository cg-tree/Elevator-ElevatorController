package messages;

import common.BusMessage;
import common.Topics;

public record MotorStoppedMessage(boolean isStopped) implements BusMessage {
    @Override
    public int topic() {
        return Topics.STOPPED;
    }

    @Override
    public int body() {
        if(isStopped){return 1;}
        return 0;
    }

    public MotorStoppedMessage fromBody(int body) {
        return new MotorStoppedMessage(body == 1);
    }
}
