import controller.ControladorIntegraciones;
import view.VistaConsola;

/**
 * Clase principal que ejecuta e inicia la aplicacion.
 */
public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        ControladorIntegraciones controlador = new ControladorIntegraciones(vista);
        controlador.iniciar();
    }
}
