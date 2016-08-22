package messenger;

public class User {
    public User(int id, String displayName){
        this.id = id;
        this.displayName = displayName;
    }
    
    
    
    @Override
    public String toString(){
        return this.displayName;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getDisplayName(){
        return this.displayName;
    }
    
    
        
    private int id;
    private String displayName;
}