package model;

import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.06.2025
 */

public class User {

    // email - по сути выполняет роль id
    private String email;
    private String password;

    private Role role;
    private final MyList<Book> books;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.books = new MyArrayList<>();
        this.role = Role.READER;
    }

    @Override
    public String toString() {
        return "User {" +
                "email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MyList<Book> getBooks() {
        return books;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(role);
        return result;
    }
}
