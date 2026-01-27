package view;

import java.util.List;
import controller.VueloController;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import model.entidades.Vuelo;
import model.entidades.Usuario;
import javax.swing.JButton;



public class BusquedaVueloPanel extends JPanel {
    private VueloController controller;
    private JTable tablaVuelos; 
    private List<Vuelo> vuelos;
    private controller.ReservaController reservaController;
    private model.entidades.Usuario usuario;

    // CONSTRUCTOR CORRECTO que pide el VueloController
    public BusquedaVueloPanel(VueloController controller, Usuario usuario) {
        this.controller = controller;
        this.usuario = usuario;
        this.reservaController = new controller.ReservaController();
        initComponents();
    }
    
    private void initComponents() {
        
        String[] columnas = {"ID", "Origen", "Destino", "Precio"};
        tablaVuelos = new JTable(new javax.swing.table.DefaultTableModel(columnas, 0));
        JScrollPane scroll = new JScrollPane(tablaVuelos);

        add(scroll, BorderLayout.SOUTH);

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
        
        JButton btnReservar = new JButton("Reservar Vuelo");
        btnReservar.setBackground(new Color(220, 53, 69));
        btnReservar.setForeground(Color.WHITE);
        
        btnBuscar.addActionListener(e -> {
        String origen = cmbOrigen.getSelectedItem().toString();
        String destino = cmbDestino.getSelectedItem().toString();
       
        if (origen.equals(destino)) {
            JOptionPane.showMessageDialog(this,
                "El origen y destino no pueden ser iguales");
            return;
        }

        vuelos = controller.buscarVuelos(origen, destino, null, 1);

        DefaultTableModel modelo =
            (DefaultTableModel) tablaVuelos.getModel();
        modelo.setRowCount(0);

        if (vuelos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No hay vuelos disponibles");
        }   

        for (Vuelo v : vuelos) {
            modelo.addRow(new Object[]{
            v.getId(),
            v.getOrigenIATA(),
            v.getDestinoIATA(),
            v.getPrecioBase()
            });
        }
});
        btnReservar.addActionListener(e -> {
            int filaSeleccionada = tablaVuelos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleccione un vuelo de la tabla");
            return;
        }

        Vuelo vueloSeleccionado = vuelos.get(filaSeleccionada);

        var reserva = reservaController.crearReserva(vueloSeleccionado, usuario);

        if (reserva != null) {
            JOptionPane.showMessageDialog(this,
                "Reserva creada con éxito");
        } else {
            JOptionPane.showMessageDialog(this,
                "No se pudo crear la reserva");
        }
    });


        
        panelFiltros.add(new JLabel("Origen:"));
        panelFiltros.add(cmbOrigen);
        panelFiltros.add(new JLabel("Destino:"));
        panelFiltros.add(cmbDestino);
        panelFiltros.add(btnBuscar);
        panelFiltros.add(btnReservar);

        
        add(panelFiltros, BorderLayout.CENTER);
    }
}