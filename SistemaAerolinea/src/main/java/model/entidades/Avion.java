package model.entidades;

public class Avion {
    private String id;
    private String modelo;
    private int capacidadTotal;
    private String estado;
    
    public Avion() {}
    
    public Avion(String id, String modelo, int capacidadTotal) {
        this.id = id;
        this.modelo = modelo;
        this.capacidadTotal = capacidadTotal;
        this.estado = "OPERATIVO";
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public int getCapacidadTotal() { return capacidadTotal; }
    public void setCapacidadTotal(int capacidadTotal) { this.capacidadTotal = capacidadTotal; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return modelo + " (Capacidad: " + capacidadTotal + ")";
    }
}