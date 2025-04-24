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
 * Команда для обновления существующего элемента в коллекции.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Update implements CommandInterface, ArgumentInterface {
    private Integer key;
    private String[] element;

    /**
     * Выполняет команду обновления элемента в коллекции.
     * 
     * @param tokens массив аргументов командной строки
     * @throws InvalidArgumentException если ключ отсутствует или есть лишние аргументы
     * @throws NullCollectionException если коллекция равна null
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            key = Integer.valueOf(tokens[1]);
            
            try {
                if (!checkKey(key)) throw new InvalidArgumentException("Элемента с этим ключом не существует!"); 
                
                element = readElement();
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Ошибка, null коллекция!");
                
                manager.updateCollection(key, element);
                System.out.println("Значение было заменено.");
            }
            catch(InvalidArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }
    }

    /**
     * Выполняет команду обновления элемента в режиме скрипта.
     * 
     * @param tokens массив аргументов командной строки
     * @throws ScriptException если произошла ошибка при чтении из скрипта
     * @throws FileNotFoundException если файл не найден
     * @throws InvalidArgumentException если ключ отсутствует или есть лишние аргументы
     * @throws NullCollectionException если коллекция равна null
     */
    @Override
    public void executeScript(String[] tokens) {
        if (checkArguments(tokens.length)) {
            key = Integer.valueOf(tokens[1]);
            
            try {
                if (!checkKey(key)) throw new InvalidArgumentException("Элемент с этим ключом уже существует!");
                
                element = readScriptElement();
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Ошибка, null коллекция!");
                
                manager.updateCollection(key, element);
                System.out.println("Значение было заменено.");
            }
            catch(ScriptException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            catch(FileNotFoundException ex) {
                System.out.println("Файл не найден! Пропуск команды.");
                return;
            }
            catch(InvalidArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }
    }

    /**
     * Возвращает описание команды для справки.
     * 
     * @return строка с описанием команды update
     */
    @Override
    public String getDescription() {
        return "update [key]: обновить элемент.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * 
     * @param lenght количество переданных аргументов
     * @return true если количество аргументов корректно, false иначе
     */
    @Override
    public boolean checkArguments(int lenght) {
        try {
            if (lenght < 2) throw new InvalidArgumentException("Ключ не должен быть пустым!");
            if (lenght > 2) throw new InvalidArgumentException("Лишние аргументы!");
        }
        catch(InvalidArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Проверяет существование ключа в коллекции.
     * 
     * @param key ключ для проверки
     * @return true если элемент с таким ключом существует, false иначе
     */
    @Override
    public boolean checkKey(Integer key) {
        CollectionManager manager = CollectionManager.getManager();
        if (manager.getCollection() == null) return false;
        if (manager.getCollection().containsKey(key)) return true;
        return false;
    }

    /**
     * Читает данные для нового элемента из пользовательского ввода.
     * 
     * @return массив строк с данными элемента
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
     * Читает данные для нового элемента из скрипта.
     * 
     * @return массив строк с данными элемента
     * @throws ScriptException если произошла ошибка при чтении из скрипта
     * @throws FileNotFoundException если файл не найден
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