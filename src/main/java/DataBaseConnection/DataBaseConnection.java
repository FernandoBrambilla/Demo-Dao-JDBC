
package DataBaseConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    
    private static Connection connection= null;
    
    //get properties dataBase
    private static Properties loadProperties(){
        try(FileInputStream file = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(file);
            return properties;
        }
        catch (IOException e){
            throw new DataBaseException(e.getMessage());   
        }     
    }
     
    //oppen connection dataBase
    public static Connection getConnection(){
        if(connection == null){
            try{
                Properties properties= loadProperties();
                String url = properties.getProperty("dbUrl");
                connection= DriverManager.getConnection(url);
            }
            catch(SQLException e){
                throw new DataBaseException(e.getMessage());
            }
        }
        return connection;
    }
    
    //close connection dataBase
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }
            catch(SQLException e){
                throw new DataBaseException(e.getMessage());
            }          
        }
    }
    
    
   
}
