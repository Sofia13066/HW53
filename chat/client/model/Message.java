package chat.client.model;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable {
	String nickName;
	LocalTime time;
	String message;
    
    
    public Message(String nickName, LocalTime time, String message) {
        this.nickName = nickName;
        setTime(time);
        setMessage(message);
    }


    public void setTime(LocalTime time) {
        this.time = LocalTime.now();
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getNickName() {
        return nickName;
    }


    public LocalTime getTime() {
        return time;
    }


    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return nickName + ":  " + message + "                 " + time + "";
    }


}
