package messages;

import common.BusMessage;
import common.Direction;

public record CabinArrivalMessage(int floor, boolean stopped, Direction directionOfTravel) implements BusMessage {
    public static class Body{
        public final static int CABIN_STOPPED_Mask = 2;
        public final static int DIRECTION_UP_Mask = 1;
    }
    @Override
    public int topic() {
        return Topics.ARRIVED;
    }

    @Override
    public int body() {
        int s = 0;
        if (stopped) {
            s = Body.CABIN_STOPPED_Mask;
        }
        int d = 0;
        if(directionOfTravel == Direction.UP){
            d = Body.DIRECTION_UP_Mask;
        }
        return ( floor << 2 ) | ( s | d ) ;
    }

    public CabinArrivalMessage fromBody(int body){
        int floor = body>>2;
        boolean stopped = (body & Body.CABIN_STOPPED_Mask) == Body.CABIN_STOPPED_Mask;
        Direction direction = Direction.DOWN;
        if((body & Body.DIRECTION_UP_Mask) == Body.DIRECTION_UP_Mask){
            direction = Direction.UP;
        }

        return new CabinArrivalMessage(floor, stopped, direction);
    }

}
