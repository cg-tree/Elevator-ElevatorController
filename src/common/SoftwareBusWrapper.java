package common;

import bus.Message;
import bus.SoftwareBus;

import java.util.UUID;

/**
 * Implements common.SoftwareBusApi using the real SoftwareBus.
 * This is the bridge between the software bus team's code and ours.
 */
public class SoftwareBusWrapper implements SoftwareBusApi {
    private static final String ROUTER_IP = "localhost";
    private static final int ROUTER_PORT = 7150;
    private final SoftwareBus bus;

    public SoftwareBusWrapper() {
        bus = new SoftwareBus(ROUTER_IP, ROUTER_PORT);
    }


    @Override
    public UUID register() {
        return bus.register();
    }

    @Override
    public void subscribe(UUID id, int topic, int subtopic) {
        bus.subscribe(id, topic, subtopic);
    }

    @Override
    public void publish(UUID id, Message message) {
        bus.publish(id, message);
    }

    @Override
    public Message getMsg(UUID id, int topic, int subtopic) {
        Message msg = bus.getMsg(id, topic, subtopic);
        if (msg != null) {
            IO.println("--- TOPIC = " + topic + " ---");
            IO.println(msg);
        }
        return msg;
    }
}
