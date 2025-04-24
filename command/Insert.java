package command;

import java.io.FileNotFoundException;
import collection.ColorEye;
import collection.ColorHair;
import collectionManager.CollectionManager;
import commandManager.ArgumentInterface;
import commandManager.CommandInterface;
import commandManager.Input;
import commandManager.InputScript;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;
import exceptions.ScriptException;

/**
 * Команда для добавления нового элемента в коллекцию.
 * Реализует интерфейсы CommandInterface и ArgumentInterface для обработки аргументов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Insert implements CommandInterface, ArgumentInterface {
    private String[] element;

    /**
     * Выполняет добавление нового элемента в коллекцию.
     * Проверяет корректность аргументов и состояние коллекции перед добавлением.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            try {
                element = readElement();
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Невозможно добавить элемент, null коллекция!");
                manager.insertToCollection(element);
                System.out.println("Элемент добавлен в коллекцию.");
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }
    }

    /**
     * Выполняет добавление элемента при выполнении скрипта.
     * Обрабатывает исключения, специфичные для работы со скриптами.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void executeScript(String[] tokens) {
        if (checkArguments(tokens.length)) {
            try {
                element = readScriptElement();
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Невозможно добавить элемент, null коллекция!");
                manager.insertToCollection(element);
                System.out.println("Элемент добавлен в коллекцию.");
            }
            catch(ScriptException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            catch(FileNotFoundException ex) {
                System.out.println("Файл не найден! Пропуск команды.");
                return;
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }
    }

    /**
     * Возвращает описание команды.
     * 
     * @return Строка с описанием команды
     */
    @Override
    public String getDescription() {
        return "insert: добавить новый элемент.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * Команда не должна иметь аргументов.
     * 
     * @param length Количество аргументов
     * @return true если количество аргументов корректно
     */
    @Override
    public boolean checkArguments(int length) {
        try {
            if (length != 1) throw new InvalidArgumentException("Лишние аргументы!");
        }
        catch(InvalidArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность ключа для обновления элемента.
     * 
     * @param key Ключ для проверки
     * @return true если ключ корректен
     */
    @Override
    public boolean checkKey(Integer key) {
        return true;
    }

    /**
     * Читает данные для создания нового элемента через консоль.
     * Предоставляет пользователю подсказки и валидацию ввода.
     * 
     * @return Массив строк с данными элемента
     */
    @Override
    public String[] readElement() {
        String eyeColor = "";
        String hairColor = "";
        for (ColorEye c : ColorEye.values()) {
            eyeColor = eyeColor + "[" + c + "] ";
        }
        for (ColorHair c : ColorHair.values()) {
            hairColor = hairColor + "[" + c + "] ";
        }
        String[] res = new String[11];
        Input input = Input.getInput();
        System.out.println("Id сгенерировано.");
        res[0] = input.readField("Введите имя: ", 0);
        System.out.println("Введите координаты");
        res[1] = input.readField("X: ", 1);
        res[2] = input.readField("Y (целое число > -273): ", 2);
        System.out.println("Дата создания сгенерирована.");
        res[3] = input.readField("Введите рост (целое число > 0): ", 3);
        res[4] = input.readField("Введите дату рождения в формате YYYY-MM-DD: ", 4);
        System.out.println("Введите цвет глаз из списка: " + eyeColor + "или 'skip' чтобы не заполнять поле.");
        res[5] = input.readField("Цвет глаз: ", 5);
        System.out.println("Введите цвет волос из списка: " + hairColor + "или 'skip' чтобы не заполнять поле.");
        res[6] = input.readField("Цвет волос: ", 6);
        System.out.println("Введите локацию или 'skip' чтобы не заполнять её.");
        res[7] = input.readField("X (целое число): ", 7);
        if (res[7] != null) {
            res[8] = input.readField("Y: ", 8);
            res[9] = input.readField("Z (целое число): ", 9);
            res[10] = input.readField("Название локации: ", 10);
        }
        return res;
    }

    /**
     * Читает данные для создания элемента из скрипта.
     * Бросает исключения при ошибках чтения или валидации.
     * 
     * @return Массив строк с данными элемента
     * @throws ScriptException если данные в скрипте некорректны
     * @throws FileNotFoundException если файл скрипта не найден
     */
    @Override
    public String[] readScriptElement() throws ScriptException, FileNotFoundException {
        String[] res = new String[11];
        InputScript input = InputScript.getInput();
        res[0] = input.readField(0);
        res[1] = input.readField(1);
        res[2] = input.readField(2);
        res[3] = input.readField(3);
        res[4] = input.readField(4);
        res[5] = input.readField(5);
        res[6] = input.readField(6);
        res[7] = input.readField(7);
        if (res[7] != null) {
            res[8] = input.readField(8);
            res[9] = input.readField(9);
            res[10] = input.readField(10);
        }
        return res;
    }
}