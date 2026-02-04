package view;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import controller.VueloController;
import controller.MainController;
import controller.ReservaController;

import model.entidades.Vuelo;
import model.entidades.Usuario;

public class BusquedaVueloPanel extends JPanel {

    private VueloController vueloController;
    private ReservaController reservaController;
    private MainController mainController;
    private Usuario usuario;

    private JTable tablaVuelos;
    private List<Vuelo> vuelos;

    // constructor
    public BusquedaVueloPanel(
        VueloController vueloController,
        Usuario usuario,
        MainController mainController,
        ReservaController reservaController
    ) {
        this.vueloController = vueloController;
        this.usuario = usuario;
        this.mainController = mainController;
        this.reservaController = reservaController;

        initComponents();
    }

    // UI
    private void initComponents() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // titulo
        JLabel lblTitulo = new JLabel("Búsqueda de Vuelos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 102, 204));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // tabla
        String[] columnas = {"ID", "Origen", "Destino", "Precio"};
        tablaVuelos = new JTable(new DefaultTableModel(columnas, 0));
        JScrollPane scroll = new JScrollPane(tablaVuelos);
        add(scroll, BorderLayout.CENTER);

        // panel inferior
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        String[] aeropuertos = {"MEX", "JFK", "LAX", "ORD", "ATL","BOG", "LIM",
            "CUN", "MTY"
        };

        JComboBox<String> cmbOrigen = new JComboBox<>(aeropuertos);
        JComboBox<String> cmbDestino = new JComboBox<>(aeropuertos);


        JButton btnBuscar = new JButton("Buscar Vuelos");
        btnBuscar.setBackground(new Color(40, 167, 69)); 
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        
        JButton btnReservar = new JButton("Reservar Vuelo");
        btnReservar.setBackground(new Color(220, 53, 69)); 
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        
        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBackground(new Color(108, 117, 125)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);

        panelFiltros.add(new JLabel("Origen:"));
        panelFiltros.add(cmbOrigen);
        panelFiltros.add(new JLabel("Destino:"));
        panelFiltros.add(cmbDestino);
        panelFiltros.add(btnBuscar);
        panelFiltros.add(btnReservar);
        panelFiltros.add(btnVolver);

        add(panelFiltros, BorderLayout.SOUTH);

        // acciones
        btnBuscar.addActionListener(e -> buscarVuelos(cmbOrigen, cmbDestino));
        btnReservar.addActionListener(e -> reservarVuelo());
        btnVolver.addActionListener(e -> mainController.mostrarDashboard());
    }

    // metodos
    private void buscarVuelos(JComboBox<String> cmbOrigen, JComboBox<String> cmbDestino) {

        String origen = cmbOrigen.getSelectedItem().toString();
        String destino = cmbDestino.getSelectedItem().toString();

        if (origen.equals(destino)) {
            JOptionPane.showMessageDialog(this,
                "El origen y destino no pueden ser iguales");
            return;
        }

        vuelos = vueloController.buscarVuelos(origen, destino, null, 1);

        DefaultTableModel modelo = (DefaultTableModel) tablaVuelos.getModel();
        modelo.setRowCount(0);

        if (vuelos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No hay vuelos disponibles");
            return;
        }

        for (Vuelo v : vuelos) {
            modelo.addRow(new Object[]{
                v.getId(),
                v.getOrigenIATA(),
                v.getDestinoIATA(),
                v.getPrecioBase()
            });
        }
    }

    private void reservarVuelo() {

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
    }
}
