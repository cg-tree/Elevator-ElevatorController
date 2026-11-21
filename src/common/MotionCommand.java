package common;

public enum MotionCommand implements BusPayload {
    START_UP(0),
    START_DOWN(1),
    STOP(2),
    NUDGE_UP(3),
    NUDGE_DOWN(4);

    // TODO: change to int.
    public final long encoding;
    MotionCommand(long encoding) { this.encoding = encoding; }

    @Override
    public Topic topic() {
        return Topic.MOTION_COMMAND;
    }

    @Override
    public long body() {
        return encoding;
    }

    public static MotionCommand fromBody(long body) {
        for (MotionCommand cmd : values()) {
            if (cmd.encoding == body) return cmd;
        }
        throw new IllegalArgumentException("Unrecognized encoded MotionCommand body: " + body);
    }
}
