package model.entidades;

import java.util.Date;

public class Vuelo {
    private String id;
    private String numeroVuelo;
    private String origenIATA;
    private String destinoIATA;
    private Date fechaHoraSalida;
    private Date fechaHoraLlegada;
    private double precioBase;
    private int asientosDisponibles;
    private String estado;
    
    public Vuelo() {}
    
    public Vuelo(String id, String numeroVuelo, String origenIATA, String destinoIATA,
                Date fechaHoraSalida, Date fechaHoraLlegada, double precioBase) {
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.origenIATA = origenIATA;
        this.destinoIATA = destinoIATA;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.precioBase = precioBase;
        this.asientosDisponibles = 150;
        this.estado = "PROGRAMADO";
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNumeroVuelo() { return numeroVuelo; }
    public void setNumeroVuelo(String numeroVuelo) { this.numeroVuelo = numeroVuelo; }
    
    public String getOrigenIATA() { return origenIATA; }
    public void setOrigenIATA(String origenIATA) { this.origenIATA = origenIATA; }
    
    public String getDestinoIATA() { return destinoIATA; }
    public void setDestinoIATA(String destinoIATA) { this.destinoIATA = destinoIATA; }
    
    public Date getFechaHoraSalida() { return fechaHoraSalida; }
    public void setFechaHoraSalida(Date fechaHoraSalida) { this.fechaHoraSalida = fechaHoraSalida; }
    
    public Date getFechaHoraLlegada() { return fechaHoraLlegada; }
    public void setFechaHoraLlegada(Date fechaHoraLlegada) { this.fechaHoraLlegada = fechaHoraLlegada; }
    
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
    
    public int getAsientosDisponibles() { return asientosDisponibles; }
    public void setAsientosDisponibles(int asientosDisponibles) { this.asientosDisponibles = asientosDisponibles; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return numeroVuelo + ": " + origenIATA + " → " + destinoIATA + " - $" + precioBase;
    }
}