package common;

/**
 * The payload that's sent over the software bus, either a command (start, stop, etc.) or an elevator car sensor
 * detection.
 */
public sealed interface BusPayload permits MotionCommand, CarDetection {
    Topic topic();
    long body();

    static BusPayload decode(int topic, long body) {
        Topic decodedTopic = Topic.decode(topic);
        return switch (decodedTopic) {
            case MOTION_COMMAND -> MotionCommand.fromBody(body);
            case CAR_DETECTION -> CarDetection.fromBody(body);
        };
    }
}
