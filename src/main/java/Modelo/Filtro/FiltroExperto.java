package Modelo.Filtro;

import Excepciones.NoExisteReceta;
import Excepciones.NoExistenRecetas;
import Modelo.Datos.Dificultad;
import Modelo.Datos.Receta;

import java.util.ArrayList;
import java.util.List;

public class FiltroExperto implements Filtro{
    public List<Receta> filtrar(List<Receta> recetas) throws NoExistenRecetas {
        if(recetas.size()==0)
            throw new NoExistenRecetas();
        List<Receta> res= new ArrayList<Receta>();
        for(Receta t:recetas){
            if(t.getDificultad().equals(Dificultad.Experto))
                res.add(t);
        }
        return res;
    }

    public void setIngredientes(List<String> ingredientes) {

    }
}
