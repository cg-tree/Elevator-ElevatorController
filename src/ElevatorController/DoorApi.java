package elevatorcontroller;

/**
 * API exposed by the Door virtual device. Used by the Elevator Controller.
 * The Door virtual device manages the elevator cabin door by sending and receiving messages over the software bus.
 */
public interface DoorApi {
    /**
     * @return True if the door is currently fully open, False otherwise.
     */
    boolean isFullyOpen();

    /**
     * @return True if the door is currently fully closed, False otherwise.
     */
    boolean isFullyClosed();

    /**
     * @return True if the door's obstruction sensor is currently triggered, false otherwise.
     */
    boolean isObstructed();

    /**
     * @return True if the overweight sensors is currently triggered, false otherwise.
     */
    boolean isOverweight();

    /**
     * Open the door.
     */
    void openDoor();

    /**
     * Try to close the door. Closing may fail if an overweight sensor is triggered any time before closing completes.
     */
    boolean closeDoor();
}