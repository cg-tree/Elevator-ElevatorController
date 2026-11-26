package messages;

import common.BusMessage;

public record AlignmentMessage(int floor, ActiveSensors sensors) implements BusMessage {

    public enum ActiveSensors {
        UPPER(1),
        LOWER(2),
        BOTH(3);

        public final int encoding;
        ActiveSensors(int encoding) { this.encoding = encoding; }
    }
    public class SensorMask{
        public final static int BOTH=3;
        public final static int UPPER=1;
        public final static int LOWER=2;
    }

    @Override
    public int body() {
        // Inverse of fromBody: floor in the high bits, sensor mask in the low 2 bits.
        return (floor << 2) | sensors.encoding;
    }

    @Override
    public int topic() {
        return Topics.ALIGNMENT;
    }

    public AlignmentMessage fromBody(int body) {
        int floor = body>>2;
        int sensors = body & SensorMask.BOTH;
        if(sensors == SensorMask.BOTH){
            return new AlignmentMessage(floor, ActiveSensors.BOTH);
        } else if (sensors == SensorMask.UPPER){
            return new AlignmentMessage(floor,ActiveSensors.UPPER);
        } else if (sensors == SensorMask.LOWER){
            return new AlignmentMessage(floor, ActiveSensors.LOWER);
        }
        throw new UnsupportedOperationException();
    }
}
