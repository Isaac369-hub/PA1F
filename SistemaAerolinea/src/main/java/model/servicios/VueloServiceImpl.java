package model.servicios;

import model.entidades.Vuelo;
import model.entidades.Aeropuerto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class VueloServiceImpl implements IVueloService {
    private List<Vuelo> vuelos;
    private List<Aeropuerto> aeropuertos;
    
    public VueloServiceImpl() {
        this.vuelos = new ArrayList<>();
        this.aeropuertos = new ArrayList<>();
        cargarAeropuertosDesdeArchivo();
        cargarVuelosDesdeArchivo();
    }
    private void cargarAeropuertosDesdeArchivo() {
        File archivo = new File("data/aeropuertos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(",");

            aeropuertos.add(new Aeropuerto(
                d[0], // IATA
                d[1], // nombre
                d[2], // ciudad
                d[3]  // pais
            ));
        }
        } catch (IOException e) {
            System.err.println("Error cargando aeropuertos: " + e.getMessage());
        }
    }

    private void cargarVuelosDesdeArchivo() {
        File archivo = new File("data/vuelos.txt");

        Date salida = new Date(System.currentTimeMillis() + 86400000L); // mañana
        Date llegada = new Date(salida.getTime() + 10800000L); // +3h

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(",");

            vuelos.add(new Vuelo(
                d[0],               // id
                d[1],               // numero
                d[2],               // origen
                d[3],               // destino
                salida,
                llegada,
                Double.parseDouble(d[4])
            ));
        }
        } catch (IOException e) {
            System.err.println("Error cargando vuelos: " + e.getMessage());
        }
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