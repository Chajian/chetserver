package src.my.socket.server;

import src.my.socket.server.communication.Communication;
import src.my.socket.server.communication.socket.SocketCommunication;
import src.my.socket.server.sql.Sql;
import src.my.socket.server.sql.sqlite.SqlLite;

import java.io.IOException;

public class Server {
    private static Sql sqlite;
    private static Communication communication;

    public static void main(String[] args){
        sqlite = new SqlLite();
        communication = new SocketCommunication(sqlite.getExecuteSql());

    }

    public static void StartComm()throws Exception{
        Thread.sleep(1);
        if(sqlite.getExecuteSql()!=null){
            communication = new SocketCommunication(sqlite.getExecuteSql());
            System.out.println("socket通讯成功");
        }
        else {
            StartComm();
            if(sqlite.getCreate()==null)
                System.out.println("Create是空的");
            if(sqlite.getExecuteSql()==null)
                System.out.println("execute是空的");
        }
    }

}
