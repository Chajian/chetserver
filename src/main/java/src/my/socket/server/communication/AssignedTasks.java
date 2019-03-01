package src.my.socket.server.communication;

import src.my.socket.server.communication.socket.SocketCommunication;
import src.my.socket.server.communication.socket.SocketRegisterComm;
import src.my.socket.server.info.Tran;
import src.my.socket.server.info.user.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AssignedTasks implements Runnable{

    private Communication communication;
    private LoginComm loginComm;
    private RegisterComm registerComm;

    public AssignedTasks(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void run() {
        while(true) {
            ConcurrentHashMap<String, Socket> socketConcurrentHashMap = communication.getTranvers_socket();
            for(int i = 0 ; i < socketConcurrentHashMap.size() ; i++){
                Diversion(String.valueOf(i),socketConcurrentHashMap.get(String.valueOf(i)));
            }
        }
    }

    //游客分流
    public void Diversion(String namme,Socket socket){

        try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                System.out.println("分流");
//                if (objectInputStream.readObject() instanceof Tran) {
                    Tran tran = (Tran) objectInputStream.readObject();
                    switch (tran.getObjectInfo()) {
                        case LOGIN_MESSAGE:
                            loginComm = communication.getLogin();
                            if (loginComm.login(tran, objectInputStream, new ObjectOutputStream(socket.getOutputStream()))) {
                                communication.getTranvers_socket().remove(namme);
                                System.out.println("登录成功!");
                            }
                            break;


                        case REGISTER_MESSAGE:
                            registerComm = communication.getRegister();
                            if(((SocketRegisterComm)registerComm).getExecuteSql()!=null) {
                                registerComm.register((User) tran.getObject());
                            }
                            else {
                                ((SocketRegisterComm) registerComm).setExecuteSql(communication.getExecuteSql());
                                registerComm.register((User) tran.getObject());
                            }
                            break;
                    }
//                }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
