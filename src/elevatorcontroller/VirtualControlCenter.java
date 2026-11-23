package elevatorcontroller;

import bus.Message;
import common.SoftwareBusApi;
import messages.GoToMessage;
import messages.ServiceMessage;

import java.util.Optional;
import java.util.Queue;
import java.util.UUID;

public class VirtualControlCenter implements ControlCenterApi {
    private final UUID id;
    private final int elevatorNum;
    private SoftwareBusApi bus;

    Queue<ElevatorCommand> commandQueue;
    public VirtualControlCenter(SoftwareBusApi bus, int elevatorNum){
        this.bus = bus;
        this.elevatorNum = elevatorNum;
        id = bus.register();
        var goToMsg = new GoToMessage(1);
        bus.subscribe(id, goToMsg.topic(), elevatorNum);
        var serviceMsg = new ServiceMessage(1);
        bus.subscribe(id,serviceMsg.topic(),elevatorNum);

    }
    @Override
    public Optional<ElevatorCommand> receiveCommand() {
        ElevatorCommand elevatorCommand;
        var goToMsg = new GoToMessage(1);
        Message cmd = bus.getMsg(id,goToMsg.topic(),elevatorNum);
        if (null == cmd){
            var serviceMsg = new ServiceMessage(1);
            cmd = bus.getMsg(id, serviceMsg.topic(), elevatorNum);
        } else if (null != cmd) {
            elevatorCommand = new ElevatorCommand.GoTo(cmd.body());
            return Optional.of(elevatorCommand);
        }
        if(null != cmd){
            elevatorCommand = new ElevatorCommand.Service(cmd.body());
            return Optional.of(elevatorCommand);

        }
        return Optional.empty();
    }
}
