package src.my.socket.server.info.user;

public class Traveler extends User{

    /**
     * @deprecated 游客
     * @param username
     * @param passworld
     */
    public Traveler(String username, String passworld) {
        super(username, passworld);
        setPermission(1);
    }
}
