import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    private static ArrayList<Libro> libros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Prestamo> prestamos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        // Agregar algunos datos de ejemplo
        inicializarDatos();
        
        boolean salir = false;
        while (!salir) {
            System.out.println("--- SISTEMA DE BIBLIOTECA ---");
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
            
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    registrarLibro();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    prestarLibro();
                    break;
                case 4:
                    devolverLibro();
                    break;
                case 5:
                    buscarLibros();
                    break;
                case 6:
                    mostrarLibros();
                    break;
                case 7:
                    mostrarUsuarios();
                    break;
                case 8:
                    mostrarPrestamosActivos();
                    break;
                case 9:
                    salir = true;
                    System.out.println("¡Sistema de biblioteca! sesión finalizada");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void inicializarDatos() {
        // Libros de ejemplo
        libros.add(new Libro(1, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Ficción", true));
        libros.add(new Libro(2, "Cien años de soledad", "Gabriel García Márquez", 1967, "Novela", true));
        libros.add(new Libro(3, "El principito", "Antoine de Saint-Exupéry", 1943, "Fábula", true));
        
        // Usuarios de ejemplo
        usuarios.add(new Usuario(101, "Jose Camacho", "jantonio@gmail.com", "123456789"));
        usuarios.add(new Usuario(102, "Patricia Moreno", "patricia@gmail.com", "987654321"));
    }

    private static void registrarLibro() {
        System.out.println("--- REGISTRAR NUEVO LIBRO ---");
        
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        
        Libro nuevoLibro = new Libro(id, titulo, autor, anio, genero, true);
        libros.add(nuevoLibro);
        
        System.out.println("Libro registrado con éxito.");
    }

    private static void registrarUsuario() {
        System.out.println("--- REGISTRAR NUEVO USUARIO ---");
        
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        Usuario nuevoUsuario = new Usuario(id, nombre, email, telefono);
        usuarios.add(nuevoUsuario);
        
        System.out.println("Usuario registrado con éxito.");
    }

    private static void prestarLibro() {
        System.out.println("--- PRESTAR LIBRO ---");
        
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        Libro libro = null;
        Usuario usuario = null;
        
        // Buscar libro
        for (Libro l : libros) {
            if (l.getId() == idLibro) {
                libro = l;
                break;
            }
        }
        
        // Buscar usuario
        for (Usuario u : usuarios) {
            if (u.getId() == idUsuario) {
                usuario = u;
                break;
            }
        }
        
        if (libro == null) {
            System.out.println("Error: Libro no encontrado.");
            return;
        }
        
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }
        
        if (!libro.isDisponible()) {
            System.out.println("Error: El libro no está disponible actualmente.");
            return;
        }
        
        // Verificar si el usuario tiene más de 3 libros prestados
        int librosUsuario = 0;
        for (Prestamo p : prestamos) {
            if (p.getIdUsuario() == idUsuario && !p.isDevuelto()) {
                librosUsuario++;
            }
        }
        
        if (librosUsuario >= 3) {
            System.out.println("Error: El usuario ya tiene 3 libros prestados.");
            return;
        }
        
        // Realizar el préstamo
        Date fechaPrestamo = new Date();
        Prestamo nuevoPrestamo = new Prestamo(prestamos.size() + 1, idLibro, idUsuario, fechaPrestamo, null, false);
        prestamos.add(nuevoPrestamo);
        
        // Marcar libro como no disponible
        libro.setDisponible(false);
        
        System.out.println("Préstamo realizado con éxito.");
    }

    private static void devolverLibro() {
        System.out.println("--- DEVOLVER LIBRO ---");
        
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        Prestamo prestamo = null;
        
        // Buscar préstamo activo para este libro
        for (Prestamo p : prestamos) {
            if (p.getIdLibro() == idLibro && !p.isDevuelto()) {
                prestamo = p;
                break;
            }
        }
        
        if (prestamo == null) {
            System.out.println("Error: No hay préstamos activos para este libro.");
            return;
        }
        
        // Marcar préstamo como devuelto
        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucion(new Date());
        
        // Marcar libro como disponible
        for (Libro l : libros) {
            if (l.getId() == idLibro) {
                l.setDisponible(true);
                break;
            }
        }
        
        System.out.println("Libro devuelto con éxito.");
    }

    private static void buscarLibros() {
        System.out.println("--- BUSCAR LIBROS ---");
        System.out.println("1. Buscar por título");
        System.out.println("2. Buscar por autor");
        System.out.println("3. Buscar por género");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("Ingrese término de búsqueda: ");
        String termino = scanner.nextLine().toLowerCase();
        
        boolean encontrado = false;
        
        System.out.println("Resultados:");
        for (Libro libro : libros) {
            boolean coincide = false;
            
            switch (opcion) {
                case 1:
                    coincide = libro.getTitulo().toLowerCase().contains(termino);
                    break;
                case 2:
                    coincide = libro.getAutor().toLowerCase().contains(termino);
                    break;
                case 3:
                    coincide = libro.getGenero().toLowerCase().contains(termino);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }
            
            if (coincide) {
                System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo() + 
                                 " | Autor: " + libro.getAutor() + " | Año: " + libro.getAnio() + 
                                 " | Género: " + libro.getGenero() + 
                                 " | Disponible: " + (libro.isDisponible() ?"Sí" : "No"));
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        }
    }

    private static void mostrarLibros() {
        System.out.println("--- LISTADO DE LIBROS ---");
        
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        for (Libro libro : libros) {
            System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo() + 
                             " | Autor: " + libro.getAutor() + " | Año: " + libro.getAnio() + 
                             " | Género: " + libro.getGenero() + 
                             " | Disponible: " + (libro.isDisponible() ?"Sí" : "No"));
        }
    }

    private static void mostrarUsuarios() {
        System.out.println("--- LISTADO DE USUARIOS ---");
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " | Nombre: " + usuario.getNombre() + 
                             " | Email: " + usuario.getEmail() + " | Teléfono: " + usuario.getTelefono());
        }
    }

    private static void mostrarPrestamosActivos() {
        System.out.println("--- PRÉSTAMOS ACTIVOS ---");
        
        boolean hayPrestamos = false;
        
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isDevuelto()) {
                Libro libro = null;
                Usuario usuario = null;
                
                // Buscar libro y usuario asociados
                for (Libro l : libros) {
                    if (l.getId() == prestamo.getIdLibro()) {
                        libro = l;
                        break;
                    }
                }
                
                for (Usuario u : usuarios) {
                    if (u.getId() == prestamo.getIdUsuario()) {
                        usuario = u;
                        break;
                    }
                }
                
                if (libro != null && usuario != null) {
                    System.out.println("ID Préstamo: " + prestamo.getId() + 
                                     " | Libro: " + libro.getTitulo() + 
                                     " | Usuario: " + usuario.getNombre() + 
                                     " | Fecha: " + prestamo.getFechaPrestamo());
                    hayPrestamos = true;
                }
            }
        }
        
        if (!hayPrestamos) {
            System.out.println("No hay préstamos activos.");
        }
    }

}
