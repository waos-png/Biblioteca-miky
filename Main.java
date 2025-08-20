package biblioteca;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Mostrar historial de préstamos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1: biblioteca.registrarLibro(); break;
                case 2: biblioteca.registrarUsuario(); break;
                case 3: biblioteca.prestarLibro(); break;
                case 4: biblioteca.devolverLibro(); break;
                case 5: biblioteca.mostrarLibrosDisponibles(); break;
                case 6: biblioteca.mostrarUsuarios(); break;
                case 7: biblioteca.mostrarHistorialPrestamos(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}