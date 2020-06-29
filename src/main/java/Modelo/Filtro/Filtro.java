package Modelo.Filtro;

import Excepciones.NoExisteReceta;
import Excepciones.NoExistenRecetas;
import Modelo.Datos.Receta;

import java.util.List;

public interface  Filtro {
    List<Receta> filtrar(List<Receta> recetas) throws NoExistenRecetas;
    void setIngredientes(List<String> ingredientes);


}
