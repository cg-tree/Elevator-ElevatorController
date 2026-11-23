package common;

import bus.Message;

public interface BusMessage<T> {
    int topic();
    int body();
    T fromBody(int body);
    //Message toMsg(int subtopic);
    default Message toMsg(int subtopic){
        return new Message(topic(),subtopic, body());

    }

}