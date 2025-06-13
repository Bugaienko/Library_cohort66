package utils;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 19.05.2025
 */

public class MyArrayList<T> implements MyList<T> {

    private T[] array;
    private int cursor; // по умолчанию = 0

    // Методы, расширяющие функционал массива

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        // Стирание типов. Невозможно создать объект типа Т
        this.array = (T[]) new Object[10]; // [null, null...null]
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(T[] numbers) {

        if (numbers == null || numbers.length == 0) {
            this.array = (T[]) new Object[10];
        } else {
            // Создаю внутренний массив такой же размерности
            this.array = (T[]) new Object[numbers.length * 2];

            // 2. Все элементы копирую во внутренний массив
            addAll(numbers); // (T..numbers) может принять ссылку на массив T[]
        }
    }

    // Добавление в массив одного элемента
    public void add(T value) {

        // Проверка!
        // Есть ли свободное место во внутреннем массиве
        // Если места нет - место нужно добавить

        if (cursor == array.length) {
            // Расширить внутренний массив перед добавлением нового значения
            expandArray();
        }

        array[cursor] = value;
        cursor++;
    }

    // Динамическое расширение массива
    private void expandArray() {
        // System.out.println("Расширяем внутренний массив! Курсор = " + cursor);
        /*
        1. Создать новый массив бОльшего размера (в 2 раза больше)
        2. Переписать в новый массив все значения из старого массива (до курсора)
        3. Перебросить ссылку
         */

        // 1.
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[array.length * 2];

        // 2
        for (int i = 0; i < cursor; i++) {
            newArray[i] = array[i];
        }

        // Перебрасываю ссылку. Переменная (поле) array хранит ссылку на "новый" массив
        array = newArray;
    }


    @Override
    // Добавление в массив сразу нескольких значений
    public void addAll(T... numbers) {
        // с numbers можно обращаться точно также, как со ссылкой на массив
//        System.out.println("Принял несколько int: " + numbers.length);
//        System.out.println("значения: " + Arrays.toString(numbers));
//        System.out.println("У каждого есть индекс, как в массиве: " + numbers[0]);

        // Перебираю все значения. Для каждого вызываю метод add()
        for (int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }

    }

    // Возвращает строковое представление массива
    // [5, 20, 45, ]
    // "[10
    @Override
    public String toString() {
        // перебрать все "значимые" элементы = с индексами от 0 до cursor минус 1

        if (cursor == 0) return "[]";

        String result = "[";
        for (int i = 0; i < cursor; i++) {
            result += array[i] + (i < cursor - 1 ? ", " : "]");
        }

        return result;
    }

    // Текущее кол-во элементов в массиве
    public int size() {
        return cursor;
    }

    // Возвращает значение по индексу
    public T get(int index) {
        // Проверить входящий индекс

        if (index >= 0 && index < cursor) {
            return array[index];
        }

        // Код, если индекс не корректный
        return null;
    }

    // Удалить элемент по индексу. Возвращает старое значение.
    public T remove(int index) {
        /*
        1. Проверка индекса на валидность
            1.1. Запомнить старое значение
        2. Удалить значение по индексу
        3. Передвинуть курсор влево (т.к. кол-во элементов уменьшилось)
        4. Вернуть старое значение
         */

        if (index >= 0 && index < cursor) {
            // Логика удаления
            T value = array[index];

            // Перебираем элементы начиная с индекса и перезаписываем значением из ячейки справа
            for (int i = index; i < cursor - 1; i++) {
                array[i] = array[i + 1];
            }

            cursor--;

//            System.out.println("внутренний массив: " + Arrays.toString(array));

            return value;

        } else {
            // Индекс не валидный
            // Ничего не удаляем, но вернуть что-то нужно
            return null;
        }
    }

    // Поиск по значению. Первое вхождение
    // {1, 100, 5, 24, 0, 5} -> indexOf(5) ? индекс значения : отрицательно значение => 2
    public int indexOf(T value) {
        /*
        Перебираю все значимые элементы
        Если элемент равен искомому - вернуть индекс такого элемента
         */
        for (int i = 0; i < cursor; i++) {

//            if (array[i].equals(value)) {
            // null-безопасное сравнение
            if (Objects.equals(array[i], value)) {
                // Значения совпали. Возвращаю индекс:
                return i;
            }
        }

        // Сюда мы попадем, если ни одно значение в массиве не совпало с искомым
        return -1;
    }

    // Поиск последнего вхождения
    // {1, 100, 5, 24, 0, 5} -> lastIndexOf(5) =>
    public int lastIndexOf(T value) {
        for (int i = cursor - 1; i >= 0; i--) {
            if (Objects.equals(array[i], value)) return i;
        }

        // Перебрали все индексы, равный элемент не нашли
        return -1;
    }



    @Override
    // Удаление элемента по значению
    public boolean remove(T value) {
        // ПЕРЕИСПОЛЬЗУЙТЕ уже написанные методы
        /*
        1. Есть ли в массиве такой элемент
        2. Если нет - ничего не пытаемся удалить - возвращаем false
        3. Если найдем - удалить и вернуть true
         */

        int index = indexOf(value);
        if (index < 0) return false;

        // В эту строчку кода попадем только при index = 0 или больше
        remove(index);
        return true;
    }

    //  массив, состоящий из элементов магического массива
    @SuppressWarnings("unchecked")
    public T[] toArray() {

//        T[] result = (T[]) new Object[cursor];

        /*
        Взять какой-то объект из моего массива
        и узнать с помощью рефлексии тип этого объекта.
        Потом я могу создать массив этого типа.
         */

        if (cursor ==0) return null;

        Class<T> clazz = (Class<T>) array[0].getClass();
//        System.out.println("clazz: " + clazz);
//        System.out.println("array.class: " + array.getClass());

        // Создаю массив того же типа, что и 0-й элемент
        T[] result = (T[]) Array.newInstance(clazz, cursor);

        for (int i = 0; i < cursor; i++) {
            result[i] = array[i];
        }

        return result;
    }

    // Содержит ли массив элемент с таким значением
    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return cursor == 0;
    }

    // Перезаписывает значение по указанному индексу
    @Override
    public void set(int index, T value) {
        if (index >= 0 && index < cursor) {
            // Если индекс корректный, присваиваем новое значение
            array[index] = value;
        }
        //Если нет - действий не требуется
    }

    // Требует реализовать интерфейс Iterable
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < cursor;
        }

        @Override
        public T next() {
//            return  array[currentIndex++];
            T value = array[currentIndex];
            currentIndex++;
            return value;
        }
    }

} // End class

/*
0. Динамическое изменение размера внутреннего массива ++
1. Добавлять в массив элемент (не думаю об индексах, размере массива) ++
2. Добавить в массив сразу несколько значений ++
3. Возвратить строковое представление массива (все элементы массива в одной строке) ++
4. Текущее кол-во элементов в массиве ++

5. Возвращает значение по индексу ++
6. Удалить элемент по индексу (есть индекс - удалить элемент из этой ячейки). Возвращает старое значение.  ++
7. Удалять элемент по значению. Возвращает boolean. Если удалил - вернет true. Не нашел что удалять - false - ++
8. Поиск по значению. Первое вхождение ++
9. Поиск по значению. Последнее вхождение ++

10. Конструктор, принимающий обычный массив. Создать магический массив с элементами этого массива ++
12. Написать метод, который вернет массив, состоящий из элементов магического массива ++

 */