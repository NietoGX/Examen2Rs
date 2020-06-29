package Excepciones;

public class ExcepcionYaExisteReceta extends Exception {
    public ExcepcionYaExisteReceta() {
        super("Ya existe una receta con ese nombre");
    }

}