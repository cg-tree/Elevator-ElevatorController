package messages;

import common.BusMessage;

public record ArrivalBeepMessage(int floor) implements BusMessage {

    @Override
    public int topic() {
        return Topics.BEEP;
    }

    @Override
    public int body() {
        return floor();
    }

    public ArrivalBeepMessage fromBody(int body){
        return new ArrivalBeepMessage(body);
    }
}