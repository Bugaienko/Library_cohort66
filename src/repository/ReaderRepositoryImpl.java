package repository;

import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.06.2025
 */

public class ReaderRepositoryImpl implements ReaderRepository {

    private final MyList<User> users;

    public ReaderRepositoryImpl() {
        this.users = new MyArrayList<>();
        initData();
    }

    private void initData() {
        User admin = new User("1", "1");
        admin.setRole(Role.ADMIN);
        users.add(admin);

        User test = new User("2", "2");

        User lib = new User("3", "3");
        lib.setRole(Role.LIBRARIAN);

        users.addAll(test, lib);

    }

    @Override
    public User saveUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public MyList<User> getAllUsers() {
        // TODO Elena
        return null;
    }

    @Override
    public boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return true;
        }
        return false;
    }
}
