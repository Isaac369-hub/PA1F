package model.entidades;

public class Asiento {
    private String numero;
    private String clase; // "ECONOMICA", "BUSINESS", "PRIMERA"
    private double precio;
    private boolean disponible;
    private Vuelo vuelo;
    
    public Asiento() {}
    
    public Asiento(String numero, String clase, double precio, Vuelo vuelo) {
        this.numero = numero;
        this.clase = clase;
        this.precio = precio;
        this.vuelo = vuelo;
        this.disponible = true;
    }
    
    // Getters y Setters
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
    
    @Override
    public String toString() {
        return numero + " (" + clase + ") - $" + precio + (disponible ? " ✔" : " ✘");
    }
}