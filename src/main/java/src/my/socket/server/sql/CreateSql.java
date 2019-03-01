package src.my.socket.server.sql;

import java.sql.Connection;

public interface CreateSql {

    Connection createSql(String sql_name);

}
