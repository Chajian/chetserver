package src.my.socket.server.sql;

public abstract class Sql {
    protected CreateSql create;
    protected ExecuteSql executeSql;


    public CreateSql getCreate() {
        return create;
    }

    public void setCreate(CreateSql create) {
        this.create = create;
    }

    public ExecuteSql getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(ExecuteSql executeSql) {
        this.executeSql = executeSql;
    }
}
