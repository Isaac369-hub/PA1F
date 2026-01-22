package model.entidades;

import java.util.Date;
import java.util.List;

public class Reserva {
    private String id;
    private Vuelo vuelo;
    private Usuario usuario;
    private List<Pasajero> pasajeros;
    private List<String> asientos;
    private Date fechaReserva;
    private double precioTotal;
    private String estado; // "CONFIRMADA", "CANCELADA", "CHECKIN", "EMBARCADA"
    private String clase; // "ECONOMICA", "BUSINESS", "PRIMERA"
    private String codigoReserva;
    
    public Reserva() {}
    
    public Reserva(String id, Vuelo vuelo, Usuario usuario, List<Pasajero> pasajeros,
                  List<String> asientos, double precioTotal, String clase) {
        this.id = id;
        this.vuelo = vuelo;
        this.usuario = usuario;
        this.pasajeros = pasajeros;
        this.asientos = asientos;
        this.fechaReserva = new Date();
        this.precioTotal = precioTotal;
        this.estado = "CONFIRMADA";
        this.clase = clase;
        this.codigoReserva = generarCodigoReserva();
    }
    
    private String generarCodigoReserva() {
        return "RES" + System.currentTimeMillis() % 10000;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public List<Pasajero> getPasajeros() { return pasajeros; }
    public void setPasajeros(List<Pasajero> pasajeros) { this.pasajeros = pasajeros; }
    
    public List<String> getAsientos() { return asientos; }
    public void setAsientos(List<String> asientos) { this.asientos = asientos; }
    
    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }
    
    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }
    
    public String getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }
    
    public int getCantidadPasajeros() {
        return pasajeros != null ? pasajeros.size() : 0;
    }
}