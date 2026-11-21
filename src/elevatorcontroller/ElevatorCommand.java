package elevatorcontroller;

/**
 * A command sent from the Control Center to the Elevator Controller.
 */
public sealed interface ElevatorCommand {

    /**
     * Direct the elevator to power on.
     */
    record PowerOnElevator() implements ElevatorCommand {}

    /**
     * Direct the elevator to power off.
     */
    record PowerOffElevator() implements ElevatorCommand {}

    /**
     * Directs the Elevator Controller to travel to the indicated floor without opening the doors.
     */
    record GoTo(int floor) implements ElevatorCommand {}

    /**
     * Directs the Elevator Controller to travel to the indicated floor and open the doors on arrival.
     */
    record Service(int floor) implements ElevatorCommand {}


}
