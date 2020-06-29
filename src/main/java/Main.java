

import Controlador.ImplementacionControlador;
import Modelo.ImplementacionModelo;
import Vista.Vista;

public class Main {
    public static void main(String args[]) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        Vista vista = new Vista();
        ImplementacionModelo modelo = new ImplementacionModelo();
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.iniciaGUI();
    }
}
