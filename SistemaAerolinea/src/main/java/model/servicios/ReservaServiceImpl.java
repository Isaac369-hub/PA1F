package model.servicios;

import model.entidades.Reserva;
import model.entidades.Vuelo;
import model.entidades.Usuario;
import model.entidades.Pasajero;
import java.util.ArrayList;
import java.util.List;

public class ReservaServiceImpl implements IReservaService {
    private List<Reserva> reservas;
    private IVueloService vueloService;
    
    public ReservaServiceImpl() {
        this.reservas = new ArrayList<>();
        this.vueloService = new VueloServiceImpl();
    }
    
    @Override
    public Reserva crearReserva(Vuelo vuelo, Usuario usuario, 
                               List<Usuario> pasajeros, List<String> asientos) {
        // Convertir usuarios a pasajeros
        List<Pasajero> pasajerosList = new ArrayList<>();
        for (Usuario u : pasajeros) {
            if (u instanceof Pasajero) {
                pasajerosList.add((Pasajero) u);
            }
        }
        
        // Calcular precio (precio base * cantidad de pasajeros)
        double precioTotal = vuelo.getPrecioBase() * pasajeros.size();
        
        // Crear reserva
        String reservaId = "RES" + String.format("%03d", reservas.size() + 1);
        Reserva reserva = new Reserva(reservaId, vuelo, usuario, pasajerosList, 
                                     precioTotal, "ECONOMICA");
        
        // Actualizar disponibilidad de asientos
        vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - pasajeros.size());
        
        // Guardar reserva
        reservas.add(reserva);
        
        return reserva;
    }
    
    @Override
    public boolean cancelarReserva(String reservaId) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getId().equals(reservaId)) {
                Reserva reserva = reservas.get(i);
                reserva.setEstado("CANCELADA");
                
                // Liberar asientos
                Vuelo vuelo = reserva.getVuelo();
                vuelo.setAsientosDisponibles(
                    vuelo.getAsientosDisponibles() + reserva.getCantidadPasajeros()
                );
                
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean modificarReserva(String reservaId, List<String> nuevosAsientos) {
        // Por ahora solo marcamos como modificada
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(reservaId)) {
                reserva.setEstado("MODIFICADA");
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Reserva obtenerReservaPorId(String reservaId) {
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(reservaId)) {
                return reserva;
            }
        }
        return null;
    }
    
    @Override
    public List<Reserva> obtenerReservasPorUsuario(String usuarioId) {
        List<Reserva> reservasUsuario = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getUsuario().getId().equals(usuarioId)) {
                reservasUsuario.add(reserva);
            }
        }
        return reservasUsuario;
    }
    
    @Override
    public double calcularPrecioTotal(Vuelo vuelo, int numPasajeros, String clase) {
        double precioBase = vuelo.getPrecioBase();
        
        // Ajustar según clase
        switch (clase.toUpperCase()) {
            case "BUSINESS":
                precioBase *= 1.5;
                break;
            case "PRIMERA":
                precioBase *= 2.0;
                break;
            // ECONOMICA mantiene precio base
        }
        
        return precioBase * numPasajeros;
    }
    
    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }
}