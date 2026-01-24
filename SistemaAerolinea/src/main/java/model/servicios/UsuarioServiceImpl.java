package model.servicios;

import model.entidades.Usuario;
import model.entidades.Pasajero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioServiceImpl implements IUsuarioService {
    private List<Usuario> usuarios;
    
    public UsuarioServiceImpl() {
        this.usuarios = new ArrayList<>();
        cargarUsuariosDemo();
    }
    
    private void cargarUsuariosDemo() {
        // Usuario 1
        Pasajero p1 = new Pasajero();
        p1.setId("USR001");
        p1.setNombre("Juan Pérez");
        p1.setEmail("juan@email.com");
        p1.setPassword("1234");
        p1.setTelefono("555-1234");
        p1.setRol("PASAJERO");
        p1.setPasaporte("P12345678");
        p1.setFechaNacimiento(new Date());
        p1.setNacionalidad("Mexicana");
        usuarios.add(p1);
        
        // Usuario 2
        Pasajero p2 = new Pasajero();
        p2.setId("USR002");
        p2.setNombre("María Gómez");
        p2.setEmail("maria@email.com");
        p2.setPassword("1234");
        p2.setTelefono("555-5678");
        p2.setRol("PASAJERO");
        p2.setPasaporte("P87654321");
        p2.setFechaNacimiento(new Date());
        p2.setNacionalidad("Mexicana");
        usuarios.add(p2);
        
        // Admin
        Usuario admin = new Usuario("ADM001", "Admin Sistema", "admin@aerolinea.com", 
                                   "admin123", "ADMIN") {
            @Override
            public void mostrarInformacion() {
                System.out.println("Administrador: " + getNombre());
            }
        };
        usuarios.add(admin);
    }
    
    @Override
    public Usuario autenticar(String email, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email) && 
                usuario.getPassword().equals(password)) {
                return usuario;
            }
        }
        return null;
    }
    
    @Override
    public boolean registrarUsuario(Usuario usuario) {
        if (validarEmailExistente(usuario.getEmail())) {
            return false;
        }
        usuario.setId("USR" + String.format("%03d", usuarios.size() + 1));
        usuarios.add(usuario);
        return true;
    }
    
    @Override
    public boolean actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuarioActualizado.getId())) {
                usuarios.set(i, usuarioActualizado);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Usuario obtenerUsuarioPorId(String usuarioId) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(usuarioId)) {
                return usuario;
            }
        }
        return null;
    }
    
    @Override
    public Pasajero convertirAPasajero(Usuario usuario) {
        if (usuario instanceof Pasajero) {
            return (Pasajero) usuario;
        }
        
        Pasajero pasajero = new Pasajero();
        pasajero.setId(usuario.getId());
        pasajero.setNombre(usuario.getNombre());
        pasajero.setEmail(usuario.getEmail());
        pasajero.setPassword(usuario.getPassword());
        pasajero.setTelefono(usuario.getTelefono());
        pasajero.setRol("PASAJERO");
        pasajero.setPasaporte("N/A");
        pasajero.setFechaNacimiento(new Date());
        pasajero.setNacionalidad("N/A");
        
        return pasajero;
    }
    
    @Override
    public boolean validarEmailExistente(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
    
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}