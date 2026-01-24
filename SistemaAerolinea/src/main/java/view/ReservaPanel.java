package view;

import controller.ReservaController;
import model.entidades.Reserva;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReservaPanel extends JPanel {
    private ReservaController controller;
    private List<Reserva> reservas;
    
    // CONSTRUCTOR CORRECTO que pide el MainController
    public ReservaPanel(ReservaController controller, List<Reserva> reservas) {
        this.controller = controller;
        this.reservas = reservas;
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
        
        // Tabla de reservas
        String[] columnas = {"Código", "Vuelo", "Precio", "Estado"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        JTable tablaReservas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);
        
        // Mostrar reservas en la tabla
        if (reservas != null && !reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                Object[] fila = {
                    reserva.getCodigoReserva(),
                    reserva.getVuelo().getNumeroVuelo(),
                    String.format("$%.2f", reserva.getPrecioTotal()),
                    reserva.getEstado()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            // Si no hay reservas
            modeloTabla.addRow(new Object[]{"No hay reservas", "", "", ""});
        }
        
        add(lblTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}