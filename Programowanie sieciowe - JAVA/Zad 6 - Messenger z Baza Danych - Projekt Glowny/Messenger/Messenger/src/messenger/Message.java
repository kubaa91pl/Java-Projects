package messenger;

import java.sql.Timestamp;

public class Message {
    public Message(int from, int to, Timestamp date, String message){
        this.from = from;
        this.to = to;
        this.date = date;
        this.message = message;
    }
    
    
    
    public int getFrom(){
        return from;
    }
    
    public int getTo(){
        return to;
    }
    
    public Timestamp getDate(){
        return date;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getFromDisplayName() {
        return fromDisplayName;
    }
    
    public void setFromDisplayName(String fromDisplayName) {
        this.fromDisplayName = fromDisplayName;
    }
    
    
    private int from;
    private int to;
    private Timestamp date;
    private String message;
    private String fromDisplayName;
}