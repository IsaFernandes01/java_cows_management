package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private static final String url = "jdbc:mysql://localhost:3306/vaquinhas";
    private static final String user = "root";
    private static final String password = "2008@Isadora";

    private static Connection conn;

    public static Connection getConn() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }
        return conn;
    }
}
