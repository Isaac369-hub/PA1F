package model.entidades;

import java.util.Date;

public class Pasajero extends Usuario {
    private String pasaporte;
    private Date fechaNacimiento;
    private String nacionalidad;
    
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
    }
    
    @Override
    public void mostrarInformacion() {
        System.out.println("Pasajero: " + getNombre() + " - " + pasaporte);
    }
    
    // Getters y Setters
    public String getPasaporte() { return pasaporte; }
    public void setPasaporte(String pasaporte) { this.pasaporte = pasaporte; }
    
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
}