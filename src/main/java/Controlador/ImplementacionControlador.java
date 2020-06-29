package Controlador;

import Excepciones.ExcepcionYaExisteReceta;
import Excepciones.NoExisteReceta;
import Modelo.CambioModelo;
import Modelo.Datos.Receta;
import Vista.InterrogaVista;

import java.util.List;

public class ImplementacionControlador implements Controlador{
    private InterrogaVista vista;
    private CambioModelo modelo;
    public Receta crearReceta() throws ExcepcionYaExisteReceta {
        System.out.println(vista.getTexto());
        return modelo.crearReceta(vista.getTexto());
    }

    public boolean eliminarReceta() throws NoExisteReceta {
        System.out.println(vista.getTexto().getNombre());
        return modelo.eliminarTarea(vista.getTexto().getNombre());

    }

    public Receta getReceta() {
        return null;
    }



    public List<Receta> getRecetas() {
        return modelo.getRecetas();
    }
    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }
}
