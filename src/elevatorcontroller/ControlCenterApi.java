package elevatorcontroller;

import common.Direction;

import java.util.Optional;

/**
 * API exposed by the Control Center virtual device. Used by the Elevator Controller.
 * The Control Center virtual device sends and receives messages from the Control Center via the software bus.
 */
public interface ControlCenterApi {
    /**
     * Consume one command sent from the Control Center if it is available. Removes the command from the message queue.
     * Returns an empty optional if no commands are enqueued.
     */
    Optional<ElevatorCommand> receiveCommand();
}
