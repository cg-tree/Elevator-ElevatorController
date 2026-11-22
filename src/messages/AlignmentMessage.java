package messages;

import common.BusMessage;
import common.Topics;

public record AlignmentMessage(int floor, ActiveSensors sensors) implements BusMessage {

    public enum ActiveSensors {
        UPPER(0),
        LOWER(1),
        BOTH(2);

        public final int encoding;
        ActiveSensors(int encoding) { this.encoding = encoding; }
    }


    @Override
    public int body() {
        return -1;
    }

    @Override
    public int topic() {
        return Topics.ALIGNMENT;
    }

    public AlignmentMessage fromBody(int body) {
        throw new UnsupportedOperationException();
    }
}
