package elevatorcontroller;

import common.Direction;
import common.SoftwareBusApi;
import messages.AlignmentMessage;

/**
 * - THIS ASSUMES THE LOWER SENSOR OFFSET IS 0!
 * - Cabin position is the position of the very bottom of the cabin.
 * - Assumes we start stopped at floor 1.
 * - Treats "nudging" as *not* stopped.
 */
public class VirtualCabin2 {
    /// Floor to floor height.
    private static final double FLOOR_H = 5;
    private static final double UPPER_SENSOR_OFFSET = 4;
    private static final double SENSOR_TOLERANCE = 0.1;
    /// Max cruising speed in meters per second.
    private static final double MAX_SPEED = FLOOR_H;
    /// Acceleration in meters per seconds.
    private static final double ACC = FLOOR_H;

    private final SoftwareBusApi bus;

    private AlignmentMessage lastAlign = new AlignmentMessage(1, AlignmentMessage.ActiveSensors.BOTH);
    private Direction directionOfTravel = null;
    private State state = new State.StoppedAt(1);

    public VirtualCabin2(SoftwareBusApi bus) { this.bus = bus; }


    private void goToFloor(int floor) {
        if (state instanceof State.TransittingTo) {
            return;
        }

    }

    private void handleAlignmentMessage(AlignmentMessage alignMsg) {

    }

    /**
     * The height in meters of elevator cabin when an alignment event occurs.
     */
    private double alignToPos(AlignmentMessage alignMsg, Direction directionOfTravel) {
        double centerToEdge = (directionOfTravel == Direction.UP ? -1 : 1) * SENSOR_TOLERANCE;
        if (alignMsg.sensors() == AlignmentMessage.ActiveSensors.NONE) {
            // We just left the window centered on centerPos, add the distance to the edge.
            return centerPos(lastAlign) + centerToEdge;
        }
        // We just entered the window centered on centerPos, subtract the distance to the edge.
        return centerPos(alignMsg) - centerToEdge;
    }

    /**
     * The height in meters of the center of the window where the cabin could be given an alignment message.
     */
    private double centerPos(AlignmentMessage m) {
        double floorPos = FLOOR_H * (m.floor() - 1);
        return switch (m.sensors()) {
            case BOTH  -> floorPos;
            case LOWER -> floorPos - UPPER_SENSOR_OFFSET;
            case UPPER -> floorPos + UPPER_SENSOR_OFFSET;
            case NONE  -> throw new IllegalArgumentException("NONE has no center");
        };
    }

    /**
     * Compute the amount of time required to travel `dist` starting at `initialSpeed` and accelerating at `ACC` until
     * reaching `MAX_SPEED`.
     */
    private double timeToTravelDist(double dist, double initialSpeed) {
        // Distance covered while accelerating from initialSpeed up to MAX_SPEED.
        double accelDist = (MAX_SPEED * MAX_SPEED - initialSpeed * initialSpeed) / (2 * ACC);

        if (dist >= accelDist) {
            // Accelerate to MAX_SPEED then cruise for the remainder.
            double accelTime = (MAX_SPEED - initialSpeed) / ACC;
            return accelTime + (dist - accelDist) / MAX_SPEED;
        }

        // Travel dist while still accelerating. Need to solve dist = v*t + 0.5*ACC*t^2.
        // Rearranged form of (-v + sqrt(v^2 + 2*ACC*dist)) / ACC that avoids cancellation when v is large.
        return 2 * dist / (initialSpeed + Math.sqrt(initialSpeed * initialSpeed + 2 * ACC * dist));
    }

    private sealed interface State {
        record StoppedAt(int floor) implements State {};
        record TransittingTo(int floor) implements State {};
    }
}
