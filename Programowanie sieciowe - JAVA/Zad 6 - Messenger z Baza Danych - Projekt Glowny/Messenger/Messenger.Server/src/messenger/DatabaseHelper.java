package messenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.UUID;

public class DatabaseHelper {
    public static Boolean IsUserWithLoginAndPassword(String login, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Boolean result = false;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM messenger.user WHERE Login = '" + login + "' AND Password = '" + password + "';");
            result = resultSet.isBeforeFirst();
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
    
    public static int getUserId(String login, String password){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int result = -1;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT Id FROM messenger.user WHERE Login = '" + login + "' AND Password = '" + password + "';");
            resultSet.next();
            result = resultSet.getInt(1);
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
    
    static boolean addMessage(int from, int to, Timestamp date, String message) {
        Connection connection = null;
        Statement statement = null;
        int result = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            String sql = "INSERT INTO `messenger`.`msg` (`FromID`, `ToID`, `CreateDate`, `Message`) VALUES (" + from + ", " + to + ", '" + date + "', '" + message + "');";
            result = statement.executeUpdate(sql);
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
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
        
        return result == 1;
    }
    
    static boolean addSession(UUID id, int userId, String ipAddress, int port) {
        Connection connection = null;
        Statement statement = null;
        int result = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            String sql = "INSERT INTO `messenger`.`session` (`Id`, `User`, `IpAddress`, `Port`) VALUES ('" + id + "', " + userId + ", '" + ipAddress + "', " + port + ");";
            result = statement.executeUpdate(sql);
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
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
        
        return result == 1;
    }
    
    static boolean removeSession(int userId) {
        Connection connection = null;
        Statement statement = null;
        int result = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            String sql = "DELETE FROM `messenger`.`session` WHERE User =" + userId + ";";
            result = statement.executeUpdate(sql);
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
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
        
        return result == 1;
    }
    
    static int getPort(int userId) {
        Connection connection = null;
        Statement statement = null;
        int result = -1;
        ResultSet resultSet = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    DatabaseHelper.url,
                    DatabaseHelper.user,
                    DatabaseHelper.password);
            statement = connection.createStatement();
            String sql = "SELECT Port FROM `messenger`.`session` WHERE User =" + userId + ";";
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            result = resultSet.getInt(1);
        }
        catch (SQLException ex) {
            
        }
        catch (ClassNotFoundException ex) {
            
        }
        finally {
            try {
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
    
    
    
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user = "root";
    private static String password = "Kuba123Nizynski123";
}