package src.my.socket.server.communication.socket;

import src.my.socket.server.communication.ChatComm;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketChatComm extends ChatComm {
    public SocketChatComm(ServerSocket server, Socket client) {
        super(server, client);
    }

}
