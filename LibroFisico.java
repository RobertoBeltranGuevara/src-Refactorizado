//LibroFisico.java

package biblioteca.model;

public class LibroFisico extends Libro {
    private final String ubicacion;

    public LibroFisico(int id, String titulo, String autor, int anio, String genero, boolean disponible, String ubicacion) {
        super(id, titulo, autor, anio, genero, disponible);
        this.ubicacion = ubicacion;
    }

    @Override
    public String getTipo() { return "Físico"; }
    
    public String getUbicacion() { return ubicacion; }
}