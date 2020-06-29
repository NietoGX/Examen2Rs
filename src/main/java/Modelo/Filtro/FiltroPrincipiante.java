package Modelo.Filtro;

import Excepciones.NoExisteReceta;
import Excepciones.NoExistenRecetas;
import Modelo.Datos.Dificultad;
import Modelo.Datos.Receta;

import java.util.ArrayList;
import java.util.List;

public class FiltroPrincipiante implements Filtro {
    public List<Receta> filtrar(List<Receta> recetas) throws NoExistenRecetas {
        if(recetas.size()==0||recetas==null)
            throw new NoExistenRecetas();
        List<Receta> res= new ArrayList<Receta>();
        for(Receta t:recetas){
            if(t.getDificultad().equals(Dificultad.Principiante))
                res.add(t);
        }
        return res;
    }

    public void setIngredientes(List<String> ingredientes) {

    }
}
