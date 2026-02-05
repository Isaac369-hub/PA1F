package model.servicios;

import model.entidades.Reserva;
import model.entidades.Usuario;
import model.entidades.Pasajero;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import model.entidades.Vuelo;

public class ReservaServiceImpl implements IReservaService {
    private List<Reserva> reservas;
    private IVueloService vueloService;
    
    public ReservaServiceImpl() {
        this.reservas = new ArrayList<>();
        this.vueloService = new VueloServiceImpl();
    }
    
    @Override
    public Reserva crearReserva(Vuelo vuelo, Usuario usuario, 
                               List<Usuario> pasajeros, List<String> asientos, String metodoPago) {
        // Convertir usuarios a pasajeros
        List<Pasajero> pasajerosList = new ArrayList<>();
        for (Usuario u : pasajeros) {
            if (u instanceof Pasajero) {
                pasajerosList.add((Pasajero) u);
            }
        }
        
        // Calcular precio (precio base * cantidad de pasajeros)
        double precioTotal = vuelo.getPrecioBase() * pasajeros.size();
        // Generar asientos numerados automáticamente
        List<String> asientosGenerados = new ArrayList<>();

        int inicio = vuelo.getAsientosDisponibles() - pasajeros.size() + 1;

        for (int i = 0; i < pasajeros.size(); i++) {
            asientosGenerados.add("A" + (inicio + i));
        }

        // Crear reserva
        String reservaId = "RES" + String.format("%03d", reservas.size() + 1);

        Reserva reserva = new Reserva(reservaId,vuelo,usuario,pasajerosList,precioTotal,"ECONOMICA",
            pasajeros.size(),asientosGenerados, metodoPago);
        // Actualizar disponibilidad de asientos
        vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - pasajeros.size());
        
        // Guardar reserva
        reservas.add(reserva);
        guardarReservaEnArchivo(reserva);
        generarTicketTXT(reserva);

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
         vuelo.getAsientosDisponibles() + reserva.getCantidadAsientos());
                
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
    
    // manejo de archivos txt
    private static final String DATA_PATH = "data";
    private static final String RESERVAS_FILE = DATA_PATH + "/reservas.txt";
    private static final String TICKETS_PATH = DATA_PATH + "/tickets";

    private void crearDirectoriosSiNoExisten() {
        new File(DATA_PATH).mkdirs();
        new File(TICKETS_PATH).mkdirs();
    }

    private void guardarReservaEnArchivo(Reserva reserva) {
        crearDirectoriosSiNoExisten();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESERVAS_FILE, true))) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String linea =
                reserva.getCodigoReserva() + ";" +
                reserva.getUsuario().getNombre() + ";" +
                reserva.getVuelo().getOrigenIATA() + ";" +
                reserva.getVuelo().getDestinoIATA() + ";" +
                reserva.getPrecioTotal() + ";" +
                reserva.getEstado() + ";" +
                sdf.format(reserva.getFechaReserva());

        bw.write(linea);
        bw.newLine();

    } catch (IOException e) {
        System.err.println("Error guardando reserva en archivo: " + e.getMessage());
    }
}

private void generarTicketTXT(Reserva reserva) {
    crearDirectoriosSiNoExisten();

    String nombreArchivo =
            TICKETS_PATH + "/ticket_" + reserva.getCodigoReserva() + ".txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        bw.write("======== TICKET DE VUELO FIS ========");
        bw.newLine();
        bw.write("Código de reserva: " + reserva.getCodigoReserva());
        bw.newLine();
        bw.write("Pasajero: " + reserva.getUsuario().getNombre());
        bw.newLine();
        bw.write("Vuelo: " +reserva.getVuelo().getOrigenIATA() + " -> " +reserva.getVuelo().getDestinoIATA());
        bw.newLine();
        bw.write("Asientos reservados: " + reserva.getCantidadAsientos());
        bw.newLine();
        bw.write("Clase: " + reserva.getClase());
        bw.newLine();
        bw.write("Asientos: " + reserva.getAsientosTexto());
        bw.newLine();
        bw.write("Precio total: $" + reserva.getPrecioTotal());
        bw.newLine();
        bw.write("Estado: " + reserva.getEstado());
        bw.newLine();
        bw.write("Fecha: " + sdf.format(reserva.getFechaReserva()));
        bw.newLine();
        bw.write("================================");

    } catch (IOException e) {
        System.err.println("Error generando ticket: " + e.getMessage());
    }
}

}