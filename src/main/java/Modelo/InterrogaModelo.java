package Modelo;

import Excepciones.NoExisteReceta;
import Modelo.Datos.Receta;

import java.util.List;

public interface InterrogaModelo {
    Receta getReceta(String nombre) throws NoExisteReceta;
    List<Receta> getRecetas();
}
