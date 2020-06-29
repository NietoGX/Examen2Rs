package Modelo.Filtro;

import Excepciones.NoExisteReceta;
import Excepciones.NoExistenRecetas;
import Modelo.Datos.Dificultad;
import Modelo.Datos.Receta;
import Vista.InterrogaVista;

import java.util.ArrayList;
import java.util.List;

public class FiltroIngredientes implements Filtro{
    List<String> ingredientes;
    public List<Receta> filtrar(List<Receta> recetas) throws NoExistenRecetas {
        List<Receta> res= new ArrayList<Receta>();
        res.addAll(recetas);
        if(recetas.size()==0)
            throw new NoExistenRecetas();
        if(ingredientes.toString().equals("[]")){
            return recetas;
        }
        for(Receta r: recetas){

            for(String s: ingredientes){
                if(!r.getIngredientes().contains(s)){
                    res.remove(r);
                }
            }
        }

        return res;



    }
    public void setIngredientes(List<String> ingredientes){
        this.ingredientes= ingredientes;
    }
}
