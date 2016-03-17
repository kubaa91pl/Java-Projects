package messenger;

public class SessionContext {
    public static int getCurrentUserId(){
        return currentUserId;
    }
    
    public static void setCurrentUserId(int currentUserId){
        SessionContext.currentUserId = currentUserId;
    }
    
    public static int getSelectedContactId(){
        return selectedContactId;
    }
    
    public static void setSelectedContactId(int selectedContactId){
        SessionContext.selectedContactId = selectedContactId;
    }
    
    public static String getIpAddress(){
        return ipAddress;
    }
    
    public static void setIpAddress(String ipAddress){
        SessionContext.ipAddress = ipAddress;
    }
    
    public static int getPort(){
        return port;
    }
    
    public static void setPort(int port){
        SessionContext.port = port;
    }
    
    
    
    private static int currentUserId;
    private static int selectedContactId;
    private static String ipAddress;
    private static int port;
}