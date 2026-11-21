package common;

public interface ElevatorMotionApi {

    void start(Direction direction);
    void stop();
    void nudge(Direction direction);
    int upperFloorSensorTriggered(); //returns floor number or 0 if none
    int lowerFloorSensorTriggered(); //returns floor number or 0 if none
    boolean isAligned(); //true if (upperFloorSensorTriggered == lowerFloorSensorTriggered) and neither is 0
}
