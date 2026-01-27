package controller;

import java.util.ArrayList;
import model.entidades.Reserva;
import model.entidades.Usuario;
import model.servicios.IReservaService;
import model.servicios.ReservaServiceImpl;
import view.MainFrame;
import view.ReservaPanel;

import java.util.List;
import model.entidades.Vuelo;

public class ReservaController {
    private IReservaService reservaService;
    
    public ReservaController() {
        this.reservaService = new ReservaServiceImpl();
    }
    
    public List<Reserva> obtenerReservasUsuario(Usuario usuario) {
        try {
            return reservaService.obtenerReservasPorUsuario(usuario.getId());
        } catch (Exception e) {
            System.err.println("Error obteniendo reservas: " + e.getMessage());
            return new ArrayList<>();

        }
    }
    
    public Reserva crearReserva(Vuelo vuelo, Usuario usuario) {
        try {
            List<Usuario> pasajeros = new ArrayList<>();
            pasajeros.add(usuario); // 1 pasajero

            return reservaService.crearReserva(
                vuelo,
                usuario,
                pasajeros,
                new ArrayList<>()
            );
        } catch (Exception e) {
            System.err.println("Error creando reserva: " + e.getMessage());
            return null;
        }
    }
    
    public void mostrarReservasUsuario(Usuario usuario, MainFrame mainFrame) {
        List<Reserva> reservas = obtenerReservasUsuario(usuario);
        ReservaPanel panel = new ReservaPanel(this, reservas);
        mainFrame.setContentPane(panel);
        mainFrame.actualizarTitulo("Mis Reservas");
    }
}