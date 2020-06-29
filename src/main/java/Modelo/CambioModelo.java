package Modelo;

import Excepciones.ExcepcionYaExisteReceta;
import Excepciones.NoExisteReceta;
import Modelo.Datos.Receta;

import java.util.List;

public interface CambioModelo {
    Receta crearReceta(Receta receta) throws ExcepcionYaExisteReceta;
    boolean eliminarTarea(String nombre) throws NoExisteReceta;
    List<Receta>  getRecetas();
}
