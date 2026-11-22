package messages;

import common.BusMessage;
import common.Topics;

public record OverweightMessage()implements BusMessage {
    @Override
    public int topic() {
        return Topics.OVERWEIGHT;
    }

    @Override
    public int body() {
        return 0;
    }

    @Override
    public OverweightMessage fromBody(int body) {
        return new OverweightMessage();
    }
}
