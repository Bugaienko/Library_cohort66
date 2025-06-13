package repository;

import model.User;
import utils.MyList;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.06.2025
 */

public interface ReaderRepository {
    // CRUD

    User saveUser(String email, String password);

    User getUserByEmail(String email);
    MyList<User> getAllUsers();

    boolean isEmailExists(String email);

    // Метод взятия и отдачи книги




}
