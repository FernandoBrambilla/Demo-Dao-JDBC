
package DataBaseConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
  
    //close statement
    public static void closeStatement(Statement stmt){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }
    
      //close resultSet
    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
    }
    
    
   
}
