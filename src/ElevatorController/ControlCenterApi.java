package elevatorcontroller;

import common.Direction;

import java.util.Optional;

/**
 * API exposed by the Control Center virtual device. Used by the Elevator Controller.
 * The Control Center virtual device sends and receives messages from the Control Center via the software bus.
 */
public interface ControlCenterApi {
    /**
     * Tell the Control Center that the elevator cabin arrived at a floor. The cabin may have stopped at
     * <code>floor</code> or it may have passed by without stopping.
     * @param floor The floor number that the cabin arrived at.
     * @param stopped True if the elevator stopped at <code>floor</code>, false if the elevator passed without stopping.
     * @param directionOfTravel The direction (UP or DOWN) that the elevator was traveling in before arrival.
     */
    void notifyArrival(int floor, boolean stopped, Direction directionOfTravel);

    /**
     * Consume one command sent from the Control Center if it is available. Removes the command from the message queue.
     * Returns an empty optional if no commands are enqueued.
     */
    Optional<ElevatorCommand> receiveCommand();
}
