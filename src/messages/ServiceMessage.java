package messages;

import common.BusMessage;
import common.Topics;

public record ServiceMessage(int floor) implements BusMessage {

    @Override
    public int topic(){
        return Topics.SERVICE;
    }

    @Override
    public int body() {
        return floor();
    }

    @Override
    public ServiceMessage fromBody(int body) {
        return new ServiceMessage(body);
    }
}
