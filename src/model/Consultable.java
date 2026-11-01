package model;

/**
 * Contrato para integraciones que permiten verificar su estado operativo.
 */
public interface Consultable {
    /**
     * Consulta el estado de operacion del servicio externo.
     * @return Estado actual del servicio.
     */
    String consultarEstado();
}
