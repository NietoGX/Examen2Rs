package Modelo;

import Excepciones.NoExisteReceta;
import Modelo.Datos.Receta;

import java.util.List;

public interface InterrogaModelo {

    List<Receta> getRecetas();
}
