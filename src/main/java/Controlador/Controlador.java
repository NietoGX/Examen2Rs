package Controlador;

import Excepciones.ExcepcionYaExisteReceta;
import Excepciones.NoExisteReceta;
import Modelo.Datos.Receta;
import Modelo.InterrogaModelo;

import java.util.List;

public interface Controlador extends InterrogaModelo {
    Receta crearReceta() throws ExcepcionYaExisteReceta;
    boolean eliminarReceta() throws NoExisteReceta;
    Receta getReceta();
    List<Receta> getRecetas();



}
