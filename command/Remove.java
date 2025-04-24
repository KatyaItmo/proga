package command;

import collectionManager.CollectionManager;
import commandManager.ArgumentInterface;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для удаления элемента из коллекции по его ключу.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 1.0
 */
public class Remove implements CommandInterface, ArgumentInterface {
    private Integer key;

    /**
     * Выполняет команду удаления элемента из коллекции.
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
                
                CollectionManager manager = CollectionManager.getManager();
                if (manager.getCollection() == null) throw new NullCollectionException("Null коллекция!");
                
                manager.removeElement(key);
                System.out.println("Элемент удалён.");
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
     * Выполняет команду удаления в режиме скрипта.
     * 
     * @param tokens массив аргументов командной строки
     */
    @Override
    public void executeScript(String[] tokens) {
        execute(tokens);
    }

    /**
     * Возвращает описание команды для справки.
     * 
     * @return строка с описанием команды remove
     */
    @Override
    public String getDescription() {
        return "remove [key]: удалить элемент.";
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
     * Метод не реализован для команды remove, так как она не требует чтения данных.
     * 
     * @return null
     */
    @Override
    public String[] readElement() {
        return null;
    }

    /**
     * Метод не реализован для команды remove, так как она не требует чтения данных из скрипта.
     * 
     * @return null
     */
    @Override
    public String[] readScriptElement() {
        return null;
    }
}