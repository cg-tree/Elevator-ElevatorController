package bus;

import common.SoftwareBusApi;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PhonySoftwareBus implements SoftwareBusApi {
    private final List<Message> messages = new LinkedList<>();

    @Override
    public UUID register() {
        UUID id = UUID.randomUUID();
        System.out.println("id [" + id + "] registered");
        return id;
    }

    @Override
    public void subscribe(UUID id, int topic, int subtopic) {
        System.out.println("id [" + id + "] subscribed to topic [" + topic + "], subtopic [" + subtopic + "]");
    }

    @Override
    public void publish(UUID id, Message message) {
        System.out.println("id [" + id + "] publishing message [" + message + "] ");
        messages.add(message);
    }

    @Override
    public Message getMsg(UUID id, int topic, int subtopic) {
        for (int i = 0; i < messages.size(); i++) {
            Message msg = messages.get(i);
            if (msg != null && msg.topic() == topic && msg.subtopic() == subtopic) {
                messages.remove(i);
                System.out.println("id [" + id + "] grabbing message: topic [" + topic + "], subtopic [" + subtopic + "] ");
                return msg;
            }
        }
        return null;
    }
}
