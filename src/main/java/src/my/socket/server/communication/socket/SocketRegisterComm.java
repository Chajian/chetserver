package src.my.socket.server.communication.socket;

import src.my.socket.server.communication.Communication;
import src.my.socket.server.communication.RegisterComm;
import src.my.socket.server.info.user.User;
import src.my.socket.server.sql.ExecuteSql;

import java.sql.SQLException;

public class SocketRegisterComm implements RegisterComm {

    ExecuteSql executeSql;
    Communication communication;

    public SocketRegisterComm(ExecuteSql executeSql,Communication communication) {
        this.executeSql = executeSql;
        this.communication = communication;
    }

    //注册
    @Override
    public boolean register(User user) {
        System.out.println(user.getUsername()+"尝试注册");
        if(executeSql==null)
            System.out.println("execu是空的");
        try {
            if (!executeSql.isExist("name", user.getUsername())) {
                executeSql.WriteUser(user);
                System.out.println("注册成功!");
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //删除账号
    @Override
    public boolean deleteAccount(String name) {
        try {
            if (executeSql.isExist("name", name)) {
                executeSql.deleteUser(name);//删除用户
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ExecuteSql getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(ExecuteSql executeSql) {
        this.executeSql = executeSql;
    }
}
