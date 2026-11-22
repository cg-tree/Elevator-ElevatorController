package elevatorcontroller;

/**
 * API exposed by the Door virtual device. Used by the Elevator Controller.
 * The Door virtual device manages the elevator cabin door by sending and receiving messages over the software bus.
 */
public interface DoorApi {
    /**
     * @return True if the door is partially or fully open. False if the door is fully closed.
     */
    boolean isDoorOpen();

    /**
     * @return True if the door's obstruction sensor is currently triggered, false otherwise.
     */
    boolean isObstructed();

    /**
     * @return True if the overloaded sensors is currently triggered, false otherwise.
     */
    boolean isOverloaded();

    /**
     * Open the door.
     */
    void openDoor();

    /**
     * Close the door.
     */
    void closeDoor();
}