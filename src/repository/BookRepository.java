package repository;

import model.Book;
import utils.MyList;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.06.2025
 */

public interface BookRepository<T extends Book> {

    /*
    CRUD - операция
    C - create - создать
    R - read - получить / прочитать
    U - update - обновить существующий объект
    D - delete - удалить
     */

    // C
    T addBook(String title, String author, int year);
    boolean isBookExist(String title, String author, int year);

    // R
    MyList<T> getAllBooks();
    T getBookById(int id);

    MyList<T> getBooksByTitle(String title);
    MyList<T> getBooksByAuthor(String author);
    MyList<T> getBooksByStatus(boolean status);

    // U
    void  takeBook(T book);
    void releaseBook(T book);





}
