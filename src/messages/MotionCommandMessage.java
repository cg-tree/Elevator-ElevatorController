package messages;

import common.BusMessage;
import common.Topics;

public enum MotionCommandMessage implements BusMessage {
    START_UP(0),
    START_DOWN(1),
    STOP(2),
    NUDGE_UP(3),
    NUDGE_DOWN(4);

    public final int encoding;
    MotionCommandMessage(int encoding) { this.encoding = encoding; }

    public int topic() {
        return Topics.MOTION_COMMAND;
    }

    public int body() {
        return encoding;
    }

    public MotionCommandMessage fromBody(int body) {
        for (MotionCommandMessage cmd : values()) {
            if (cmd.encoding == body) return cmd;
        }
        throw new IllegalArgumentException("Unrecognized encoded MotionCommand body: " + body);
    }
}
