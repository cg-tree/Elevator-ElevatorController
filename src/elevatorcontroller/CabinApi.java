package elevatorcontroller;

/**
 * API exposed by the Cabin virtual device. Used by the Elevator Controller.
 * The Cabin virtual device manages the motion of the elevator cabin by communicating with the
 * <code>ElevatorMotion</code> subsystem via the software bus.
 */
public interface CabinApi {
    /**
     * Check if the cabin is stopped.
     * state tracking Do Not Republish
     */
    boolean stopped();

    /**
     * Get the floor where the cabin is, according to the midpoint of the cabin.
     */
    int currentLocation();

    /**
     * Direct the cabin to travel to the indicated floor.
     */
    void goToFloor(int floor);

    /**
     * @return The floor number of cabin's current destination. If the cabin has already arrived at its destination,
     * the current floor number is returned.
     */
    int destination();
}
