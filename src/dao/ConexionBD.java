package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author sebas
 */
public class ConexionBD { 
    private static Connection conexion;
    private static final String URL = "jdbc:postgresql://localhost:5432/bd_vuelos";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    private ConexionBD() {
        // Constructor privado
    }

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver"); 
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver no encontrado.");
                throw new SQLException("Driver not found.", e);
            }
        }
        return conexion;
    }

    public static void closeConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}