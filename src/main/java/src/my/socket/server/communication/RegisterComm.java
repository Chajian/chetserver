package src.my.socket.server.communication;

import src.my.socket.server.info.user.User;

public interface RegisterComm {


    boolean register(User user);

    boolean deleteAccount(String name);

}
