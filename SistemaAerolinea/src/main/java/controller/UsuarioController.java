package controller;

import model.entidades.Usuario;
import model.entidades.Pasajero;
import model.servicios.IUsuarioService;
import view.MainFrame;

public class UsuarioController {
    private IUsuarioService usuarioService;
    
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public void mostrarPerfil(Usuario usuario, MainFrame mainFrame) {
        System.out.println("Mostrando perfil de: " + usuario.getNombre());
    }
}