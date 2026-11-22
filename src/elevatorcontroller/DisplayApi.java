package elevatorcontroller;

/**
 * THE CURRENT PLAN IS TO MOVE ALL THIS LOGIC OTHER VIRTUAL DEVICES.
 *
 *
 * API exposed by the Display virtual device. Used by the Elevator Controller.
 * The Display virtual device sends display information to other subsystems using the software bus.
 */
public interface DisplayApi {
    /**
     * Notify the cabin panel that the overweight buzzer should turn on or off due to a change in the overweight sensor.
     * @param active True if the buzzer should now be active, False if the buzzer should now be inactive.
     */
    void overweightBuzzerChange(boolean active);

    /**
     * Notify the floor panel that the arrival beep should be played.
     * @param floor The floor where the beep should be played (i.e. the floor the cabin arrived at).
     */
    void arrivalBeep(int floor);

    /**
     * Send a notification that the cabin has changed locations to a new floor. Uses the midpoint of the cabin to
     * determine which floor the cabin is on. The cabin may not be stopped at the indicated floor.
     * @param newFloor The floor number that the midpoint of the cabin is now located on.
     */
    void locationChange(int newFloor);

    /**
     * Send a notification that the cabin has changed direction of travel.
     * @param newDirection The direction the cabin is now traveling in (UP, DOWN, or STATIONARY).
     */
    void directionChange(DirectionOfTravel newDirection);

    /**
     * Sort of duplicates our Direction, but we need a STATIONARY case here.
     */
    enum DirectionOfTravel {
        UP,
        DOWN,
        STATIONARY
    }
}
