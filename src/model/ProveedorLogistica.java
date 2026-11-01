package model;

/**
 * Clase que representa una API externa de Proveedor de Logistica de envios.
 * Implementa Cotizable, Documentable y Consultable.
 */
public class ProveedorLogistica extends Integracion implements Cotizable, Documentable, Consultable {

    public ProveedorLogistica(String identificador, String nombreProveedor, String estadoConexion, 
                               double latenciaPromedio, String parametrosConfiguracion) {
        super(identificador, nombreProveedor, estadoConexion, latenciaPromedio, parametrosConfiguracion);
    }

    @Override
    public String cotizar() {
        return "[" + nombreProveedor + "] Cotizacion estandar de envio calculada: Q35.00 (Tarifa Base).";
    }

    @Override
    public String generarDocumento() {
        return "[" + nombreProveedor + "] Guia de envio generada exitosamente. Numero de rastreo: LOG-" + System.currentTimeMillis() % 100000;
    }

    @Override
    public String consultarEstado() {
        return "[" + nombreProveedor + "] Estado de red logistica: " + estadoConexion + " - Envíos a tiempo.";
    }
}
