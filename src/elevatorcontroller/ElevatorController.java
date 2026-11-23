package elevatorcontroller;

public class ElevatorController {
    private final ControlCenterApi controlCenter;
    private final CabinApi cabin;
    private final DoorApi door;

    public ElevatorController(ControlCenterApi controlCenter, CabinApi cabin, DoorApi door) {
        this.controlCenter = controlCenter;
        this.cabin = cabin;
        this.door = door;
    }
        
}
