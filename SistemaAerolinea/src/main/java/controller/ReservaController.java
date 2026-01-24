package controller;

import model.entidades.Reserva;
import model.entidades.Usuario;
import model.servicios.IReservaService;
import model.servicios.ReservaServiceImpl;
import view.MainFrame;
import view.ReservaPanel;

import java.util.List;

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
            return List.of();
        }
    }
    
    public void mostrarReservasUsuario(Usuario usuario, MainFrame mainFrame) {
        List<Reserva> reservas = obtenerReservasUsuario(usuario);
        ReservaPanel panel = new ReservaPanel(this, reservas);
        mainFrame.setContentPane(panel);
        mainFrame.actualizarTitulo("Mis Reservas");
    }
}