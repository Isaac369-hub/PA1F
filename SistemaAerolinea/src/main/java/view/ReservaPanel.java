package view;

import controller.ReservaController;
import model.entidades.Reserva;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import controller.MainController;
import java.awt.Desktop;
import java.io.File;

public class ReservaPanel extends JPanel {
    private ReservaController controller;
    private List<Reserva> reservas;
    private MainController mainController;
    private JTable tablaReservas;
    
    // CONSTRUCTOR CORRECTO que pide el MainController
        public ReservaPanel(ReservaController controller,List<Reserva> reservas,
            MainController mainController) {
        this.controller = controller;
        this.reservas = reservas;
        this.mainController = mainController;
        initComponents();
    }

    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Mis Reservas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(new Color(0, 102, 204));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(220, 53, 69));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);

        btnRegresar.addActionListener(e -> mainController.mostrarDashboard());
        JButton btnVerTicket = new JButton("Ver Ticket");
        btnVerTicket.setBackground(new Color(23, 162, 184));
        btnVerTicket.setForeground(Color.WHITE);
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVerTicket);
        btnVerTicket.addActionListener(e -> verTicket());

        add(panelBoton, BorderLayout.SOUTH);
        
        // Tabla de reservas
        String[] columnas = {"Código", "Vuelo", "Fecha", "Asientos", "Precio", "Estado"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        tablaReservas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        
        // Mostrar reservas en la tabla
        if (reservas != null && !reservas.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Reserva reserva : reservas) {
                Object[] fila = {
                    reserva.getCodigoReserva(),
                    reserva.getVuelo().getNumeroVuelo(),
                    sdf.format(reserva.getFechaReserva()),
                    reserva.getCantidadAsientos(),
                    String.format("$%.2f", reserva.getPrecioTotal()),
                    reserva.getEstado()
                };
                modeloTabla.addRow(fila);
                }

        } else {
            // Si no hay reservas
            modeloTabla.addRow(new Object[]{"No hay reservas", "", "", "", "", ""});
        }
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        panelSuperior.add(btnRegresar, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
    }
        private void verTicket() {
        int fila = tablaReservas.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                "Seleccione una reserva");
            return;
        }

        Reserva reserva = reservas.get(fila);

        try {
            File archivo = new File(
                "data/tickets/ticket_" + reserva.getCodigoReserva() + ".txt"
            );

            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(this,
                    "El ticket no existe");
                return;
            }

            Desktop.getDesktop().open(archivo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "No se pudo abrir el ticket");
        }
    }

}