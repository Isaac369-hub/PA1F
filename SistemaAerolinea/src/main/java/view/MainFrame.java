package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private JLabel lblTitulo;
    
    public MainFrame() {
        initComponents();
        configurarVentana();
    }
    
    private void initComponents() {
        // Configurar propiedades de la ventana
        setTitle("Sistema AeroSystem - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        // Crear panel principal con BorderLayout
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        // Crear barra de título
        lblTitulo = new JLabel("Sistema-AeroSystem FIS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 76, 153));
        
        // Crear panel para el título
        JPanel tituloPanel = new JPanel(new BorderLayout());
        tituloPanel.setBackground(Color.WHITE);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tituloPanel.add(lblTitulo, BorderLayout.CENTER);
        
        // Botón de salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 12));
        btnSalir.setBackground(new Color(220, 53, 69));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.addActionListener(e -> System.exit(0));
        tituloPanel.add(btnSalir, BorderLayout.EAST);
        
        // Añadir componentes al frame
        add(tituloPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private void configurarVentana() {
        // Hacer visible la ventana
        setVisible(true);
    }
    
    /**
     * Cambia el contenido principal de la ventana
     */
    public void setContentPane(JPanel panel) {
        this.contentPanel.removeAll();
        this.contentPanel.add(panel, BorderLayout.CENTER);
        this.contentPanel.revalidate();
        this.contentPanel.repaint();
    }
    
    /**
     * Actualiza el título de la ventana
     */
    public void actualizarTitulo(String titulo) {
        lblTitulo.setText(titulo);
    }
    
    /**
     * Muestra un mensaje de diálogo
     */
    public void mostrarMensaje(String mensaje, String tipo) {
        int messageType;
        switch(tipo.toUpperCase()) {
            case "ERROR":
                messageType = JOptionPane.ERROR_MESSAGE;
                break;
            case "WARNING":
                messageType = JOptionPane.WARNING_MESSAGE;
                break;
            default:
                messageType = JOptionPane.INFORMATION_MESSAGE;
        }
        
        JOptionPane.showMessageDialog(this, mensaje, "Sistema de Aerolínea", messageType);
    }
    
    /**
     * Método main simplificado
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}