package controller;

import javax.swing.JOptionPane;

import model.entidades.Usuario;
import model.servicios.IUsuarioService;
import model.servicios.IVueloService;
import model.servicios.UsuarioServiceImpl;
import model.servicios.VueloServiceImpl;

import view.MainFrame;
import view.LoginPanel;
import view.DashboardPanel;
import view.BusquedaVueloPanel;

public class MainController {

    private MainFrame mainFrame;
    private LoginPanel loginPanel;
    private DashboardPanel dashboardPanel;

    private VueloController vueloController;
    private ReservaController reservaController;
    private UsuarioController usuarioController;

    private IUsuarioService usuarioService;
    private Usuario usuarioActual;

    public MainController() {

        // Servicios
        usuarioService = new UsuarioServiceImpl();
        IVueloService vueloService = new VueloServiceImpl();

        // Controladores
        vueloController = new VueloController(vueloService);
        reservaController = new ReservaController();
        usuarioController = new UsuarioController(usuarioService);

        // frame
        mainFrame = new MainFrame();
        loginPanel = new LoginPanel(this);

        mainFrame.setContentPane(loginPanel);
        mainFrame.actualizarTitulo("Sistema de Aerolínea - Login");
        mainFrame.setVisible(true);
    }

    // inicio de secion 
    public boolean login(String username, String password) {
        usuarioActual = usuarioService.autenticar(username, password);

        if (usuarioActual != null) {
            mostrarDashboard();
            return true;
        }
        return false;
    }

    // el logout
    public void logout() {
        usuarioActual = null;
        mainFrame.setContentPane(loginPanel);
        loginPanel.limpiarCampos();
        mainFrame.actualizarTitulo("Sistema de Aerolínea - Login");
    }

    // dashboard principal
    public void mostrarDashboard() {
        dashboardPanel = new DashboardPanel(this);
        dashboardPanel.setUsuario(usuarioActual);

        mainFrame.setContentPane(dashboardPanel);
        mainFrame.actualizarTitulo(
            "Bienvenido, " + usuarioActual.getNombre()
        );
    }

    // la busqueda de vuelos
    public void mostrarBusquedaVuelos() {

        BusquedaVueloPanel panel = new BusquedaVueloPanel(
            vueloController,
            usuarioActual,
            this,
            reservaController
        );

        mainFrame.setContentPane(panel);
        mainFrame.actualizarTitulo("Búsqueda de Vuelos");
    }

    // reservas
    public void mostrarReservas() {
        reservaController.mostrarReservasUsuario(usuarioActual, mainFrame);
    }

    // perfil
    public void mostrarPerfil() {
        usuarioController.mostrarPerfil(usuarioActual, mainFrame);
    }

    // getters
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(MainController::new);
    }
}