package controller;

import model.entidades.Vuelo;
import model.entidades.Aeropuerto;
import model.servicios.IVueloService;
import model.servicios.VueloServiceImpl;
import view.MainFrame;
import view.BusquedaVueloPanel;

import java.util.Date;
import java.util.List;

public class VueloController {
    private IVueloService vueloService;
    
    public VueloController() {
        this.vueloService = new VueloServiceImpl();
    }
    
    public List<Vuelo> buscarVuelos(String origen, String destino, Date fecha, int pasajeros) {
        try {
            return vueloService.buscarVuelosDisponibles(origen, destino, fecha, pasajeros);
        } catch (Exception e) {
            System.err.println("Error buscando vuelos: " + e.getMessage());
            return List.of();
        }
    }
    
    public void mostrarPanelBusqueda(MainFrame mainFrame) {
        BusquedaVueloPanel panel = new BusquedaVueloPanel(this);
        mainFrame.setContentPane(panel);
        mainFrame.actualizarTitulo("Búsqueda de Vuelos");
    }
}