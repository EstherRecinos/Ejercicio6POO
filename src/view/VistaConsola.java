package view;

import java.util.List;

/**
 * Clase encargada de la interaccion directa con el usuario por consola.
 */
public class VistaConsola {

    /**
     * Muestra el menú principal de opciones.
     */
    public void mostrarMenu() {
        System.out.println("\n==================================================");
        System.out.println("  SISTEMA DE GESTION DE INTEGRACIONES Y APIS EXTERNAS");
        System.out.println("==================================================");
        System.out.println("1. Listar todas las integraciones (Estado de Conexion)");
        System.out.println("2. Buscar API por identificador (Parametros)");
        System.out.println("3. Ordenar catalogo por latencia promedio (ASC)");
        System.out.println("4. Solicitar/Ejecutar operaciones de una integracion");
        System.out.println("5. Salir del programa");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Imprime una lista general de integraciones.
     * @param integraciones Cadena o lista con la representacion textual.
     */
    public void mostrarIntegraciones(List<?> integraciones) {
        System.out.println("\n--- CATALOGO DE INTEGRACIONES REGISTRADAS ---");
        for (Object item : integraciones) {
            System.out.println(item.toString());
        }
    }

    /**
     * Imprime un mensaje general en consola.
     * @param mensaje Texto a desplegar.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra las opciones operativas ejecutables para una integracion específica.
     */
    public void mostrarSubMenuOperaciones() {
        System.out.println("\n--- SUBMENU DE OPERACIONES DISPONIBLES ---");
        System.out.println("a) Consultar Estado Operativo");
        System.out.println("b) Cotizar Envío/Servicio");
        System.out.println("c) Procesar Cobro Financiero");
        System.out.println("d) Procesar Reintegro Financiero");
        System.out.println("e) Generar Documento Legal/Fiscal");
        System.out.println("f) Registrar Evento de Auditoria");
        System.out.println("g) Consultar Historial de Auditoria");
        System.out.print("Seleccione la operacion que desea solicitar: ");
    }
}
