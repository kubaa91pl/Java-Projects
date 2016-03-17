package messenger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.BIT;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DatabaseHelper {
    public static ArrayList<User> GetContects(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<User> result = new ArrayList<User>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM messenger.user WHERE Id != " + SessionContext.getCurrentUserId() + ";");

            while(resultSet.next()){
                User user = new User(resultSet.getInt(1), resultSet.getString(2) + " " + resultSet.getString(3));
                result.add(user);
            }
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            }
            catch (SQLException ex) {
                
            }
        }
        
        return result;
    }
    
    public static ArrayList<Message> GetMessage(int contactId){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Message> result = new ArrayList<Message>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            
            PreparedStatement st = connection.prepareCall("{call get_messages(?, ?)}");
            st.setInt(1, SessionContext.getCurrentUserId());
            st.setInt(2, contactId);
            resultSet = st.executeQuery();
            
//            resultSet = statement.executeQuery(
//                "SELECT * FROM messenger.message" +
//                " WHERE FromID = " + SessionContext.getCurrentUserId() +
//                " AND ToID = " + contactId +
//                " OR FromID = " + contactId +
//                " AND ToID = " + SessionContext.getCurrentUserId() +
//                " ORDER BY CreateDate");

            while(resultSet.next()){
                Message message = new Message(resultSet.getInt(1), resultSet.getInt(2), resultSet.getTimestamp(3), resultSet.getString(4));
                message.setFromDisplayName(resultSet.getString(5));
                result.add(message);
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                MainWindow.getInstance(),
                ex.getMessage(),
                "Błąd",
                JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            }
            catch (SQLException ex) {
                
            }
        }
        
        return result;
    }
    
    public static boolean ChangePassword(String oldPassword, String newPassword){
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        
        boolean result = false;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            
            statement = connection.prepareCall("{call set_password(?, ?, ?, ?)}");
            statement.setInt(1, SessionContext.getCurrentUserId());
            statement.setString(2, oldPassword);
            statement.setString(3, newPassword);
            statement.registerOutParameter(4, BIT);
            resultSet = statement.executeQuery();
            
            result = statement.getBoolean(4);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    MainWindow.getInstance(),
                    ex.getMessage(),
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            }
            catch (SQLException ex) {
                
            }
        }
        
        return result;
    }
    
    public static User GetUser(int userId){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM messenger.user WHERE Id = " + userId + ";");
            resultSet.next();
            
            user = new User(resultSet.getInt(1), resultSet.getString(2) + " " + resultSet.getString(3));
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            }
            catch (SQLException ex) {
                
            }
        }
        
        return user;
    }
    
    
    
    private static String url = "jdbc:mysql://localhost:3306/messenger";
    private static String user = "root";
    private static String password = "Kuba123Nizynski123";
}