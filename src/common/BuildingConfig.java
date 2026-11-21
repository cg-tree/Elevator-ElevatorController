package common;

public record BuildingConfig(double floorToFloorHeight,
                             double elevatorMaxSpeed,
                             double floorThickness,
                             double lowerSensorOffset,
                             double upperSensorOffset,
                             double elevatorAcceleration,
                             double nudgeDistance,
                             double sensorTolerance,
                             double collisionOffset
) {
}
