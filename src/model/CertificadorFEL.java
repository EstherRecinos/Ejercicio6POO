package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un Certificador de Factura Electrónica en Línea (FEL).
 * Implementa Documentable, Consultable y Auditable.
 */
public class CertificadorFEL extends Integracion implements Documentable, Consultable, Auditable {
    private List<String> historialEventos;

    public CertificadorFEL(String identificador, String nombreProveedor, String estadoConexion, 
                           double latenciaPromedio, String parametrosConfiguracion) {
        super(identificador, nombreProveedor, estadoConexion, latenciaPromedio, parametrosConfiguracion);
        this.historialEventos = new ArrayList<>();
        registrarEvento("Certificador FEL habilitado y autenticado.");
    }

    @Override
    public String generarDocumento() {
        String log = "Factura Electrónica DTE generada y certificada legalmente. Autorizacion UUID: FEL-" + System.nanoTime();
        registrarEvento(log);
        return "[" + nombreProveedor + "] " + log;
    }

    @Override
    public String consultarEstado() {
        return "[" + nombreProveedor + "] Servidor de Certificación SAT FEL: " + estadoConexion + " - Conectado.";
    }

    @Override
    public void registrarEvento(String evento) {
        historialEventos.add("[LOG-FEL] " + evento);
    }

    @Override
    public List<String> consultarHistorialEventos() {
        return new ArrayList<>(historialEventos);
    }
}
