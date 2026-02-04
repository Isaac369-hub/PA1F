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
        
        // ===== MÉXICO - USA =====
        vuelos.add(new Vuelo("V004", "UA321", "MEX", "ORD", fecha1,
                new Date(fecha1.getTime() + 10800000L), 220.00));

        vuelos.add(new Vuelo("V005", "DL654", "MEX", "ATL", fecha2,
                new Date(fecha2.getTime() + 12600000L), 240.00));

        vuelos.add(new Vuelo("V006", "AA777", "LAX", "JFK", fecha1,
                new Date(fecha1.getTime() + 18000000L), 310.00));

        // vuelos de USA a mexico
        vuelos.add(new Vuelo("V007", "UA888", "ORD", "MEX", fecha2,
                new Date(fecha2.getTime() + 10800000L), 225.00));

        vuelos.add(new Vuelo("V008", "DL999", "ATL", "MEX", fecha1,
                new Date(fecha1.getTime() + 12600000L), 245.00));

        // vuelos de mexico a latam
        vuelos.add(new Vuelo("V009", "AM101", "MEX", "BOG", fecha1,
                new Date(fecha1.getTime() + 14400000L), 280.00));

        vuelos.add(new Vuelo("V010", "AM202", "MEX", "LIM", fecha2,
                new Date(fecha2.getTime() + 18000000L), 320.00));

        // vuelos de latam a mexico
        vuelos.add(new Vuelo("V011", "LA303", "LIM", "MEX", fecha1,
                new Date(fecha1.getTime() + 18000000L), 315.00));

        vuelos.add(new Vuelo("V012", "AV404", "BOG", "MEX", fecha2,
                new Date(fecha2.getTime() + 14400000L), 275.00));

        // vuelos naciones
        vuelos.add(new Vuelo("V013", "VB111", "MEX", "CUN", fecha1,
                new Date(fecha1.getTime() + 7200000L), 120.00));

        vuelos.add(new Vuelo("V014", "VB222", "CUN", "MEX", fecha2,
                new Date(fecha2.getTime() + 7200000L), 125.00));

        vuelos.add(new Vuelo("V015", "AM333", "MEX", "MTY", fecha1,
                new Date(fecha1.getTime() + 5400000L), 95.00));

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