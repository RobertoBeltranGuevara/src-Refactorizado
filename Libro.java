//Libro.java

package biblioteca.model;

public abstract class Libro {
    private final int id;
    private final String titulo;
    private final String autor;
    private final int anio;
    private final String genero;
    private boolean disponible;

    public Libro(int id, String titulo, String autor, int anio, String genero, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
        this.disponible = disponible;
    }
    
    public abstract String getTipo();

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }
    public String getGenero() { return genero; }
    public boolean isDisponible() { return disponible; }
    
    // Setter
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}