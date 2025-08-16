package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
/**
 *
 * @author sebas
 */


public class VueloDAO {

    public List<Vuelo> getVuelos() throws SQLException {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM Vuelo";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                vuelo.setNumeroVuelo(rs.getString("numerovuelo"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                // Conversión de java.sql.Date a java.time.LocalDate
                vuelo.setFechaSalida(rs.getDate("fechasalida").toLocalDate()); 
                
                vuelos.add(vuelo);
            }
        }
        return vuelos;
    }
    
    public void crearReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO Reserva (id_vuelo, id_pasajero, fechareserva, numeroasiento, estadoreserva) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, reserva.getIdVuelo());
            pstmt.setInt(2, reserva.getIdPasajero());
            pstmt.setDate(3, java.sql.Date.valueOf(reserva.getFechaReserva()));
            pstmt.setString(4, reserva.getNumeroAsiento());
            pstmt.setString(5, reserva.getEstadoReserva());

            pstmt.executeUpdate();
        }
    }
}
