package src.my.socket.server.sql.sqlite;

import src.my.socket.server.sql.CreateSql;

import java.io.File;
import java.sql.*;

/**
 * @deprecated 使用sqlite建立数据库
 */
public class CreateSqlLite implements CreateSql {

    private String classname = "org.sqlite.JDBC";

    private String sql_url = "jdbc:sqlite:E:\\Study\\demo\\java\\chetserver-master\\chetserver.db";

    private Connection connection;

    //建立数据库连接
    @Override
    public Connection createSql(String sql_name) {
        try {
            Class.forName(classname);
            connection = DriverManager.getConnection(sql_url);
            Statement state = connection.createStatement();
            if(!isExist(sql_name)) {
                state.executeUpdate("CREATE TABLE " + sql_name + "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "permission int," +
                        "name text," +
                        "passworld text," +
                        "photo mediumblob," +
                        "birthday date," +
                        "info text " +
                        ")");
            }
            state.close();
        }
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        System.out.println("sqlite数据库建立成功!");
        return connection;
    }

    public boolean isExist(String sql_name){
        File file = new File(sql_name+".db");
        if(file.exists()){
            return true;
        }
        return false;
    }

    public String getSql_url() {
        return sql_url;
    }

    public void setSql_url(String sql_url) {
        this.sql_url = sql_url;
    }

    public Connection getConnection() {
        return connection;
    }
}
