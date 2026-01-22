// En: src/model/entidades/Avion.java
package model.entidades;

/**
 * Representa un avión en la flota de la aerolínea
 */
public class Avion {
    private String id;
    private String modelo;
    private String fabricante;
    private int capacidadEconomica;
    private int capacidadBusiness;
    private int capacidadPrimera;
    private double autonomiaKm;
    private String estado; // "OPERATIVO", "MANTENIMIENTO", "INACTIVO"
    
    public Avion() {}
    
    public Avion(String id, String modelo, int capacidadTotal) {
        this.id = id;
        this.modelo = modelo;
        this.capacidadEconomica = capacidadTotal;
        this.capacidadBusiness = 0;
        this.capacidadPrimera = 0;
        this.estado = "OPERATIVO";
    }
    
    public int getCapacidadTotal() {
        return capacidadEconomica + capacidadBusiness + capacidadPrimera;
    }
    
    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    
    public int getCapacidadEconomica() { return capacidadEconomica; }
    public void setCapacidadEconomica(int capacidadEconomica) { this.capacidadEconomica = capacidadEconomica; }
    
    public int getCapacidadBusiness() { return capacidadBusiness; }
    public void setCapacidadBusiness(int capacidadBusiness) { this.capacidadBusiness = capacidadBusiness; }
    
    public int getCapacidadPrimera() { return capacidadPrimera; }
    public void setCapacidadPrimera(int capacidadPrimera) { this.capacidadPrimera = capacidadPrimera; }
    
    public double getAutonomiaKm() { return autonomiaKm; }
    public void setAutonomiaKm(double autonomiaKm) { this.autonomiaKm = autonomiaKm; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}