package messages;

import common.BusMessage;

public record GoToMessage(int floor) implements BusMessage {
    @Override
    public int topic() {
        return Topics.GO_TO;
    }

    @Override
    public int body() {
        return floor;
    }

    @Override
    public GoToMessage fromBody(int body) {
        return new GoToMessage(body);
    }
}
