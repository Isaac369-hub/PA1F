package model.servicios;

import model.entidades.Vuelo;
import model.entidades.Aeropuerto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VueloServiceImpl implements IVueloService {
    private List<Vuelo> vuelos;
    private List<Aeropuerto> aeropuertos;
    
    public VueloServiceImpl() {
        this.vuelos = new ArrayList<>();
        this.aeropuertos = new ArrayList<>();
        cargarDatosDemo();
    }
    
    private void cargarDatosDemo() {
        // Aeropuertos
        aeropuertos.add(new Aeropuerto("MEX", "Aeropuerto Internacional Ciudad de México", 
                                       "Ciudad de México", "México"));
        aeropuertos.add(new Aeropuerto("JFK", "Aeropuerto Internacional John F. Kennedy", 
                                       "Nueva York", "USA"));
        aeropuertos.add(new Aeropuerto("LAX", "Aeropuerto Internacional Los Ángeles", 
                                       "Los Ángeles", "USA"));
        
        // Vuelos (fechas futuras)
        Date fecha1 = new Date(System.currentTimeMillis() + 86400000L); // Mañana
        Date fecha2 = new Date(System.currentTimeMillis() + 172800000L); // Pasado mañana
        
        vuelos.add(new Vuelo("V001", "AA123", "MEX", "JFK", fecha1, 
                           new Date(fecha1.getTime() + 27000000L), 250.00));
        vuelos.add(new Vuelo("V002", "AA456", "JFK", "MEX", fecha2,
                           new Date(fecha2.getTime() + 27000000L), 250.00));
        vuelos.add(new Vuelo("V003", "LA789", "MEX", "LAX", fecha1,
                           new Date(fecha1.getTime() + 7200000L), 180.00));
    }
    
    @Override
    public List<Vuelo> buscarVuelosDisponibles(String origen, String destino, 
                                             Date fecha, int pasajeros) {
        List<Vuelo> resultados = new ArrayList<>();
        
        for (Vuelo vuelo : vuelos) {
            boolean coincideOrigen = origen.isEmpty() || vuelo.getOrigenIATA().equalsIgnoreCase(origen);
            boolean coincideDestino = destino.isEmpty() || vuelo.getDestinoIATA().equalsIgnoreCase(destino);
            boolean coincideFecha = fecha == null || 
                                   (vuelo.getFechaHoraSalida() != null && 
                                    vuelo.getFechaHoraSalida().getDate() == fecha.getDate());
            boolean hayDisponibilidad = vuelo.getAsientosDisponibles() >= pasajeros;
            
            if (coincideOrigen && coincideDestino && coincideFecha && hayDisponibilidad) {
                resultados.add(vuelo);
            }
        }
        
        return resultados;
    }
    
    @Override
    public Vuelo obtenerVueloPorId(String vueloId) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getId().equals(vueloId)) {
                return vuelo;
            }
        }
        return null;
    }
    
    @Override
    public List<Aeropuerto> obtenerTodosAeropuertos() {
        return new ArrayList<>(aeropuertos);
    }
    
    @Override
    public boolean verificarDisponibilidadAsientos(String vueloId, int pasajeros) {
        Vuelo vuelo = obtenerVueloPorId(vueloId);
        if (vuelo == null) return false;
        return vuelo.getAsientosDisponibles() >= pasajeros;
    }
    
    @Override
    public List<Vuelo> obtenerVuelosRecomendados(String usuarioId) {
        // Por ahora devolvemos los primeros 3 vuelos
        return vuelos.subList(0, Math.min(3, vuelos.size()));
    }
    
    public List<Vuelo> getVuelos() {
        return new ArrayList<>(vuelos);
    }
}