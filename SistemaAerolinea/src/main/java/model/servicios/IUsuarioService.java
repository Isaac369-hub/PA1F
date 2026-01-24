package model.servicios;

import model.entidades.Usuario;
import model.entidades.Pasajero;

public interface IUsuarioService {
    Usuario autenticar(String email, String password);
    boolean registrarUsuario(Usuario usuario);
    boolean actualizarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(String usuarioId);
    Pasajero convertirAPasajero(Usuario usuario);
    boolean validarEmailExistente(String email);
}