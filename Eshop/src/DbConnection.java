package src;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import sun.rmi.runtime.Log;


public class DbConnection{
    protected static Connection initiallizeDatabase() throws SQLException, ClassNotFoundException{
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/Eshop";
        
        
        String dbName = "productstore";
        String dbUsername = "root";
        String dbPassword = "root_pass";

        Class.forName(dbDriver); 
        Connection conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        
        return conn;
    }
}