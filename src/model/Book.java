package model;

import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.06.2025
 */

public class Book {

    private final int id;

    private String title;
    private String author;
    private int year;

    private boolean isTaken;
    private User user;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", isTaken=" + isTaken +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;
        return id == book.id && year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(author);
        result = 31 * result + year;
        return result;
    }
}
