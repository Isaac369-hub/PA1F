package model.servicios;

import model.entidades.Vuelo;
import model.entidades.Aeropuerto;
import java.util.Date;
import java.util.List;

public interface IVueloService {
    List<Vuelo> buscarVuelosDisponibles(String origen, String destino, 
                                       Date fecha, int pasajeros);
    Vuelo obtenerVueloPorId(String vueloId);
    List<Aeropuerto> obtenerTodosAeropuertos();
    boolean verificarDisponibilidadAsientos(String vueloId, int pasajeros);
    List<Vuelo> obtenerVuelosRecomendados(String usuarioId);
}