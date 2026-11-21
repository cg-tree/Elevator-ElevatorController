package common;

import java.util.Objects;

public record CarDetection(SensorPlacement sensor, int floorNumber) implements BusPayload {

    public CarDetection {
        Objects.requireNonNull(sensor, "sensor");
        if (floorNumber < 1)
            throw new IllegalArgumentException("floorNumber must be >= 1, received " + floorNumber);
    }

    @Override
    public Topic topic() {
        return Topic.CAR_DETECTION;
    }

    @Override
    public long body() {
        return 2L * floorNumber + (sensor == SensorPlacement.UPPER ? 1 : 0);
    }

    public static CarDetection fromBody(long body) {
        if (body < 2)
            throw new IllegalArgumentException("Invalid CarDetection body " + body + ": floor would be < 1");
        if (body / 2 > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Invalid CarDetection body " + body + ": floor too large");
        SensorPlacement sensor = body % 2 == 1 ? SensorPlacement.UPPER : SensorPlacement.LOWER;
        int floorNumber = (int) (body / 2);
        return new CarDetection(sensor, floorNumber);
    }
}
