package crud.connexion;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Connexion {
    public static Connection getConnection() {
 
        Connection dbConnection = null;
 
        String URL = "jdbc:mysql://localhost:3306/test";
        String USER = "root";
        String PASSWORD = "root";
        String DRIVER = "com.mysql.jdbc.Driver";
 
        try {
 
            Class.forName(DRIVER);
            dbConnection= DriverManager.getConnection(URL, USER, PASSWORD);
 
        } catch (SQLException e) { 
 
            e.printStackTrace();
 
        }catch(ClassNotFoundException cnfe){
 
           cnfe.printStackTrace();
           System.exit(-1);
 
       }
 
        return dbConnection;
    }
}