// En: src/model/entidades/Usuario.java
package model.entidades;

/**
 * Clase base para todos los usuarios del sistema
 * Demuestra principio de Liskov Substitution (SOLID)
 */
public abstract class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String rol; // "ADMIN", "AGENTE", "PASAJERO"
    
    public Usuario() {
        // Constructor por defecto
    }
    
    public Usuario(String id, String nombre, String email, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    
    // Método abstracto - principio de Open/Closed
    public abstract void mostrarInformacion();
    
    // Getters y Setters (genera estos con tu IDE)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}