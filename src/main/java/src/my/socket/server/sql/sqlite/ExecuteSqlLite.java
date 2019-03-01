package src.my.socket.server.sql.sqlite;

import src.my.socket.server.info.user.Admin;
import src.my.socket.server.info.user.OridinaryUser;
import src.my.socket.server.info.user.Traveler;
import src.my.socket.server.info.user.User;
import src.my.socket.server.sql.ExecuteSql;

import java.io.IOException;
import java.sql.*;

public class ExecuteSqlLite implements ExecuteSql {

    private Connection connection;

    private String table_name;

    private PreparedStatement statement;

    public ExecuteSqlLite(){
    }

    public ExecuteSqlLite(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(String[] mark, Object[] values)throws SQLException{
        if(mark.length==values.length&&mark.length>0) {
            String string_mark = "";
            String string_values = "";
            for(int i = 1 ; i <= mark.length ; i++){
                if(i<mark.length) {
                    string_mark += mark[i-1] + ",";
                    string_values += "?,";
                }
                else {
                    string_mark += mark[i-1];
                    string_values += "?";
                }
            }
            String sql = "INSERT INTO "+table_name+" ("+string_mark+") values ("+string_values+")";
            statement = connection.prepareStatement(sql);
            for(int i = 0 ; i < mark.length ; i++){
                statement.setObject(i+1,values[i]);
            }
            statement.execute();
            statement.close();
        }
    }

    @Override
    public void delete(String where) throws SQLException{
        if(where!=null){
            statement = connection.prepareStatement("DELETE FROM "+table_name+where);
            statement.execute();
            statement.close();
        }
    }

    @Override
    public void find(String[] mark, String where) throws SQLException{
        String string_mark = "";
        if(mark.length>0||where!=null){
            for(int i = 0 ; i < mark.length ; i++){
                if(i<mark.length){
                    string_mark += mark[i]+",";
                }
                else{
                    string_mark += mark[i];
                }
            }
            statement = connection.prepareStatement("SELECT "+string_mark+" FROM "+table_name+" "+where);
            statement.execute();
            statement.close();
        }
    }

    @Override
    public void updata(String[] mark, Object[] values, String where)throws SQLException {
        if(mark.length == values.length&&where!=null) {
            String string_set = "";
            for(int i = 1 ; i<=mark.length ; i++){
                if(i<mark.length)
                    string_set += mark[i-1]+" = ?,";
                else
                    string_set += mark[i-1]+" = ?";
            }
            statement = connection.prepareStatement("UPDATE " + table_name + " SET " +string_set+where);

            for(int i = 0 ; i < mark.length ;i++){
                statement.setObject(i+1,values[i]);
            }

            statement.execute();
            statement.close();
        }
    }

    //通过用户名删除
    @Override
    public void deleteUser(String name) throws SQLException {
        String where = "WHERE name = "+name;
        delete(where);
    }

    @Override
    public boolean isExist(Object mark, Object values)throws SQLException {

        if(mark!=null&&values!=null){
            statement = connection.prepareStatement("select id from "+table_name+" WHERE "+(String) mark+"= ?");
            statement.setString(1,(String)values);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public User getUser(String name,String passworld)throws SQLException{
        if(name!=null){
            statement = connection.prepareStatement("SELECT * FROM "+table_name+" WHERE name = ? and passworld = ?");
            statement.setString(1,name);
            statement.setString(2,passworld);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = null;
                int permission = resultSet.getInt("permission");
                String username = resultSet.getString("name");
                String userpass = resultSet.getString("passworld");
                switch (permission){
                    case 1:
                        user = new Traveler(username,userpass);
                        break;

                    case 5:
                        user = new OridinaryUser(username,userpass);
                        break;

                    case 9:
                        user = new Admin(username,userpass);
                        break;
                }
                user.setPhoto(resultSet.getBytes("photo"));
                user.setInfo(resultSet.getString("info"));
                user.setId(resultSet.getInt("id"));
                return user;
            }
        }
        return null;
    }

    //**将用户信息写入数据库
    @Override
    public void WriteUser(User user) throws SQLException {
        String[] mark = {"permission","name","passworld","info","photo","birthday"};
        Object[] values = {user.getPermission(),user.getUsername(),user.getPassworld(),user.getInfo(),user.getPhoto(),user.getDate()};
        add(mark,values);
    }

    @Override
    public void customize() {

    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getSql_name() {
        return table_name;
    }

    public void setSql_name(String sql_name) {
        this.table_name = sql_name;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }
}
