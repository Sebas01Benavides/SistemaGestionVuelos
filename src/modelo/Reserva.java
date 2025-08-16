package modelo;
import java.time.LocalDate;

/**
 *
 * @author sebas
 */

public class Reserva {
    private int idReserva;
    private LocalDate fechaReserva;
    private String numeroAsiento;
    private String estadoReserva;
    private int idVuelo;
    private int idPasajero;

    public Reserva(int idReserva, LocalDate fechaReserva, String numeroAsiento, String estadoReserva, int idVuelo, int idPasajero) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.numeroAsiento = numeroAsiento;
        this.estadoReserva = estadoReserva;
        this.idVuelo = idVuelo;
        this.idPasajero = idPasajero;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public int getIdPasajero() {
        return idPasajero;
    }
    
    
    
    
}
