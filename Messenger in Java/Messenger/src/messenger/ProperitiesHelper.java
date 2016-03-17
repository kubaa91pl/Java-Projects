package messenger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProperitiesHelper {
    private InputStream inputStream;

    public String getPort() throws IOException {
        try {
            Properties properties = new Properties();
            
            try {
                properties.load(new FileInputStream("resources/messenger.properties"));
                return properties.getProperty("port");
            }
            catch (IOException e) {
                System.out.println("Could not open Config file");
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        
        return null;
    }
}