package src.my.socket.server.info;

import java.io.Serializable;

/**
 * @deprecated 数据包
 */
public class Tran implements Serializable{

    private static final long serialVersionUID = 1L;
    private Object object;
    private ObjectInfo objectInfo;
    private String sendTime;
    private String sendName;
    private String receiveName;

    public Tran() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ObjectInfo getObjectInfo() {
        return objectInfo;
    }

    public void setObjectInfo(ObjectInfo objectInfo) {
        this.objectInfo = objectInfo;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
