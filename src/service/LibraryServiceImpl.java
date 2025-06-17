package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.ReaderRepository;
import utils.MyArrayList;
import utils.MyList;
import utils.UserValidator;

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
        MyList<Book> allBooks = bookRepository.getAllBooks();
        MyList<Book> freeBooks = new MyArrayList<>();

        for (Book book : allBooks){
            if (!book.isTaken()){
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return bookRepository.getBooksByStatus(true);
    }

    @Override
    public Book getBookById(int id) {
        if (id <= 0) {
            return null;
        }
        return bookRepository.getBookById(id);
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        if (author == null || author.isEmpty()) return new MyArrayList<>();
        return bookRepository.getBooksByAuthor(author);
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
        User user = null;

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            // Сервис не должен писать сообщения "клиенту"
//            System.out.println("Что-то не так с email / password");
            return null;
        }
        if (readerRepository.isEmailExists(email)) {
            // не должны писать, но здесь по другому не получится с текущими знаниями
            System.out.println("Пользователь с таким именем существует");
            return null;
        }

        // Надо проверить email и пароль на соответствие требованиям.
        if (UserValidator.isEmailValid(email) && UserValidator.isPasswordValid(password)) {
            // пользователя можно регистрировать
            user = readerRepository.saveUser(email, password);
            return user;
        }

        // email и/или пароль не прошли валидацию
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
