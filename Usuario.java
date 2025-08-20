package biblioteca;

public class Usuario {
    String nombre, idUsuario;
    int librosPrestados = 0;

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    void mostrarDatos() {
        System.out.println("Usuario: " + nombre + ", ID: " + idUsuario + ", Libros prestados: " + librosPrestados);
    }
}