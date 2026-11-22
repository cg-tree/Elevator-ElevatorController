package messages;

import common.BusMessage;
import common.Direction;
import common.Topics;

public record CabinArrivalMessage(int floor, boolean stopped, Direction directionOfTravel) implements BusMessage {

    @Override
    public int topic() {
        return Topics.ARRIVED;
    }

    @Override
    public int body() {
        //TODO implement me!!!!
        return 0;
    }

    public CabinArrivalMessage fromBody(int body){
        //TODO implement me!!!!
        return new CabinArrivalMessage(0,false,Direction.DOWN);
    }

}
