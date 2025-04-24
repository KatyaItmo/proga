package command;

import collectionManager.CollectionManager;
import commandManager.ArgumentInterface;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для удаления элементов с ключами, превышающими заданный.
 * Реализует интерфейсы CommandInterface и ArgumentInterface для обработки аргументов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class RemoveGreater implements CommandInterface, ArgumentInterface {
    private Integer key;

    /**
     * Выполняет удаление элементов с ключами, превышающими заданный.
     * Проверяет корректность аргументов и существование элемента с заданным ключом.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            key = Integer.valueOf(tokens[1]);
            
            try {
                if (!checkKey(key)) throw new InvalidArgumentException("Элемента с этим ключом не существует!");
                
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Null коллекция!");
                    
                int number = manager.removeKeys(key, true);
                if (number >= 0) System.out.println(number + " элементов удалено.");
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
     * Выполняет удаление элементов при выполнении скрипта.
     * Делегирует выполнение основному методу execute.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void executeScript(String[] tokens) {
        execute(tokens);
    }

    /**
     * Возвращает описание команды.
     * 
     * @return Строка с описанием команды
     */
    @Override
    public String getDescription() {
        return "remove_greater_key [key]: удалить из коллекции все элементы, ключ которых превышает заданный.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * Команда должна иметь ровно один аргумент - ключ элемента.
     * 
     * @param length Количество аргументов
     * @return true если количество аргументов корректно
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
     * Проверяет существование элемента с заданным ключом в коллекции.
     * 
     * @param key Ключ для проверки
     * @return true если элемент существует в коллекции
     */
    @Override
    public boolean checkKey(Integer key) {
        CollectionManager manager = CollectionManager.getManager();
        if (manager.getCollection() == null) return false;
        if (manager.getCollection().containsKey(key)) return true;
        return false;
    }

    /**
     * Метод не реализован, так как команда remove_greater не требует чтения элементов.
     * 
     * @return null
     */
    @Override
    public String[] readElement() {
        return null;
    }

    /**
     * Метод не реализован, так как команда remove_greater не требует чтения элементов из скрипта.
     * 
     * @return null
     */
    @Override
    public String[] readScriptElement() {
        return null;
    }
}