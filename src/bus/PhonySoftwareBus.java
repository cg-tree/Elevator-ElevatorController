package bus;

import common.SoftwareBusApi;

import java.util.*;

public class PhonySoftwareBus implements SoftwareBusApi {
    private final SoftwareBus bus = new SoftwareBus();

    @Override
    public UUID register() {
        UUID id = bus.register();
        System.out.println("id [" + id + "] registered");
        return id;
    }

    @Override
    public void subscribe(UUID id, int topic, int subtopic) {
        System.out.println("id [" + id + "] subscribed to topic [" + topic + "], subtopic [" + subtopic + "]");
        bus.subscribe(id, topic, subtopic);
    }

    @Override
    public void publish(UUID id, Message message) {
        System.out.println("id [" + id + "] publishing message [" + message + "] ");
        bus.publish(id, message);
    }

    @Override
    public Message getMsg(UUID id, int topic, int subtopic) {
        Message msg = bus.getMsg(id, topic, subtopic);
        if (msg != null) {
            System.out.println("id [" + id + "] grabbing message: topic [" + topic + "], subtopic [" + subtopic + "], msg [" + msg + "] ");
                return msg;
        }
        return null;
    }
}
