package src.my.socket.server.info.user;


/**
 * @deprecated 管理员
 * 权限:9
 */
public class Admin extends User{

    public Admin(String username, String passworld) {
        super(username, passworld);
        setPermission(9);
    }
}
