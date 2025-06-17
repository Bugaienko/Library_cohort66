package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.06.2025
 */

public class BookRepositoryImpl<T extends Book> implements BookRepository<Book> {

    private final MyList<Book> books;

    private final AtomicInteger currentId = new AtomicInteger(1);
   // counter++;  counter = counter + 1;


    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        init();
    }

    private void init() {
        books.addAll(
                new Book(currentId.getAndIncrement(), "Эффективное программирование на Java", "Дж. Блох", 2018),
                new Book(currentId.getAndIncrement(), "Java. Полное руководство", "Герберт Шилдт", 2020),
                new Book(currentId.getAndIncrement(), "Java. Библиотека профессионала. Том 1. Основы", "К. Хорстман", 2019),
                new Book(currentId.getAndIncrement(), "Изучаем Java", "Кети Сьера", 2005),
                new Book(currentId.getAndIncrement(), "Java. Многопоточность в практике", "Дж. Ритц", 2011),
                new Book(currentId.getAndIncrement(), "Java. Руководство для начинающих", "Герберт Шилдт", 2019)
        );
        Book bookBusy = new Book(currentId.getAndIncrement(), "Чистый код. Создание, анализ и рефакторинг", "Роберт Мартин", 2008);
        bookBusy.setTaken(true);
        books.add(bookBusy);
    }

    @Override
    public Book addBook(String title, String author, int year) {
        return null;
    }

    @Override
    public boolean isBookExist(String title, String author, int year) {
        // TODO Vitaliy
        return false;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    @Override
    public MyList<Book> getBooksByTitle(String title) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) result.add(book);
        }
        return result;
    }

    @Override
    public MyList<Book> getBooksByAuthor(String author) {
        // TODO Elena
        return null;
    }

    @Override
    public MyList<Book> getBooksByStatus(boolean status) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.isTaken() == status) result.add(book);
        }
        return result;
    }

    @Override
    public void takeBook(Book book) {

    }

    @Override
    public void releaseBook(Book book) {

    }
}
