package view;

import controller.MainController;
import model.entidades.Usuario;
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private MainController controller;
    private Usuario usuario;
    private JLabel lblBienvenida;
    private JButton btnBuscarVuelos;
    private JButton btnMisReservas;
    private JButton btnCerrarSesion;
    
    public DashboardPanel(MainController controller) {
        this.controller = controller;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        
        // Panel de bienvenida
        JPanel panelBienvenida = new JPanel(new BorderLayout());
        panelBienvenida.setBackground(new Color(0, 102, 204));
        panelBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        lblBienvenida = new JLabel("Bienvenido al Sistema de Aerolínea", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
        lblBienvenida.setForeground(Color.WHITE);
        panelBienvenida.add(lblBienvenida, BorderLayout.CENTER);
        
        // Panel de opciones
        JPanel panelOpciones = new JPanel(new GridBagLayout());
        panelOpciones.setBackground(new Color(245, 245, 245));
        panelOpciones.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Botón: Buscar Vuelos
        btnBuscarVuelos = new JButton("Buscar Vuelos");
        btnBuscarVuelos.setFont(new Font("Arial", Font.BOLD, 16));
        btnBuscarVuelos.setBackground(new Color(40, 167, 69));
        btnBuscarVuelos.setForeground(Color.WHITE);
        btnBuscarVuelos.setPreferredSize(new Dimension(250, 50));
        btnBuscarVuelos.addActionListener(e -> controller.mostrarBusquedaVuelos());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelOpciones.add(btnBuscarVuelos, gbc);
        
        // Botón: Mis Reservas
        btnMisReservas = new JButton("Mis Reservas");
        btnMisReservas.setFont(new Font("Arial", Font.BOLD, 16));
        btnMisReservas.setBackground(new Color(0, 123, 255));
        btnMisReservas.setForeground(Color.WHITE);
        btnMisReservas.setPreferredSize(new Dimension(250, 50));
        btnMisReservas.addActionListener(e -> controller.mostrarReservas());
        
        gbc.gridy = 1;
        panelOpciones.add(btnMisReservas, gbc);
        
        // Botón: Cerrar Sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCerrarSesion.setBackground(new Color(220, 53, 69));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setPreferredSize(new Dimension(150, 40));
        btnCerrarSesion.addActionListener(e -> controller.logout());
        
        // Panel para botón de cerrar sesión
        JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCerrar.setBackground(new Color(245, 245, 245));
        panelCerrar.add(btnCerrarSesion);
        
        // Añadir componentes al panel principal
        add(panelBienvenida, BorderLayout.NORTH);
        add(panelOpciones, BorderLayout.CENTER);
        add(panelCerrar, BorderLayout.SOUTH);
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        lblBienvenida.setText("Bienvenido, " + usuario.getNombre() + " (" + usuario.getRol() + ")");
    }
}