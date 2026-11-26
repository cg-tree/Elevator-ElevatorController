package messages;

import common.BusMessage;

public record OverLoadedBuzzerMessage(int cmd) implements BusMessage {
    public static class BuzzerCommand{
        public static final int BUZZER_ON = 1;
        public static final int BUZZER_OFF = 0;
    }

    @Override
    public int topic() {
        return Topics.BUZZER;
    }

    @Override
    public int body() {
        return cmd;
    }

    public OverLoadedBuzzerMessage fromBody(int body){
        return new OverLoadedBuzzerMessage( body & BuzzerCommand.BUZZER_ON);//sanitize body
    }
}
