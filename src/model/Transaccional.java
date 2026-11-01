package model;

/**
 * Contrato para integraciones que ejecutan operaciones financieras de cobro y reintegro.
 */
public interface Transaccional {
    /**
     * Procesa un cobro financiero por el monto especificado.
     * @param monto Cantidad de dinero a cobrar.
     * @return Mensaje de confirmacion del cobro.
     */
    String cobrar(double monto);

    /**
     * Procesa una devolucion o reintegro financiero por el monto especificado.
     * @param monto Cantidad de dinero a reintegrar.
     * @return Mensaje de confirmacion del reintegro.
     */
    String reintegrar(double monto);
}
