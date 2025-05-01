//BibliotecaUI.java

package biblioteca.view;

import biblioteca.service.BibliotecaService;
import biblioteca.util.BibliotecaException;
import java.util.Scanner;

public class BibliotecaUI {
    private final BibliotecaService service;
    private final Scanner scanner;
    
    public BibliotecaUI(BibliotecaService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            try {
                switch (opcion) {
                    case 1 -> registrarLibro();
                    case 2 -> registrarUsuario();
                    case 3 -> prestarLibro();
                    case 4 -> devolverLibro();
                    case 5 -> buscarLibros();
                    case 6 -> mostrarTodosLibros();
                    case 7 -> mostrarTodosUsuarios();
                    case 8 -> mostrarPrestamosActivos();
                    case 9 -> salir = true;
                    default -> System.out.println("Opción no válida.");
                }
            } catch (BibliotecaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("¡Sistema de biblioteca! Sesión finalizada");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
        System.out.println("1. Registrar nuevo libro");
        System.out.println("2. Registrar nuevo usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Buscar libros");
        System.out.println("6. Ver todos los libros");
        System.out.println("7. Ver todos los usuarios");
        System.out.println("8. Ver préstamos activos");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    // Resto de métodos de interacción...
}