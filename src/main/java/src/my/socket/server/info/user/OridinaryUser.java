package src.my.socket.server.info.user;

/**
 * @deprecated 普通玩家
 * 权限: 5
 */
public class OridinaryUser extends User{
    public OridinaryUser(String username, String passworld) {
        super(username, passworld);
        setPermission(5);
    }
}
