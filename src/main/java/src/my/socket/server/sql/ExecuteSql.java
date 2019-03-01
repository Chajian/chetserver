package src.my.socket.server.sql;

import src.my.socket.server.info.user.User;

import java.sql.SQLException;

public interface ExecuteSql {

    void add(String[] mark,Object[] values)throws SQLException;

    void delete(String where)throws SQLException;

    void find(String[] mark,String where)throws SQLException;

    void updata(String[] mark,Object[] values,String where)throws SQLException;

    void customize()throws SQLException;

    User getUser(String name,String passworld)throws SQLException;

    void WriteUser(User user)throws SQLException;

    void deleteUser(String name)throws SQLException;

    boolean isExist(Object mark,Object values)throws SQLException;

}
