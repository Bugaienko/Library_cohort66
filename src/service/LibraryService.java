package service;

import model.Book;
import model.User;
import utils.MyList;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.06.2025
 */

public interface LibraryService {

    Book createBook(String title, String author, int year);

    MyList<Book> getAllBooks();
    MyList<Book> getAllFreeBooks();
    MyList<Book> getAllBusyBooks();
    Book getBookById(int id);

    MyList<Book> getBooksByAuthor(String author);
    MyList<Book> getBooksByTitle(String title);
    MyList<Book> getBooksByStatus(boolean status);

    MyList<User> getAllUsers();
    User createUser(String email, String password);

    User login(String email, String password);
    void  logout();

    User getActiveUser();

    boolean takeBook(int bookId);
    boolean releaseBook(int bookId);



}
