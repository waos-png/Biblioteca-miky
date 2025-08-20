package biblioteca;

import java.util.*;

class Prestamo {
    Usuario usuario;
    Libro libro;
    Date fechaInicio;
    Date fechaLimite;
    Date fechaDevolucion;

    public Prestamo(Usuario usuario, Libro libro, Date fechaInicio, Date fechaLimite) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.fechaDevolucion = null;
    }
}

public class Biblioteca {
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Prestamo> prestamos = new ArrayList<>();
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
        if (usuario.librosPrestados.size() >= 3) {
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
        usuario.agregarPrestamo(libro);
        Date fechaInicio = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaInicio);
        cal.add(Calendar.DAY_OF_MONTH, 7); // 7 días de préstamo
        Date fechaLimite = cal.getTime();
        prestamos.add(new Prestamo(usuario, libro, fechaInicio, fechaLimite));
        System.out.println("Préstamo realizado. Fecha límite: " + fechaLimite);
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
        Prestamo prestamo = buscarPrestamo(usuario, libro);
        if (prestamo == null || prestamo.fechaDevolucion != null) {
            System.out.println("No hay préstamo activo para este libro.");
            return;
        }
        libro.marcarDisponible();
        usuario.devolverLibro(libro);
        Date fechaDevolucion = new Date();
        prestamo.fechaDevolucion = fechaDevolucion;
        long retraso = (fechaDevolucion.getTime() - prestamo.fechaLimite.getTime()) / (1000 * 60 * 60 * 24);
        if (retraso > 0) {
            long multa = retraso * 500;
            System.out.println("Devolución realizada. Multa por retraso: $" + multa);
        } else {
            System.out.println("Devolución realizada. Sin multa.");
        }
    }

    void mostrarHistorialPrestamos() {
        for (Prestamo p : prestamos) {
            System.out.println("Usuario: " + p.usuario.nombre + ", Libro: " + p.libro.titulo +
                ", Inicio: " + p.fechaInicio + ", Límite: " + p.fechaLimite +
                ", Devolución: " + (p.fechaDevolucion != null ? p.fechaDevolucion : "Pendiente"));
        }
    }

    private Prestamo buscarPrestamo(Usuario usuario, Libro libro) {
        for (Prestamo p : prestamos)
            if (p.usuario == usuario && p.libro == libro && p.fechaDevolucion == null)
                return p;
        return null;
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