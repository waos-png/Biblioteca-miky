package biblioteca;

import java.util.*;

public class Biblioteca {
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void registrarLibro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        libros.add(new Libro(titulo, autor, codigo));
        System.out.println("Libro registrado.");
    }

    void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("ID Usuario: ");
        String id = sc.nextLine();
        usuarios.add(new Usuario(nombre, id));
        System.out.println("Usuario registrado.");
    }

    void prestarLibro() {
        System.out.print("ID Usuario: ");
        String id = sc.nextLine();
        Usuario usuario = buscarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (usuario.librosPrestados >= 3) {
            System.out.println("Ya tiene 3 libros prestados.");
            return;
        }
        System.out.print("Código del libro: ");
        String codigo = sc.nextLine();
        Libro libro = buscarLibro(codigo);
        if (libro == null || !libro.disponible) {
            System.out.println("Libro no disponible.");
            return;
        }
        libro.marcarPrestado();
        usuario.librosPrestados++;
        System.out.println("Préstamo realizado.");
    }

    void devolverLibro() {
        System.out.print("ID Usuario: ");
        String id = sc.nextLine();
        Usuario usuario = buscarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        System.out.print("Código del libro: ");
        String codigo = sc.nextLine();
        Libro libro = buscarLibro(codigo);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
        libro.marcarDisponible();
        if (usuario.librosPrestados > 0) usuario.librosPrestados--;
        System.out.println("Devolución realizada.");
    }

    void mostrarLibrosDisponibles() {
        for (Libro libro : libros)
            if (libro.disponible) libro.mostrarDatos();
    }

    void mostrarUsuarios() {
        for (Usuario usuario : usuarios)
            usuario.mostrarDatos();
    }

    private Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios)
            if (u.idUsuario.equals(id)) return u;
        return null;
    }

    private Libro buscarLibro(String codigo) {
        for (Libro l : libros)
            if (l.codigo.equals(codigo)) return l;
        return null;
    }

    void cerrarScanner() {
        sc.close();
    }
}