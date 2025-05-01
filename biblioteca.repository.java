//BibliotecaRepository.java

package biblioteca.repository;

import biblioteca.model.*;
import java.util.List;

public interface BibliotecaRepository {
    // Libros
    void agregarLibro(Libro libro);
    Libro buscarLibroPorId(int id);
    List<Libro> buscarLibros(String criterio, String valor);
    List<Libro> obtenerTodosLibros();
    void actualizarDisponibilidadLibro(int idLibro, boolean disponible);
    
    // Usuarios
    void agregarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int id);
    List<Usuario> obtenerTodosUsuarios();
    
    // Préstamos
    void registrarPrestamo(Prestamo prestamo);
    void actualizarPrestamo(Prestamo prestamo);
    List<Prestamo> obtenerPrestamosActivos();
    Prestamo buscarPrestamoActivoPorLibro(int idLibro);
}