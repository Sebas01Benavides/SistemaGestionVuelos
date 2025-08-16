package dao;
import modelo.Pasajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author sebas
 */
public class PasajeroDAO {

    public int crearPasajero(Pasajero pasajero) throws SQLException {
        String sql = "INSERT INTO Pasajero (Nombre, Apellido, Email, Telefono) VALUES (?, ?, ?, ?)";
        int idGenerado = -1;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, pasajero.getNombre());
            pstmt.setString(2, pasajero.getApellido());
            pstmt.setString(3, pasajero.getEmail());
            pstmt.setString(4, pasajero.getTelefono());

            int filasAfectadas = pstmt.executeUpdate();

            // Si la inserción fue exitosa, recupera el ID generado
            if (filasAfectadas > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                    }
                }
            }
        }
        return idGenerado;
    }
}
