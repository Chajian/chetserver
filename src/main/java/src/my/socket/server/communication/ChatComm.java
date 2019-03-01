package src.my.socket.server.communication;

import src.my.socket.server.info.Tran;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ChatComm implements Runnable{
    private ServerSocket server;
    private Socket client;
    private ObjectOutputStream outinput;
    private ObjectInputStream input;

    public ChatComm(ServerSocket server,Socket client){
    }

    @Override
    public void run() {

    }

    //将各种操作分流
    public void Diversion(Tran tran){

        switch (tran.getObjectInfo()){

            case PRIVATE_CHAT_MESSAGE:

                sendMessage(tran);

                break;


            case PUBLIC_CHAT_MESSAGE:



                break;
        }

    }


    void sendMessage(Tran tran){}//处理私聊
}
