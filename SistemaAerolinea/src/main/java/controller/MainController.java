package controller;

import model.entidades.Usuario;
import model.servicios.IUsuarioService;
import model.servicios.UsuarioServiceImpl;
import view.MainFrame;
import view.LoginPanel;
import view.DashboardPanel;

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
        // Inicializar servicios
        this.usuarioService = new UsuarioServiceImpl();
        
        // Inicializar controladores específicos
        this.vueloController = new VueloController();
        this.reservaController = new ReservaController();
        this.usuarioController = new UsuarioController(usuarioService);
        
        // Crear interfaz gráfica
        this.mainFrame = new MainFrame();
        this.loginPanel = new LoginPanel(this);
        
        // Configurar frame principal
        mainFrame.setContentPane(loginPanel);
        mainFrame.setVisible(true);
    }
    
    public boolean login(String username, String password) {
        usuarioActual = usuarioService.autenticar(username, password);
        
        if (usuarioActual != null) {
            // Crear dashboard después del login exitoso
            this.dashboardPanel = new DashboardPanel(this);
            dashboardPanel.setUsuario(usuarioActual);
            
            mainFrame.setContentPane(dashboardPanel);
            mainFrame.actualizarTitulo("Bienvenido, " + usuarioActual.getNombre());
            return true;
        }
        return false;
    }
    
    public void logout() {
        usuarioActual = null;
        mainFrame.setContentPane(loginPanel);
        loginPanel.limpiarCampos();
        mainFrame.actualizarTitulo("Sistema de Aerolínea - Login");
    }
    
    public void mostrarBusquedaVuelos() {
        vueloController.mostrarPanelBusqueda(mainFrame);
    }
    
    public void mostrarReservas() {
        reservaController.mostrarReservasUsuario(usuarioActual, mainFrame);
    }
    
    public void mostrarPerfil() {
        usuarioController.mostrarPerfil(usuarioActual, mainFrame);
    }
    
    // Getters para los controladores específicos
    public VueloController getVueloController() {
        return vueloController;
    }
    
    public ReservaController getReservaController() {
        return reservaController;
    }
    
    public UsuarioController getUsuarioController() {
        return usuarioController;
    }
    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public static void main(String[] args) {
        // Usar SwingUtilities para garantizar thread safety
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainController();
        });
    }
}