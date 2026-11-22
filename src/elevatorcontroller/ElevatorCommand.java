package elevatorcontroller;

/**
 * A command sent from the Control Center to the Elevator Controller.
 */
public sealed interface ElevatorCommand {
    /**
     * Directs the Elevator Controller to travel to the indicated floor without opening the doors.
     */
    record GoTo(int floor) implements ElevatorCommand {}

    /**
     * Directs the Elevator Controller to travel to the indicated floor and open the doors on arrival.
     */
    record Service(int floor) implements ElevatorCommand {}


}
