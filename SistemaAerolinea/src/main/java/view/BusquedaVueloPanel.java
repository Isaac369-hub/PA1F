package view;

import controller.VueloController;
import javax.swing.*;
import java.awt.*;

public class BusquedaVueloPanel extends JPanel {
    private VueloController controller;
    
    // CONSTRUCTOR CORRECTO que pide el VueloController
    public BusquedaVueloPanel(VueloController controller) {
        this.controller = controller;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Búsqueda de Vuelos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 102, 204));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(lblTitulo, BorderLayout.NORTH);
        
        // Panel de filtros simple
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JComboBox<String> cmbOrigen = new JComboBox<>(new String[]{"MEX", "JFK", "LAX"});
        JComboBox<String> cmbDestino = new JComboBox<>(new String[]{"MEX", "JFK", "LAX"});
        JButton btnBuscar = new JButton("Buscar Vuelos");
        btnBuscar.setBackground(new Color(40, 167, 69));
        btnBuscar.setForeground(Color.WHITE);
        
        panelFiltros.add(new JLabel("Origen:"));
        panelFiltros.add(cmbOrigen);
        panelFiltros.add(new JLabel("Destino:"));
        panelFiltros.add(cmbDestino);
        panelFiltros.add(btnBuscar);
        
        add(panelFiltros, BorderLayout.CENTER);
    }
}