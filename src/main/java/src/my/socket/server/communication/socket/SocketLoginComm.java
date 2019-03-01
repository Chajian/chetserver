package src.my.socket.server.communication.socket;

import src.my.socket.server.communication.Communication;
import src.my.socket.server.communication.LoginComm;
import src.my.socket.server.info.Tran;
import src.my.socket.server.info.user.User;
import src.my.socket.server.sql.CreateSql;
import src.my.socket.server.sql.ExecuteSql;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class SocketLoginComm implements LoginComm {

    private Communication communication;
    private ExecuteSql executeSql;

    public SocketLoginComm(Communication communication,ExecuteSql executeSql) {
        this.communication = communication;
        this.executeSql = executeSql;
    }

    //登录
    @Override
    public boolean login(Tran tran, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        if(tran.getObject() instanceof String[]){
            String[] info = (String[]) tran.getObject();
            try {
                if(executeSql.isExist("name", info[0])) {//检测是否有这个用户
                    User user = executeSql.getUser(info[0],info[1]);
                    user.setInputStream(inputStream);
                    user.setOutputStream(outputStream);
                    if(user != null && !communication.isLogin(info[0])){//用户不在线
                        communication.LoginUser(user,info[0]);//登录
                        return true;
                    }
                    else if(user != null && communication.isLogin(info[0])){//用户在线
                        return true;
                    }
                }
            }
            catch (SQLException e){
                System.out.println("数据库查询出现问题"+e.getMessage());
            }
        }
        return false;
    }


    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }
}
