//BibliotecaApp.java

package biblioteca;

import biblioteca.repository.BibliotecaRepositoryImpl;
import biblioteca.service.BibliotecaServiceImpl;
import biblioteca.view.BibliotecaUI;

public class BibliotecaApp {
    public static void main(String[] args) {
        BibliotecaRepositoryImpl repository = new BibliotecaRepositoryImpl();
        BibliotecaServiceImpl service = new BibliotecaServiceImpl(repository);
        new BibliotecaUI(service).iniciar();
    }
}