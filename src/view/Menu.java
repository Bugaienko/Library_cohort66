package view;

import model.Book;
import model.Role;
import model.User;
import service.LibraryService;
import utils.MyList;

import java.util.Scanner;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.06.2025
 */

public class Menu {

    private final LibraryService service;
    private final Scanner scanner = new Scanner(System.in);

    private final static String COLOR_BLACK = "\u001B[0m";

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";

    public static final String COLOR_WHITE = "\u001B[37m";

    public Menu(LibraryService service) {
        this.service = service;
    }

    public void run() {
        showMainMenu();
    }

    private void showMainMenu() {
        while (true) {
            System.out.println(COLOR_GREEN + "Добро пожаловать в библиотеку");
            System.out.println("============= v 1.0 =========" + COLOR_RESET);
            System.out.println("1 -> Меню книг");
            System.out.println("2 -> Меню пользователей");
            System.out.println("3 -> Меню администратора");
            System.out.println("0 -> Выход");

            System.out.println("\nСделайте выбор:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания");
                System.exit(0);
            }

            showSubMenu(choice);
        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                User user = service.getActiveUser();
                if (user == null || user.getRole() != Role.ADMIN) {
                    System.out.println(COLOR_PURPLE + "Доступ только для администраторов" + COLOR_RESET);
                    waitRead();
                } else {
                    //TODO admin menu
//                showAdminMenu();
                }
                break;
            default:
                System.out.println(COLOR_RED + "Ваш выбор не корректен" + COLOR_RESET);
                waitRead();
        }
    }

    // ================= USER menu

    private void showUserMenu() {
        while (true) {
            System.out.println(COLOR_GREEN + "Меню пользователей" + COLOR_RESET);
            System.out.println("1 -> Авторизация в системе");
            System.out.println("2 -> Регистрация нового пользователя");
            System.out.println("3 -> Logout");
            System.out.println("4 -> Список всех пользователей");
            System.out.println("0 -> Возврат в предыдущее меню");

            //запрашиваем выбор пользователя
            System.out.println("\nСделайте выбор пункта:");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) break;
            handleUserMenuChoice(input);
        }
    }

    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                //Авторизация
                System.out.println(COLOR_CYAN + "Авторизация нового пользователя\n" + COLOR_RESET);
                System.out.println("Введите ваш email:");
                String email = scanner.nextLine();

                System.out.println("Введи Ваш пароль:");
                String password = scanner.nextLine();

                User user = service.login(email, password);

                if (user == null) {
                    System.out.println(COLOR_RED + "Не верный email или password" + COLOR_RESET);
                } else {
                    System.out.println(COLOR_GREEN + "Вы успешно авторизовались в системе!" + COLOR_RESET);
                }
                waitRead();
                break;
            case 2:
                //Регистрация
                System.out.println(COLOR_CYAN + "Регистрация нового пользователя\n" + COLOR_RESET);
                System.out.println("Введите ваш email:");
                String emailReg = scanner.nextLine();

                System.out.println("Введи Ваш пароль:");
                String passwordReg = scanner.nextLine();

                User registerUser = service.createUser(emailReg, passwordReg);
                if (registerUser == null) {
                    System.out.println(COLOR_RED + "Вы ввели некорректный email или password" + COLOR_RESET);
                } else {
                    System.out.println(COLOR_GREEN + "Вы успешно зарегистрировались в системе" + COLOR_RESET);
                    System.out.println("Для начала работу пройдите авторизацию");
                }
                waitRead();

                break;
            case 3:
                service.logout();
                waitRead();
                break;
            case 4:
                // Вывести список всех пользователей
                MyList<User> userList = service.getAllUsers();

                if (!userList.isEmpty()) {
                    for (User user1 : userList) {
                        System.out.println(user1);
                    }
                } else {
                    System.out.println("[]");
                }
                waitRead();
                break;
            default:
                System.out.println(COLOR_RED + "\nНе верный ввод" + COLOR_BLACK);
                waitRead();
        }
    }

    // ================ BOOK menu

    private void showBookMenu() {
        while (true) {
            System.out.println("=========== BOOK MENU ==========");
            System.out.println("1 -> Список всех книг");
            System.out.println("2 -> Список всех свободных книг");
            System.out.println("3 -> Список книг по названию");
            System.out.println("4 -> Список книг по автору");
            System.out.println("5 -> Взять книгу");
            System.out.println("6 -> Вернуть книгу");
            System.out.println("0 -> Вернуться в предыдущее меню");

            System.out.println("\nСделайте выбор");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 0) break;

            choiceBookMenuProcessing(input);
        }
    }

    private void choiceBookMenuProcessing(int input) {
        switch (input) {
            case 1:
                MyList<Book> books = service.getAllBooks();
                printBookList(books);
                waitRead();
                break;
            case 2:
                MyList<Book> books1 = service.getAllFreeBooks();
                printBookList(books1);
                waitRead();
                break;
            case 3:
                System.out.println("Введите название книги (полное или часть): ");
                String titlePart = scanner.nextLine();
                MyList<Book> books2 = service.getBooksByTitle(titlePart);
                printBookList(books2);
                waitRead();
                break;
            case 4:
                System.out.println("Введите автора книги (полное имя или часть");
                String authorPart = scanner.nextLine();
                MyList<Book> books3 = service.getBooksByAuthor(authorPart);
                printBookList(books3);
                waitRead();
                break;
            case 5:
                // TODO взятие книги
                System.out.println("Взятие книги");
                waitRead();
                break;
            case 6:
                // TODO вернуть книгу
                System.out.println("Вернуть книгу");
                waitRead();
                break;
            default:
                System.out.println("Неверный ввод!");
                waitRead();
                break;


        }
    }

    private void printBookList(MyList<Book> books) {
        for (Book book : books) {
            System.out.printf("%d. %s (%s) | %d\n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getYear());
        }
    }


    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter ...");
        scanner.nextLine();
    }


}
