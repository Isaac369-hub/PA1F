package model.entidades;

public class Asiento {
    private String numero;
    private String clase;
    private double precio;
    private boolean disponible;
    
    public Asiento() {}
    
    public Asiento(String numero, String clase, double precio) {
        this.numero = numero;
        this.clase = clase;
        this.precio = precio;
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
    
    @Override
    public String toString() {
        return numero + " (" + clase + ") - $" + precio + (disponible ? " ✔" : " ✘");
    }
}