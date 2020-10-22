package util;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionService {
    private static Connection dbConnection;
    private static String schema;

    public static String getSchema() {
        return schema;
    }

    public static Connection getDbConnection() {
        return dbConnection;
    }

    public static void connect(String dburl, String searchPath){
        schema = searchPath;
        DBconnectionManager connectionManager = DBconnectionManager.getInstance(dburl);
        dbConnection = connectionManager.getConnection();
    }

    public static void disconnect(){
        try {
            dbConnection.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
}
