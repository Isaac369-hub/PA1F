// En: src/model/entidades/Aeropuerto.java
package model.entidades;

/**
 * Representa un aeropuerto en el sistema
 * Demuestra Single Responsibility Principle
 */
public class Aeropuerto {
    private String codigoIATA;
    private String nombre;
    private String ciudad;
    private String pais;
    private int terminales;
    
    public Aeropuerto() {}
    
    public Aeropuerto(String codigoIATA, String nombre, String ciudad, String pais) {
        this.codigoIATA = codigoIATA;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.terminales = 1;
    }
    
    // Getters y Setters
    public String getCodigoIATA() { return codigoIATA; }
    public void setCodigoIATA(String codigoIATA) { this.codigoIATA = codigoIATA; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    
    public int getTerminales() { return terminales; }
    public void setTerminales(int terminales) { this.terminales = terminales; }
    
    @Override
    public String toString() {
        return codigoIATA + " - " + nombre + " (" + ciudad + ", " + pais + ")";
    }
}