//BibliotecaServiceImpl.java

package biblioteca.service;

import biblioteca.repository.BibliotecaRepository;
import biblioteca.model.*;
import biblioteca.util.BibliotecaException;
import java.util.Date;
import java.util.List;

public class BibliotecaServiceImpl implements BibliotecaService {
    private final BibliotecaRepository repository;
    
    public BibliotecaServiceImpl(BibliotecaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void prestarLibro(int idLibro, int idUsuario) throws BibliotecaException {
        Libro libro = repository.buscarLibroPorId(idLibro);
        Usuario usuario = repository.buscarUsuarioPorId(idUsuario);
        
        if (libro == null) throw new BibliotecaException("Libro no encontrado");
        if (usuario == null) throw new BibliotecaException("Usuario no encontrado");
        if (!libro.isDisponible()) throw new BibliotecaException("El libro no está disponible");
        
        List<Prestamo> prestamosUsuario = repository.obtenerPrestamosActivos().stream()
            .filter(p -> p.getIdUsuario() == idUsuario).toList();
        
        if (prestamosUsuario.size() >= usuario.getLimitePrestamos()) {
            throw new BibliotecaException("Límite de préstamos alcanzado");
        }
        
        Prestamo prestamo = new Prestamo(
            repository.obtenerPrestamosActivos().size() + 1,
            idLibro, idUsuario, new Date(), null, false
        );
        
        repository.registrarPrestamo(prestamo);
        repository.actualizarDisponibilidadLibro(idLibro, false);
    }

    // Resto de implementaciones...
}