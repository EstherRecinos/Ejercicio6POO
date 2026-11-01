package model;

/**
 * Clase abstracta que representa la base para todas las integraciones de la plataforma.
 * Implementa la interfaz Comparable para definir el orden natural basado en latencia
 * y desempate por identificador.
 */
public abstract class Integracion implements Comparable<Integracion> {
    protected String identificador;
    protected String nombreProveedor;
    protected String estadoConexion;
    protected double latenciaPromedio;
    protected String parametrosConfiguracion;

    /**
     * Constructor principal de la clase Integracion.
     * @param identificador Clave unica de la API.
     * @param nombreProveedor Nombre de la empresa proveedora.
     * @param estadoConexion Estado inicial de la conexion (ej. ACTIVO, INACTIVO).
     * @param latenciaPromedio Latencia media en milisegundos.
     * @param parametrosConfiguracion Parametros de configuracion del servicio.
     */
    public Integracion(String identificador, String nombreProveedor, String estadoConexion, 
                       double latenciaPromedio, String parametrosConfiguracion) {
        this.identificador = identificador;
        this.nombreProveedor = nombreProveedor;
        this.estadoConexion = estadoConexion;
        this.latenciaPromedio = latenciaPromedio;
        this.parametrosConfiguracion = parametrosConfiguracion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getEstadoConexion() {
        return estadoConexion;
    }

    public double getLatenciaPromedio() {
        return latenciaPromedio;
    }

    public String getParametrosConfiguracion() {
        return parametrosConfiguracion;
    }

    /**
     * Devuelve los parametros de configuracion para la revision del administrador.
     * @return Cadena con la configuracion técnica.
     */
    public String consultarParametros() {
        return "Configuracion de [" + identificador + " - " + nombreProveedor + "]: " + parametrosConfiguracion;
    }

    /**
     * Establece el orden natural de las integraciones segun latencia ascendente.
     * En caso de empate en latencia, desempatan alfabeticamente por identificador.
     * @param otra Objeto Integracion a comparar.
     * @return Resultado entero de la comparacion.
     */
    @Override
    public int compareTo(Integracion otra) {
        int comparacionLatencia = Double.compare(this.latenciaPromedio, otra.latenciaPromedio);
        if (comparacionLatencia != 0) {
            return comparacionLatencia;
        }
        return this.identificador.compareTo(otra.identificador);
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s | Proveedor: %-20s | Estado: %-10s | Latencia: %6.2f ms",
                identificador, nombreProveedor, estadoConexion, latenciaPromedio);
    }
}
