package Modelo.Datos;

import java.util.List;

public class Receta {

    String nombre;
    String preparacion;
    List<String> ingredientes;
    Tipo tipo;
    Dificultad dificultad;

    public Receta(String nombre, String preparacion, List<String> ingredientes) {
        this.nombre = nombre;
        this.preparacion = preparacion;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", preparacion='" + preparacion + '\'' +
                ", ingredientes=" + ingredientes +
                ", tipo=" + tipo +
                ", dificultad=" + dificultad +
                '}';
    }
}
