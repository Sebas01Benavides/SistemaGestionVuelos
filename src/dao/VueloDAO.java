package dao;
import modelo.Vuelo;
import modelo.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author sebas
 */


public class VueloDAO {
    /**
     * Obtiene una lista de todos los vuelos de la base de datos.
     * @return Una lista de objetos Vuelo.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<Vuelo> getVuelos() throws SQLException {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT id_vuelo, origen, destino, fechasalida FROM Vuelo";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                vuelo.setOrigen(rs.getString("origen"));
                vuelo.setDestino(rs.getString("destino"));
                // Convertir java.sql.Date a java.time.LocalDate
                vuelo.setFechaSalida(rs.getDate("fechasalida").toLocalDate()); 
                vuelos.add(vuelo);
            }
        }
        return vuelos;
    }
    
    /**
     * Crea una nueva reserva en la base de datos.
     * @param reserva El objeto Reserva a guardar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void crearReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO Reserva (FechaReserva, NumeroAsiento, EstadoReserva, ID_Vuelo, ID_Pasajero) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(reserva.getFechaReserva()));
            pstmt.setString(2, reserva.getNumeroAsiento());
            pstmt.setString(3, reserva.getEstadoReserva());
            pstmt.setInt(4, reserva.getIdVuelo());
            pstmt.setInt(5, reserva.getIdPasajero());

            pstmt.executeUpdate();
        }
    }
    
    public List<String> getAsientosDisponibles(int idVuelo) throws SQLException {
    List<String> asientosDisponibles = new ArrayList<>();
    // Suponemos que tienes una tabla Asiento con todos los asientos del avión.
    // O puedes generar los asientos de forma programática, por ejemplo: "1A", "1B", "1C", etc.
    // Por ahora, generemos una lista de ejemplo.
    List<String> todosLosAsientos = Arrays.asList("1A", "1B", "1C", "2A", "2B", "2C", "3A", "3B", "3C");

    // Lógica para obtener los asientos ocupados para el vuelo
    Set<String> asientosOcupados = new HashSet<>();
    String sql = "SELECT NumeroAsiento FROM Reserva WHERE ID_Vuelo = ?";

    try (Connection conn = ConexionBD.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, idVuelo);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                asientosOcupados.add(rs.getString("NumeroAsiento"));
            }
        }
    }

    // Compara la lista de todos los asientos con la de los asientos ocupados
    for (String asiento : todosLosAsientos) {
        if (!asientosOcupados.contains(asiento)) {
            asientosDisponibles.add(asiento);
        }
    }

    return asientosDisponibles;
}
    
    public List<Reserva> obtenerReservas() throws SQLException {
    List<Reserva> reservas = new ArrayList<>();
    String sql = "SELECT ID_Reserva, FechaReserva, NumeroAsiento, EstadoReserva, ID_Vuelo, ID_Pasajero FROM Reserva";

    try (Connection conn = ConexionBD.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(rs.getInt("ID_Reserva"));
            reserva.setFechaReserva(rs.getDate("FechaReserva").toLocalDate());
            reserva.setNumeroAsiento(rs.getString("NumeroAsiento"));
            reserva.setEstadoReserva(rs.getString("EstadoReserva"));
            reserva.setIdVuelo(rs.getInt("ID_Vuelo"));
            reserva.setIdPasajero(rs.getInt("ID_Pasajero"));
            reservas.add(reserva);
        }
    }
    return reservas;
}
    public void editarReserva(int idReserva, String nuevoAsiento, int nuevoIdPasajero) throws SQLException {
    String sql = "UPDATE Reserva SET NumeroAsiento = ?, ID_Pasajero = ? WHERE ID_Reserva = ?";
    
    try (Connection conn = ConexionBD.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, nuevoAsiento);
        pstmt.setInt(2, nuevoIdPasajero);
        pstmt.setInt(3, idReserva);
        
        pstmt.executeUpdate();
    }
}

public void cancelarReserva(int idReserva) throws SQLException {
    String sql = "DELETE FROM Reserva WHERE ID_Reserva = ?";
    
    try (Connection conn = ConexionBD.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, idReserva);
        pstmt.executeUpdate();
    }
}
}