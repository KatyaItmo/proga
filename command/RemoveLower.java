package command;

import collectionManager.CollectionManager;
import commandManager.ArgumentInterface;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для удаления элементов из коллекции, у которых ключ меньше указанного значения.
 * Реализует интерфейсы CommandInterface и ArgumentInterface для обработки командной строки
 * и проверки аргументов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class RemoveLower implements CommandInterface, ArgumentInterface {
    
    /**
     * Ключ для сравнения при удалении элементов.
     */
    private Integer key;

    /**
     * Выполняет удаление элементов из коллекции, у которых ключ меньше указанного значения.
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
                
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Неожиданная ошибка, null коллекция!");
                    
                int number = manager.removeKeys(key, false);
                if (number >= 0) System.out.println(number + " элементов удалено.");
            } catch(InvalidArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            } catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }
    }

    /**
     * Выполняет ту же логику, что и метод execute(), но в контексте выполнения скрипта.
     * 
     * @param tokens массив строковых параметров команды
     */
    @Override
    public void executeScript(String[] tokens) {
        execute(tokens);
    }

    /**
     * Возвращает краткое описание команды.
     * 
     * @return строка описания команды
     */
    @Override
    public String getDescription() {
        return "remove_lower_key [key]: удалить из коллекции все элементы, ключ которых меньше заданного.";
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
        } catch(InvalidArgumentException ex) {
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
     * Метод не реализован для данной команды.
     * 
     * @return всегда null
     */
    @Override
    public String[] readElement() {
        return null;
    }

    /**
     * Метод не реализован для данной команды.
     * 
     * @return всегда null
     */
    @Override
    public String[] readScriptElement() {
        return null;
    }
}