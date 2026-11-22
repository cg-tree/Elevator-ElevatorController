package messages;

import common.BusMessage;
import common.Topics;

public record MoveDoorMessage(DOOR_CMD cmd)implements BusMessage {
    public enum DOOR_CMD{
        CLOSE(0),
        OPEN(1);
        public final int encoding;
        DOOR_CMD(int doorcmd){this.encoding =doorcmd;}
    }

    @Override
    public int topic() {
        return Topics.MOVE_DOOR;
    }

    @Override
    public int body() {
        return cmd().encoding;
    }

    @Override
    public MoveDoorMessage fromBody(int body) {
        for(var cmd:DOOR_CMD.values()){
            if(cmd.encoding == body){
                return new MoveDoorMessage(cmd);
            }
        }
        throw new UnsupportedOperationException();
    }
}
