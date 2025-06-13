package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.ReaderRepository;
import utils.MyArrayList;
import utils.MyList;

/**
* @author Sergey Bugaenko
* {@code @date} 12.06.2025
*/

public class LibraryServiceImpl implements LibraryService {

    private final BookRepository<Book> bookRepository;
    private final ReaderRepository readerRepository;

    public LibraryServiceImpl(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }


    @Override
    public Book createBook(String title, String author, int year) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        //TODO Elena
        return null;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return bookRepository.getBooksByStatus(true);
    }

    @Override
    public Book getBookById(int id) {
        // TODO Elena
        return null;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        // TODO Elena
        return null;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        if (title == null || title.isEmpty()) return new MyArrayList<>();
        return bookRepository.getBooksByTitle(title);
    }

    @Override
    public MyList<Book> getBooksByStatus(boolean status) {
        return bookRepository.getBooksByStatus(status);
    }

    @Override
    public MyList<User> getAllUsers() {
        return null;
    }

    @Override
    public User createUser(String email, String password) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        // TODO Sergey
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public User getActiveUser() {
        // TODO Sergey
        return null;
    }

    @Override
    public boolean takeBook(int bookId) {
        return false;
    }

    @Override
    public boolean releaseBook(int bookId) {
        return false;
    }
}
