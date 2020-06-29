package Controlador;

import Excepciones.ExcepcionYaExisteReceta;
import Excepciones.NoExisteReceta;
import Modelo.Datos.Receta;

import java.util.List;

public interface Controlador {
    Receta crearReceta() throws ExcepcionYaExisteReceta;
    boolean eliminarReceta() throws NoExisteReceta;
    Receta getReceta();
    List<Receta> getRecetas();



}
