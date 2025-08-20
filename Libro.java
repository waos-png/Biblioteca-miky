package biblioteca;

public class Libro {
    String titulo, autor, codigo;
    boolean disponible = true;

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
    }

    void mostrarDatos() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Código: " + codigo + ", Disponible: " + disponible);
    }

    void marcarPrestado() { disponible = false; }
    void marcarDisponible() { disponible = true; }
}