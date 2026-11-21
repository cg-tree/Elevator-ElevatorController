package common;

public class BuildingConfigFactory {
    public static BuildingConfig getBuildingConfig(double floorToFloorHeight,
                                                   double floorThickness,
                                                   double sensorTolerance) {
        return new BuildingConfig(floorToFloorHeight, floorToFloorHeight,
                floorThickness, 0, floorToFloorHeight - floorThickness,
                floorToFloorHeight, sensorTolerance / 2, sensorTolerance,
                floorToFloorHeight/2);
    }

    public static BuildingConfig getBuildingConfig() {
        return getBuildingConfig(5, 1, 0.01);
    }
}
