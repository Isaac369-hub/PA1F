package model.dao;

import model.entidades.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileDataLoader implements IDataLoader {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    @Override
    public List<Usuario> cargarUsuarios(String rutaArchivo) {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }
                
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    Pasajero pasajero = new Pasajero();
                    pasajero.setId(datos[0].trim());
                    pasajero.setNombre(datos[1].trim());
                    pasajero.setEmail(datos[2].trim());
                    pasajero.setPassword(datos[3].trim());
                    pasajero.setRol(datos[4].trim());
                    pasajero.setPasaporte(datos[5].trim());
                    usuarios.add(pasajero);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    @Override
    public List<Aeropuerto> cargarAeropuertos(String rutaArchivo) {
        List<Aeropuerto> aeropuertos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    Aeropuerto aeropuerto = new Aeropuerto(
                        datos[0].trim(),
                        datos[1].trim(),
                        datos[2].trim(),
                        datos[3].trim()
                    );
                    aeropuertos.add(aeropuerto);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando aeropuertos: " + e.getMessage());
        }
        return aeropuertos;
    }
    
    @Override
    public List<Avion> cargarAviones(String rutaArchivo) {
        List<Avion> aviones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    Avion avion = new Avion();
                    avion.setId(datos[0].trim());
                    avion.setModelo(datos[1].trim());
                    avion.setCapacidadTotal(Integer.parseInt(datos[2].trim()));
                    aviones.add(avion);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando aviones: " + e.getMessage());
        }
        return aviones;
    }
    
    @Override
    public List<Vuelo> cargarVuelos(String rutaArchivo) {
        List<Vuelo> vuelos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                String[] datos = linea.split(",");
                if (datos.length >= 7) {
                    try {
                        Vuelo vuelo = new Vuelo();
                        vuelo.setId(datos[0].trim());
                        vuelo.setNumeroVuelo(datos[1].trim());
                        vuelo.setOrigenIATA(datos[2].trim());
                        vuelo.setDestinoIATA(datos[3].trim());
                        vuelo.setFechaHoraSalida(dateFormat.parse(datos[4].trim()));
                        vuelo.setFechaHoraLlegada(dateFormat.parse(datos[5].trim()));
                        vuelo.setPrecioBase(Double.parseDouble(datos[6].trim()));
                        vuelos.add(vuelo);
                    } catch (Exception e) {
                        System.err.println("Error parseando vuelo: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando vuelos: " + e.getMessage());
        }
        return vuelos;
    }
    
    @Override
    public void guardarReserva(Reserva reserva, String rutaArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            pw.println(reserva.getId() + "," +
                      reserva.getCodigoReserva() + "," +
                      reserva.getVuelo().getId() + "," +
                      reserva.getUsuario().getId() + "," +
                      dateFormat.format(reserva.getFechaReserva()) + "," +
                      reserva.getPrecioTotal() + "," +
                      reserva.getEstado());
        } catch (IOException e) {
            System.err.println("Error guardando reserva: " + e.getMessage());
        }
    }
}