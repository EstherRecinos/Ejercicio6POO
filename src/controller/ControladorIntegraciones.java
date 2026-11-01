package controller;

import model.*;
import view.VistaConsola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador principal que maneja la logica de negocio y gestiona
 * la lista polimorfica de integraciones.
 */
public class ControladorIntegraciones {
    private List<Integracion> integraciones;
    private VistaConsola vista;
    private Scanner scanner;

    public ControladorIntegraciones(VistaConsola vista) {
        this.vista = vista;
        this.integraciones = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        cargarDatosIniciales();
    }

    /**
     * Realiza la carga inicial con al menos 10 conexiones a APIs diversas.
     */
    public void cargarDatosIniciales() {
        integraciones.add(new PasarelaPago("API-PAY-01", "VisaNet / NeoNet", "ACTIVO", 120.5, "Endpoint: https://api.visanet.gt/v1, Auth: OAuth2"));
        integraciones.add(new PasarelaPago("API-PAY-02", "MasterCard Gateway", "ACTIVO", 95.0, "Endpoint: https://api.mastercard.com/pay, Auth: API-Key"));
        integraciones.add(new PasarelaPago("API-PAY-03", "PayPal Express", "INACTIVO", 210.3, "Endpoint: https://api.paypal.com/v2, Auth: Bearer Token"));
        
        integraciones.add(new ProveedorLogistica("API-LOG-01", "Cargo Expreso", "ACTIVO", 150.0, "Endpoint: https://cx.com.gt/api/v1, Formato: JSON"));
        integraciones.add(new ProveedorLogistica("API-LOG-02", "Guatex Courier", "ACTIVO", 95.0, "Endpoint: https://guatex.gt/services, Formato: XML"));
        integraciones.add(new ProveedorLogistica("API-LOG-03", "DHL Express Latam", "ACTIVO", 180.2, "Endpoint: https://dhl.com/api/shipments, Auth: Basic"));
        integraciones.add(new ProveedorLogistica("API-LOG-04", "PedidosYa Envíos", "ACTIVO", 85.4, "Endpoint: https://pya.express/api, Auth: OAuth2"));

        integraciones.add(new CertificadorFEL("API-FEL-01", "Infile S.A.", "ACTIVO", 110.0, "Endpoint: https://certificador.infile.com.gt, Certificado: X509"));
        integraciones.add(new CertificadorFEL("API-FEL-02", "GNSys Digital", "ACTIVO", 130.8, "Endpoint: https://gnsys.fel.com.gt/ws, Certificado: SSL"));
        integraciones.add(new CertificadorFEL("API-FEL-03", "DigiFact Guatemala", "ACTIVO", 95.0, "Endpoint: https://digifact.com.gt/api, Certificado: Token"));
    }

    /**
     * Inicia el bucle del programa.
     */
    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    listarIntegraciones();
                    break;
                case "2":
                    buscarIntegracion();
                    break;
                case "3":
                    ordenarIntegraciones();
                    break;
                case "4":
                    ejecutarOperacion();
                    break;
                case "5":
                    salir = true;
                    vista.mostrarMensaje("Finalizando el sistema de gestion de integraciones...");
                    break;
                default:
                    vista.mostrarMensaje("Opcion no valida. Por favor intente de nuevo.");
            }
        }
    }

    /**
     * Despliega la lista completa de integraciones registradas.
     */
    public void listarIntegraciones() {
        vista.mostrarIntegraciones(integraciones);
    }

    /**
     * Busca una API por su identificador unico y despliega sus parametros.
     */
    public void buscarIntegracion() {
        vista.mostrarMensaje("\nIngrese el ID de la API a buscar: ");
        String id = scanner.nextLine().trim();
        Integracion encontrada = buscarPorId(id);

        if (encontrada != null) {
            vista.mostrarMensaje("\n--- DETALLES DE LA API ENCONTRADA ---");
            vista.mostrarMensaje(encontrada.toString());
            vista.mostrarMensaje(encontrada.consultarParametros());
        } else {
            vista.mostrarMensaje("Error: No se encontro ninguna integracion con el ID '" + id + "'.");
        }
    }

    /**
     * Ordena el catalogo utilizando Comparable (latencia y desempate por ID).
     */
    public void ordenarIntegraciones() {
        Collections.sort(integraciones);
        vista.mostrarMensaje("\nCatalogo ordenado exitosamente por latencia (ascendente) y desempate por identificador.");
        listarIntegraciones();
    }

    /**
     * Ejecuta una operacion de forma segura mediante evaluacion de capacidades (ISP).
     */
    public void ejecutarOperacion() {
        vista.mostrarMensaje("\nIngrese el ID de la API sobre la cual desea operar: ");
        String id = scanner.nextLine().trim();
        Integracion api = buscarPorId(id);

        if (api == null) {
            vista.mostrarMensaje("Error: No existe una integración con ese ID.");
            return;
        }

        vista.mostrarMensaje("\nIntegracion seleccionada: " + api.getNombreProveedor() + " (" + api.getIdentificador() + ")");
        vista.mostrarSubMenuOperaciones();
        String subOpcion = scanner.nextLine().trim().toLowerCase();

        switch (subOpcion) {
            case "a":
                if (api instanceof Consultable) {
                    vista.mostrarMensaje(((Consultable) api).consultarEstado());
                } else {
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: El proveedor " + api.getNombreProveedor() + " no posee la capacidad de consulta de estado.");
                }
                break;

            case "b":
                if (api instanceof Cotizable) {
                    vista.mostrarMensaje(((Cotizable) api).cotizar());
                } else {
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: El proveedor " + api.getNombreProveedor() + " no posee la capacidad de realizar cotizaciones.");
                }
                break;

            case "c":
                if (api instanceof Transaccional) {
                    vista.mostrarMensaje("Ingrese el monto a cobrar: ");
                    try {
                        double monto = Double.parseDouble(scanner.nextLine().trim());
                        vista.mostrarMensaje(((Transaccional) api).cobrar(monto));
                    } catch (NumberFormatException e) {
                        vista.mostrarMensaje("Monto invalido.");
                    }
                } else {
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: El proveedor " + api.getNombreProveedor() + " no posee la capacidad de realizar cobros.");
                }
                break;

            case "d":
                if (api instanceof Transaccional) {
                    vista.mostrarMensaje("Ingrese el monto a reintegrar: ");
                    try {
                        double monto = Double.parseDouble(scanner.nextLine().trim());
                        vista.mostrarMensaje(((Transaccional) api).reintegrar(monto));
                    } catch (NumberFormatException e) {
                        vista.mostrarMensaje("Monto invalido.");
                    }
                } else {
                    // ISP en accion: Se informa amigablemente y no interrumpe el flujo ni lanza excepcion
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: La API de " + api.getNombreProveedor() + " no ofrece la capacidad de reintegro financiero.");
                }
                break;

            case "e":
                if (api instanceof Documentable) {
                    vista.mostrarMensaje(((Documentable) api).generarDocumento());
                } else {
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: El proveedor " + api.getNombreProveedor() + " no posee la capacidad de generar documentos.");
                }
                break;

            case "f":
                if (api instanceof Auditable) {
                    vista.mostrarMensaje("Ingrese el texto del evento a registrar: ");
                    String ev = scanner.nextLine().trim();
                    ((Auditable) api).registrarEvento(ev);
                    vista.mostrarMensaje("Evento registrado con éxito en la bitácora del servicio.");
                } else {
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: El proveedor " + api.getNombreProveedor() + " no requiere ni soporta bitacora de auditoria.");
                }
                break;

            case "g":
                if (api instanceof Auditable) {
                    vista.mostrarMensaje("\n--- HISTORIAL DE AUDITORIA DE " + api.getNombreProveedor() + " ---");
                    List<String> historial = ((Auditable) api).consultarHistorialEventos();
                    if (historial.isEmpty()) {
                        vista.mostrarMensaje("No hay eventos registrados en la bitácora.");
                    } else {
                        for (String h : historial) {
                            vista.mostrarMensaje(" - " + h);
                        }
                    }
                } else {
                    vista.mostrarMensaje("OPERACION NO DISPONIBLE: El proveedor " + api.getNombreProveedor() + " no posee historial de auditoria.");
                }
                break;

            default:
                vista.mostrarMensaje("Opcion invalida.");
        }
    }

    private Integracion buscarPorId(String id) {
        for (Integracion i : integraciones) {
            if (i.getIdentificador().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }
}
