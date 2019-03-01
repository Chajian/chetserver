package src.my.socket.server.communication;

import src.my.socket.server.communication.socket.SocketLoginComm;
import src.my.socket.server.communication.socket.SocketRegisterComm;
import src.my.socket.server.communication.socket.SocketTasks;
import src.my.socket.server.info.ObjectInfo;
import src.my.socket.server.info.Tran;
import src.my.socket.server.info.user.User;
import src.my.socket.server.sql.ExecuteSql;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Communication {

    private final int PORT = 5578;//服务器端口
    private ServerSocket server;
    private ChatComm chat;
    private LoginComm login;
    private RegisterComm register;
    private AssignedTasks assignedTasks;
    private ConcurrentHashMap<String, User> map = new ConcurrentHashMap();//存储在线玩家
    private ConcurrentHashMap<ObjectInfo,Tran> request = new ConcurrentHashMap<>();//存储玩家的请求
    private ConcurrentHashMap<String,Socket> users_socket = new ConcurrentHashMap<>();//存储socket
    private ConcurrentHashMap<String,Socket> tranvers_socket = new ConcurrentHashMap<>();//存储游客
    private ExecuteSql executeSql;


    public Communication(ExecuteSql executeSql){
        try {
            this.executeSql = executeSql;
            server = new ServerSocket(PORT);
            login = new SocketLoginComm(this,executeSql);
            register = new SocketRegisterComm(executeSql,this);;
            assignedTasks = new SocketTasks(this);
            new Thread(assignedTasks).start();
        }
        catch (IOException e){
            System.out.println("服务器启动失败!");
        }
        while (true){
            try {
                Socket client = server.accept();
                tranvers_socket.put(String.valueOf(tranvers_socket.size()),client);//存游客
                System.out.println("有一位游客连接");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void LoginUser(User user,String username){
        map.put(username,user);
    }

    //判断用户是否在线
    public boolean isLogin(String username){
        if(map.get(username)!=null)
            return true;
        else
            return false;
    }

    public ConcurrentHashMap<String, Socket> getTranvers_socket() {
        return tranvers_socket;
    }

    public LoginComm getLogin() {
        return login;
    }

    public void setLogin(LoginComm login) {
        this.login = login;
    }

    public RegisterComm getRegister() {
        return register;
    }

    public void setRegister(RegisterComm register) {
        this.register = register;
    }

    public ExecuteSql getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(ExecuteSql executeSql) {
        this.executeSql = executeSql;
    }
}