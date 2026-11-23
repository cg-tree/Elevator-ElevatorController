package elevatorcontroller;

import common.SoftwareBusApi;

import java.util.Optional;

public class VirtualControlCenter implements ControlCenterApi {
    private final int elevatorNumber;
    private final SoftwareBusApi bus;

    public VirtualControlCenter(SoftwareBusApi bus, int elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
        this.bus = bus;
    }

    /**
     * TODO: IMPLEMENT ME! Just a stub for now.
     * @return
     */
    @Override
    public Optional<ElevatorCommand> receiveCommand() {
        return Optional.empty();
    }
}
