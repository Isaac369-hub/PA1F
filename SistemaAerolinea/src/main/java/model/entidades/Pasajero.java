// En: src/model/entidades/Pasajero.java
package model.entidades;

import java.util.Date;

/**
 * Clase concreta que extiende Usuario para pasajeros
 * Demuestra principio de Liskov Substitution
 */
public class Pasajero extends Usuario {
    private String pasaporte;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String tipoPasajero; // "ADULTO", "NIÑO", "BEBE"
    
    public Pasajero() {
        super();
        this.setRol("PASAJERO");
    }
    
    public Pasajero(String id, String nombre, String email, String password,
                   String pasaporte, Date fechaNacimiento, String nacionalidad) {
        super(id, nombre, email, password, "PASAJERO");
        this.pasaporte = pasaporte;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.tipoPasajero = "ADULTO";
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Pasajero: " + getNombre() + 
                         ", Pasaporte: " + pasaporte + 
                         ", Nacionalidad: " + nacionalidad);
    }
    
    // Getters y Setters específicos de Pasajero
    public String getPasaporte() { return pasaporte; }
    public void setPasaporte(String pasaporte) { this.pasaporte = pasaporte; }
    
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    
    public String getTipoPasajero() { return tipoPasajero; }
    public void setTipoPasajero(String tipoPasajero) { this.tipoPasajero = tipoPasajero; }
}