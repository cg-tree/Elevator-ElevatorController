package common;

import bus.Message;

import java.util.UUID;

public interface SoftwareBusApi {
        UUID register();
        void subscribe(UUID id, int topic, int subtopic);
        void publish(UUID id, Message message);
        Message getMsg(UUID id, int topic, int subtopic);
}
