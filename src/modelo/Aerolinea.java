package modelo;
/**
 *
 * @author sebas
 */
public class Aerolinea {
    private int idAerolinea;
    private String nombreAerolinea;

    // Constructor vacío (útil para frameworks)
    public Aerolinea() {
    }
    
    // Constructor completo
    public Aerolinea(int idAerolinea, String nombreAerolinea) {
        this.idAerolinea = idAerolinea;
        this.nombreAerolinea = nombreAerolinea;
    }

    // Getters y Setters
    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }
}