package src.my.socket.server.communication;

import src.my.socket.server.info.Tran;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface LoginComm {

    boolean login(Tran tran, ObjectInputStream inputStream, ObjectOutputStream outputStream);

}
