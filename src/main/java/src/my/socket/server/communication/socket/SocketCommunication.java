package src.my.socket.server.communication.socket;

import src.my.socket.server.communication.Communication;
import src.my.socket.server.sql.ExecuteSql;

public class SocketCommunication extends Communication {

    public SocketCommunication(ExecuteSql executeSql) {
        super(executeSql);
    }
}
