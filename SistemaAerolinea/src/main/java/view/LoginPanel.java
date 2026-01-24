package view;

import controller.MainController;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainController controller;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMensaje;
    
    public LoginPanel(MainController controller) {
        this.controller = controller;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 245, 250));
        
        // Panel central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(240, 245, 250));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 76, 153));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelCentral.add(lblTitulo, gbc);
        
        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelCentral.add(lblEmail, gbc);
        
        txtEmail = new JTextField(20);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCentral.add(txtEmail, gbc);
        
        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCentral.add(lblPassword, gbc);
        
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCentral.add(txtPassword, gbc);
        
        // Botón Login
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(200, 35));
        btnLogin.addActionListener(e -> realizarLogin());
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCentral.add(btnLogin, gbc);
        
        // Mensaje
        lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridy = 4;
        panelCentral.add(lblMensaje, gbc);
        
        // Info
        JLabel lblInfo = new JLabel("<html><center>"
                + "Usa: juan@email.com / 1234"
                + "</center></html>", SwingConstants.CENTER);
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        lblInfo.setForeground(Color.GRAY);
        gbc.gridy = 5;
        panelCentral.add(lblInfo, gbc);
        
        add(panelCentral, BorderLayout.CENTER);
    }
    
    private void realizarLogin() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (email.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Complete todos los campos");
            return;
        }
        
        lblMensaje.setText("Autenticando...");
        lblMensaje.setForeground(Color.BLUE);
        
        boolean loginExitoso = controller.login(email, password);
        if (!loginExitoso) {
            lblMensaje.setText("Credenciales incorrectas");
            lblMensaje.setForeground(Color.RED);
        }
    }
    
    public void limpiarCampos() {
        txtEmail.setText("");
        txtPassword.setText("");
        lblMensaje.setText(" ");
    }
}