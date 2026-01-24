package model.dao;

import model.entidades.*;
import java.util.List;

public interface IDataLoader {
    List<Usuario> cargarUsuarios(String rutaArchivo);
    List<Aeropuerto> cargarAeropuertos(String rutaArchivo);
    List<Avion> cargarAviones(String rutaArchivo);
    List<Vuelo> cargarVuelos(String rutaArchivo);
    void guardarReserva(Reserva reserva, String rutaArchivo);
}