import model.Book;
import repository.*;
import service.LibraryService;
import service.LibraryServiceImpl;
import view.Menu;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.06.2025
 */

public class LibraryApp {

    public static void main(String[] args) {

        BookRepository<Book> bookRepository = new BookRepositoryImpl<>();
        ReaderRepository readerRepository = new ReaderRepositoryImpl();


        LibraryService service = new LibraryServiceImpl(bookRepository, readerRepository);

        Menu menu = new Menu(service);
        menu.run();

    }
}
