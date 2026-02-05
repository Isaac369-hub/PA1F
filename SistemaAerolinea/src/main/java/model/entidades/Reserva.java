package model.entidades;

import java.util.Date;
import java.util.List;

public class Reserva {

    private String id;
    private Vuelo vuelo;
    private Usuario usuario;
    private List<Pasajero> pasajeros;
    private Date fechaReserva;
    private double precioTotal;
    private String estado;
    private String clase;
    private String codigoReserva;
    private int cantidadAsientos;
    private List<String> asientos;
    private String metodoPago;
    
    public Reserva() {}

    public Reserva(String id, Vuelo vuelo, Usuario usuario,
                   List<Pasajero> pasajeros,
                   double precioTotal, String clase,
                   int cantidadAsientos, List<String> asientos, String metodoPago) {

        this.id = id;
        this.vuelo = vuelo;
        this.usuario = usuario;
        this.pasajeros = pasajeros;
        this.fechaReserva = new Date();
        this.precioTotal = precioTotal;
        this.estado = "CONFIRMADA";
        this.clase = clase;
        this.codigoReserva = "RES" + System.currentTimeMillis() % 10000;
        this.cantidadAsientos = cantidadAsientos;
        this.asientos = asientos;
        this.metodoPago = metodoPago;
    }

    // GETTERS Y SETTERS

    public String getId() { return id; }
    public Vuelo getVuelo() { return vuelo; }
    public Usuario getUsuario() { return usuario; }
    public List<Pasajero> getPasajeros() { return pasajeros; }
    public Date getFechaReserva() { return fechaReserva; }
    public double getPrecioTotal() { return precioTotal; }
    public String getEstado() { return estado; }
    public String getClase() { return clase; }
    public String getCodigoReserva() { return codigoReserva; }
    public int getCantidadAsientos() { return cantidadAsientos; }
    public List<String> getAsientos() { return asientos; }
    public String getMetodoPago() {return metodoPago;}
    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAsientosTexto() {
        if (asientos == null || asientos.isEmpty()) {
            return "No asignados";
        }
        return String.join(", ", asientos);
    }

    @Override
    public String toString() {
        return "Reserva " + codigoReserva + " - " +
               vuelo.getNumeroVuelo() + " - $" + precioTotal;
    }
}