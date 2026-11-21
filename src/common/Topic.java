package common;

public enum Topic {
    MOTION_COMMAND(100),
    CAR_DETECTION(101);

    private final int encoding;
    Topic(int encoding) { this.encoding = encoding; }

    public int encode() { return encoding; }
    public static Topic decode(int encoded) {
        for (Topic t : values()) {
            if (t.encoding == encoded) return t;
        }
        throw new IllegalArgumentException("Unrecognized topic " + encoded);
    }
}
