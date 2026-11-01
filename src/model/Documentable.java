package model;

/**
 * Contrato para integraciones capaces de generar documentos legales o fiscales.
 */
public interface Documentable {
    /**
     * Genera el documento legal o fiscal correspondiente a la integracion.
     * @return Mensaje o representación del documento generado.
     */
    String generarDocumento();
}
