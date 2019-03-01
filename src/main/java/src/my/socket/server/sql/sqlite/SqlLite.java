package src.my.socket.server.sql.sqlite;

import src.my.socket.server.sql.CreateSql;
import src.my.socket.server.sql.ExecuteSql;
import src.my.socket.server.sql.Sql;

public class SqlLite extends Sql{


    public SqlLite(){
//        sql = new CreateSqlLite();
//        execu = new ExecuteSqlLite(sql.createSql("ChetServer"));
        create = new CreateSqlLite();
        executeSql = new ExecuteSqlLite(create.createSql("chetserver"));
        ((ExecuteSqlLite)executeSql).setSql_name("chetserver");
    }

}
