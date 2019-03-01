package src.my.socket.server.info.user;

import src.my.socket.server.info.Tran;

import java.awt.image.ImagingOpException;
import java.io.*;
import java.util.Date;

/**
 * @deprecated 用户信息
 */
public abstract class User implements Serializable{

    private int permission;
    private int id;
    private String username;
    private String passworld;
    private String info;//存储用户信息;
    private byte[] photo;//头像
    private Date date;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public User(String username, String passworld) {
        this.username = username;
        this.passworld = passworld;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void send(Tran tran){
        try {
            outputStream.writeObject(tran);
            outputStream.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPermission() {
        return permission;
    }

    protected void setPermission(int permission) {
        this.permission = permission;
    }
}
