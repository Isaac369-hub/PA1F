package controller;

import model.entidades.Vuelo;
import model.servicios.IVueloService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VueloController {

    private IVueloService vueloService;

    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }

    public List<Vuelo> buscarVuelos(
            String origen,
            String destino,
            Date fecha,
            int pasajeros
    ) {
        try {
            return vueloService.buscarVuelosDisponibles(
                    origen, destino, fecha, pasajeros
            );
        } catch (Exception e) {
            System.err.println("Error buscando vuelos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Vuelo> buscarVuelos(String origen, String destino) {
        return buscarVuelos(origen, destino, null, 1);
    }
}