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
 * Команда для замены элемента в коллекции по указанному ключу.
 * Реализует интерфейсы CommandInterface и ArgumentInterface для обработки командной строки
 * и проверки аргументов. Поддерживает как интерактивный ввод, так и выполнение из скрипта.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Replace implements CommandInterface, ArgumentInterface {
    
    /**
     * Ключ элемента для замены.
     */
    private Integer key;
    
    /**
     * Массив значений нового элемента.
     */
    private String[] element;

    /**
     * Выполняет замену элемента в коллекции по указанному ключу.
     * Проверяет корректность аргументов и существование элемента с заданным ключом.
     * 
     * @param tokens массив строковых параметров команды
     * @throws InvalidArgumentException если количество аргументов некорректно или ключ неверен
     * @throws NullCollectionException если коллекция пуста
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
                
                if (manager.checkElement(key, element)) manager.updateCollection(key, element);
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
     * Выполняет замену элемента при выполнении скрипта.
     * Обрабатывает дополнительные исключения, связанные с чтением из файла.
     * 
     * @param tokens массив строковых параметров команды
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
                
                manager.checkElement(key, element);
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
     * Возвращает краткое описание команды.
     * 
     * @return строка описания команды
     */
    @Override
    public String getDescription() {
        return "replace_if_greater [key]: заменить значение по ключу, если новое значение больше старого.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * Команда должна иметь ровно один аргумент - ключ элемента.
     * 
     * @param length количество переданных аргументов
     * @return true если количество аргументов корректно, false если нет
     */
    @Override
    public boolean checkArguments(int length) {
        try {
            if (length < 2) throw new InvalidArgumentException("Ключ не должен быть пустым!");
            if (length > 2) throw new InvalidArgumentException("Лишние аргументы!");
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
     * @param key проверяемый ключ
     * @return true если ключ существует в коллекции, false если нет
     */
    @Override
    public boolean checkKey(Integer key) {
        CollectionManager manager = CollectionManager.getManager();
        if (manager.getCollection() == null) return false;
        if (manager.getCollection().containsKey(key)) return true;
        return false;
    }

    /**
     * Читает данные для нового элемента из консоли.
     * Предоставляет пользователю выбор из перечислений ColorEye и ColorHair.
     * 
     * @return массив строковых значений нового элемента
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
        System.out.println("Введите цвет глаз из списка: " + eyeColor + " или 'skip' чтобы не заполнять поле.");
        res[5] = input.readField("Цвет глаз: ", 5);
        System.out.println("Введите цвет волос из списка: " + hairColor + " или 'skip' чтобы не заполнять поле.");
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
     * Выводит информацию о добавленных полях для отладки.
     * 
     * @return массив строковых значений нового элемента
     * @throws ScriptException если возникла ошибка при чтении из скрипта
     * @throws FileNotFoundException если файл скрипта не найден
     */
    @Override
    public String[] readScriptElement() throws ScriptException, FileNotFoundException {
        String[] res = new String[11];
        InputScript input = InputScript.getInput();
        
        res[0] = input.readField(0);
        System.out.println("Имя добавлено.");
        res[1] = input.readField(1);
        res[2] = input.readField(2);
        System.out.println("Координаты добавлены.");
        System.out.println("Дата создания сгенерирована.");
        res[3] = input.readField(3);
        System.out.println("Рост добавлен.");
        res[4] = input.readField(4);
        System.out.println("Дата рождения добавлена.");
        res[5] = input.readField(5);
        System.out.println("Цвет глаз добавлен.");
        res[6] = input.readField(6);
        System.out.println("Цвет волос добавлен.");
        res[7] = input.readField(7);
        if (res[7] != null) {
            res[8] = input.readField(8);
            res[9] = input.readField(9);
            res[10] = input.readField(10);
            System.out.println("Локация добавлена.");
        }
        
        return res;
    }
}