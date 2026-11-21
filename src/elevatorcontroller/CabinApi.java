package elevatorcontroller;

import java.util.function.Consumer;

/**
 * API exposed by the Cabin virtual device. Used by the Elevator Controller.
 * The Cabin virtual device manages the motion of the elevator cabin by communicating with the
 * <code>ElevatorMotion</code> subsystem via the software bus.
 */
public interface CabinApi {
    /**
     * Check if the cabin is in motion.
     */
    boolean isInMotion();

    /**
     * Get the floor where the cabin is, according to the midpoint of the cabin.
     */
    int currentFloor();

    /**
     * Direct the cabin to travel to the indicated floor.
     */
    void travelTo(int floor);

    /**
     * Register a callback that will be called whenever location changes.
     */
    void onLocationChange(Consumer<Integer> callback);

    /**
     * Register a callback that will be called whenever the cabin arrives at a floor and opens the cabin
     * doors.
     */
    void onService(Consumer<Integer> callback);

    /**
     * Register a callback that will be called whenever direction of travel changes.
     */
    void onDirectionChange(Consumer<DisplayApi.DirectionOfTravel> callback);

}
