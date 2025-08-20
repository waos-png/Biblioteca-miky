package biblioteca;

import java.util.ArrayList;

public class Usuario {
    String nombre, idUsuario;
    ArrayList<Libro> librosPrestados = new ArrayList<>();

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    void mostrarDatos() {
        System.out.println("Usuario: " + nombre + ", ID: " + idUsuario + ", Libros prestados: " + librosPrestados.size());
    }

    void agregarPrestamo(Libro libro) {
        if (librosPrestados.size() < 3) {
            librosPrestados.add(libro);
        }
    }

    void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }
}