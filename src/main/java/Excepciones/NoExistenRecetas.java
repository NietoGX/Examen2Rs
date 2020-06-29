package Excepciones;

public class NoExistenRecetas extends Exception {
    public NoExistenRecetas() {
        super("No existen recetas");
    }
}
