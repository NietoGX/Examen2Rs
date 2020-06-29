package Excepciones;

public class NoExisteReceta extends Exception {
    public NoExisteReceta() {
        super("No existe la receta");
    }
}
