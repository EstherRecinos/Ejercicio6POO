package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un servicio externo de Pasarela de Pago.
 * Implementa Transaccional, Consultable y Auditable.
 */
public class PasarelaPago extends Integracion implements Transaccional, Consultable, Auditable {
    private List<String> historialEventos;

    public PasarelaPago(String identificador, String nombreProveedor, String estadoConexion, 
                        double latenciaPromedio, String parametrosConfiguracion) {
        super(identificador, nombreProveedor, estadoConexion, latenciaPromedio, parametrosConfiguracion);
        this.historialEventos = new ArrayList<>();
        registrarEvento("Inicializacion del servicio de pasarela de pago.");
    }

    @Override
    public String cobrar(double monto) {
        String log = "Cobro procesado exitosamente por la cantidad de Q" + String.format("%.2f", monto);
        registrarEvento(log);
        return "[" + nombreProveedor + "] " + log;
    }

    @Override
    public String reintegrar(double monto) {
        String log = "Reintegro procesado exitosamente por la cantidad de Q" + String.format("%.2f", monto);
        registrarEvento(log);
        return "[" + nombreProveedor + "] " + log;
    }

    @Override
    public String consultarEstado() {
        return "[" + nombreProveedor + "] Estado del servicio de pago: " + estadoConexion + " - Operaciones operativas.";
    }

    @Override
    public void registrarEvento(String evento) {
        historialEventos.add("[LOG-PAGO] " + evento);
    }

    @Override
    public List<String> consultarHistorialEventos() {
        return new ArrayList<>(historialEventos);
    }
}
