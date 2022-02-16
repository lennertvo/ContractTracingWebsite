package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnectionManager {
    public Connection connection;
    private static DBconnectionManager dBconnectionManager_instance = null;

    private DBconnectionManager(String dburl){
        Properties dbProperties = new Properties();
        try {
            Class.forName("util.Secret"); // implementation of abstract class credentials;
            Secret.setPass(dbProperties);

        }
        catch (ClassNotFoundException e) {
            System.out.println("Class Secret with crendentials not found");
        }

        dbProperties.setProperty("ssl", "true");
        dbProperties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        //dbProperties.setProperty("sslmode", "true");
        dbProperties.setProperty("sslmode", "require");


        try {
            System.out.println("connection to database ...");
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dburl, dbProperties);

            System.out.println("done");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getException().getMessage());
        }
        catch (SQLException e){
            System.out.println("connection troubles");
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
    }

    public static DBconnectionManager getInstance(String dbURL){
        if(dBconnectionManager_instance == null) {
            dBconnectionManager_instance = new DBconnectionManager(dbURL);
        }
        return dBconnectionManager_instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
