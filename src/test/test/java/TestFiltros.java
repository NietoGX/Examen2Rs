

import Excepciones.NoExistenRecetas;
import Modelo.Datos.Dificultad;


import Modelo.Datos.Receta;

import Modelo.Filtro.*;
import Modelo.ImplementacionModelo;
import org.junit.*;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestFiltros {

    private static ImplementacionModelo modelo;
    private static Receta principianteSal;
    private static Receta normalSalAceite;
    private static Receta expertoSal;
    private static Receta principiante;
    private static List<String> sal;
    private static List<String> salAceite;
    private static List<String> vacio;
    private static List<Receta> todas;
    private static List<Receta> principianteSol;
    private static List<Receta> normalSol;
    private static List<Receta> expertoSol;
    private static List<Receta> salSol;
    private static List<Receta> sinIngredientesSol;
    private static List<Receta> salAceiteSol;


    private ArrayList<Receta> recetasSol;
    private Filtro filtro;

    @BeforeClass
    public static void iniciaModelo() throws NoExistenRecetas {
        modelo= new ImplementacionModelo();
        sal= new ArrayList<String>();
        sal.add("sal");
        salAceite= new ArrayList<String>();
        salAceite.add("sal");
        salAceite.add("aceite");
        vacio= new ArrayList<String>();


        principiante= new Receta("Principiante", "Prueba",vacio);
        principiante.setDificultad(Dificultad.Principiante);
        principianteSal= new Receta("PrincipianteSal", "Prueba",sal);
        principianteSal.setDificultad(Dificultad.Principiante);
        normalSalAceite= new Receta("NormalSalAceite", "Prueba",salAceite);
        normalSalAceite.setDificultad(Dificultad.Normal);
        expertoSal=new Receta("ExpertoSal","Prueba", sal);
        expertoSal.setDificultad(Dificultad.Experto);


        todas=new ArrayList<Receta>();
        todas.add(principiante);
        todas.add(principianteSal);
        todas.add(normalSalAceite);
        todas.add(expertoSal);

        principianteSol= new ArrayList<Receta>();
        principianteSol.add(principianteSal);
        principianteSol.add(principiante);

        normalSol= new ArrayList<Receta>();
        normalSol.add(normalSalAceite);

        expertoSol=new ArrayList<Receta>();
        expertoSol.add(expertoSal);

        salSol=new ArrayList<Receta>();
        salSol.add(expertoSal);
        salSol.add(principianteSal);
        salSol.add(normalSalAceite);

        salAceiteSol=new ArrayList<Receta>();
        salAceiteSol.add(normalSalAceite);





    }
    @AfterClass
    public static void destruyeModelo(){
        modelo=null;
    }

//    @Test
//    public void testFiltros() throws ExcepcionNoExistenTareas {

//        assertEquals(alta.toString(), new FiltroPrioAlta().filtrar(tareas).toString());
//        assertEquals(media.toString(), new FiltroPrioMedia().filtrar(tareas).toString());
//        assertEquals(baja.toString(), new FiltroPrioBaja().filtrar(tareas).toString());
//        assertEquals(comp.toString(), new FiltroCompletada().filtrar(tareas).toString());
//        assertEquals(noComp.toString(), new FiltroNoCompletada().filtrar(tareas).toString());


//    }
    //Dado que en el anterior ejemplo comentado solo devuelve true si las 2 listas tienen el mismo orden, refactorizo el método equals
    //para que funcione correctamente el método contains
    @Test
    public void testFiltroPrincipiante() throws NoExistenRecetas {
        ArrayList<Receta> filtrado= (ArrayList<Receta>) new FiltroPrincipiante().filtrar(todas);
        for(Receta t: principianteSol){
            assertTrue(filtrado.contains(t));
        }
        for(Receta t:filtrado){
            assertTrue(principianteSol.contains(t));
        }
        assertTrue(principianteSol.size()==filtrado.size());
    }
    @Test
    public void testFiltroNormal() throws NoExistenRecetas {
        ArrayList<Receta> filtrado= (ArrayList<Receta>) new FiltroNormal().filtrar(todas);
        for(Receta t: normalSol){
            assertTrue(filtrado.contains(t));
        }
        for(Receta t:filtrado){
            assertTrue(normalSol.contains(t));
        }
        assertTrue(normalSol.size()==filtrado.size());
    }
    @Test
    public void testFiltroExperto() throws NoExistenRecetas {
        ArrayList<Receta> filtrado= (ArrayList<Receta>) new FiltroExperto().filtrar(todas);
        for(Receta t: expertoSol){
            assertTrue(filtrado.contains(t));
        }
        for(Receta t:filtrado){
            assertTrue(expertoSol.contains(t));
        }
        assertTrue(expertoSol.size()==filtrado.size());
    }
    @Test
    public void testFiltroSinIngredientes() throws NoExistenRecetas {
        Filtro f=new FiltroIngredientes();
        f.setIngredientes(vacio);
        ArrayList<Receta> filtrado= (ArrayList<Receta>) f.filtrar(todas);
        for(Receta t: todas){
            assertTrue(filtrado.contains(t));
        }
        for(Receta t:filtrado){
            assertTrue(todas.contains(t));
        }
        assertTrue(todas.size()==filtrado.size());

    }
    @Test
    public void testFiltroSal() throws NoExistenRecetas {
        Filtro f=new FiltroIngredientes();
        f.setIngredientes(sal);
        ArrayList<Receta> filtrado= (ArrayList<Receta>) f.filtrar(todas);
        for(Receta t: salSol){
            assertTrue(filtrado.contains(t));
        }
        for(Receta t:filtrado){
            assertTrue(salSol.contains(t));
        }
        assertTrue(salSol.size()==filtrado.size());

    }
    @Test
    public void testFiltroSalAceite() throws NoExistenRecetas {
        Filtro f=new FiltroIngredientes();
        f.setIngredientes(salAceite);
        ArrayList<Receta> filtrado= (ArrayList<Receta>) f.filtrar(todas);
        for(Receta t: salAceiteSol){
            assertTrue(filtrado.contains(t));
        }
        for(Receta t:filtrado){
            assertTrue(salAceiteSol.contains(t));
        }
        assertTrue(salAceiteSol.size()==filtrado.size());

    }
    @Test(expected = NoExistenRecetas.class)
    public void testExcepciones() throws NoExistenRecetas {
        new FiltroPrincipiante().filtrar(new ArrayList<Receta>());
        new FiltroNormal().filtrar(new ArrayList<Receta>());
        new FiltroExperto().filtrar(new ArrayList<Receta>());
        new FiltroIngredientes().filtrar(new ArrayList<Receta>());


    }
}
