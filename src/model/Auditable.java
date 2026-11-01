package model;

import java.util.List;

/**
 * Contrato para servicios que requieren mantener un registro e historial de auditoria.
 */
public interface Auditable {
    /**
     * Registra un evento en la Bitacora del servicio.
     * @param evento Descripcion del evento a registrar.
     */
    void registrarEvento(String evento);

    /**
     * Obtiene la lista completa de eventos auditados.
     * @return Lista de eventos registrados.
     */
    List<String> consultarHistorialEventos();
}
