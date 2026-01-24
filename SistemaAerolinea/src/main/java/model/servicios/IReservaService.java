package model.servicios;

import model.entidades.Reserva;
import model.entidades.Vuelo;
import model.entidades.Usuario;
import java.util.List;

public interface IReservaService {
    Reserva crearReserva(Vuelo vuelo, Usuario usuario, 
                        List<Usuario> pasajeros, List<String> asientos);
    boolean cancelarReserva(String reservaId);
    boolean modificarReserva(String reservaId, List<String> nuevosAsientos);
    Reserva obtenerReservaPorId(String reservaId);
    List<Reserva> obtenerReservasPorUsuario(String usuarioId);
    double calcularPrecioTotal(Vuelo vuelo, int numPasajeros, String clase);
}