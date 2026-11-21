package elevatorcontroller;

import common.SoftwareBusApi;

import java.util.UUID;
import java.util.function.Consumer;

public class VirtualCabin implements CabinApi{
    private UUID id;
    private SoftwareBusApi busApi;

    private boolean isinmotion;
    private int currentfloor = 1;

    public Consumer<Integer> onLocationChangeCallBack;
    public Consumer<Integer> onServiceCallBack;
    public Consumer<DisplayApi.DirectionOfTravel> onDirectionChangeCallBack;
    /**
     * Check if the cabin is in motion.
     */
    @Override
    public boolean isInMotion() {
        return isinmotion;
    }

    /**
     * Get the floor where the cabin is, according to the midpoint of the cabin.
     */
    @Override
    public int currentFloor() {
        return currentfloor;
    }

    /**
     * Direct the cabin to travel to the indicated floor.
     * @param floor
     */
    @Override
    public void travelTo(int floor) {

    }

    /**
     * Register a callback that will be called whenever location changes.
     *
     * @param callback
     */
    @Override
    public void onLocationChange(Consumer<Integer> callback) {
        onDirectionChangeCallBack
    }

    /**
     * Register a callback that will be called whenever the cabin arrives at a floor and opens the cabin
     * doors.
     *
     * @param callback
     */
    @Override
    public void onService(Consumer<Integer> callback) {

    }

    /**
     * Register a callback that will be called whenever direction of travel changes.
     *
     * @param callback
     */
    @Override
    public void onDirectionChange(Consumer<DisplayApi.DirectionOfTravel> callback) {

    }

    /**
     * Register a callback that will be called whenever location changes.
     *
     * @param callback
     */
    @Override
    public void registerOnLocationChange(Consumer<Integer> callback) {
        onLocationChange = callback;
    }

    /**
     * Register a callback that will be called whenever the cabin arrives at a floor and opens the cabin
     * doors.
     * **DING**
     * // publish beep
     * @param callback
     */
    @Override
    public void registerOnService(Consumer<Integer> callback) {
        onService = callback;
    }

    /**
     * Register a callback that will be called whenever direction of travel changes.
     *
     * @param callback
     */
    @Override
    public void registerOnDirectionChange(Consumer<DisplayApi.DirectionOfTravel> callback) {

    }
}
