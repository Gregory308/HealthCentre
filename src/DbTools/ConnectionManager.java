package DbTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection conn = null;

    public static Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcentre", "root", "MojeHasło1");
            }
        }
        catch(SQLException e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą danych");
        }
        return conn;
    }

    public static void freeConnection() {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Nie udało się zamknąć Connectiona");
        }

    }
}
