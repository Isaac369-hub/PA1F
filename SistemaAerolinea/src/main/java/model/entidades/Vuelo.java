package model.entidades;

import java.util.Date;

public class Vuelo {
    private String id;
    private String numeroVuelo;
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Date fechaHoraSalida;
    private Date fechaHoraLlegada;
    private Avion avion;
    private double precioBase;
    private int asientosDisponibles;
    private String estado; // "PROGRAMADO", "EN_VUELO", "CANCELADO", "REALIZADO"
    
    public Vuelo() {}
    
    public Vuelo(String id, String numeroVuelo, Aeropuerto origen, Aeropuerto destino,
                Date fechaHoraSalida, Date fechaHoraLlegada, double precioBase) {
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.precioBase = precioBase;
        this.asientosDisponibles = 150; // Valor por defecto
        this.estado = "PROGRAMADO";
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNumeroVuelo() { return numeroVuelo; }
    public void setNumeroVuelo(String numeroVuelo) { this.numeroVuelo = numeroVuelo; }
    
    public Aeropuerto getOrigen() { return origen; }
    public void setOrigen(Aeropuerto origen) { this.origen = origen; }
    
    public Aeropuerto getDestino() { return destino; }
    public void setDestino(Aeropuerto destino) { this.destino = destino; }
    
    public Date getFechaHoraSalida() { return fechaHoraSalida; }
    public void setFechaHoraSalida(Date fechaHoraSalida) { this.fechaHoraSalida = fechaHoraSalida; }
    
    public Date getFechaHoraLlegada() { return fechaHoraLlegada; }
    public void setFechaHoraLlegada(Date fechaHoraLlegada) { this.fechaHoraLlegada = fechaHoraLlegada; }
    
    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }
    
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
    
    public int getAsientosDisponibles() { return asientosDisponibles; }
    public void setAsientosDisponibles(int asientosDisponibles) { this.asientosDisponibles = asientosDisponibles; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public double calcularDuracionHoras() {
        if (fechaHoraSalida != null && fechaHoraLlegada != null) {
            long diffMs = fechaHoraLlegada.getTime() - fechaHoraSalida.getTime();
            return diffMs / (1000.0 * 60 * 60); // Convertir a horas
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return numeroVuelo + " | " + origen.getCodigoIATA() + " → " + destino.getCodigoIATA() + 
               " | " + fechaHoraSalida;
    }
}