package model;

/**
 * Contrato para servicios externos que permiten realizar cotizaciones de costos.
 */
public interface Cotizable {
    /**
     * Calcula la cotizacion del servicio ofrecido.
     * @return Mensaje o valor de la cotizacion obtenida.
     */
    String cotizar();
}
