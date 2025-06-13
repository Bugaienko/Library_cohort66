package view;

import model.Book;
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

    public Menu(LibraryService service) {
        this.service = service;
    }

    public void run() {
        showMainMenu();
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("Добро пожаловать в библиотеку");
            System.out.println("============= v 1.0 =========");
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
                // TODO user menu
//                showUserMenu();
                break;
            case 3:
                //TODO admin menu
//                showAdminMenu();
                break;
            default:
                System.out.println("Ваш выбор не корректен");
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
