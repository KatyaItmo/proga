package command;

import collectionManager.CollectionManager;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для очистки коллекции.
 * Удаляет все элементы из коллекции, если она существует и не пуста.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Clear implements CommandInterface {
    /**
     * Выполняет очистку коллекции.
     * Проверяет корректность аргументов и состояние коллекции перед очисткой.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            CollectionManager manager = CollectionManager.getManager();
            try {
                if (manager.getCollection() == null) throw new NullCollectionException("Null коллекция!");
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            
            if (manager.getSize() == 0) {
                System.out.println("Коллекция уже пуста!");
            }
            else {
                manager.clearCollection();
                System.out.println("Коллекция очищена.");
            }
        }
    }

    /**
     * Выполняет команду при выполнении скрипта.
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
        return "clear: очистить коллекцию.";
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
            if (length != 1) throw new InvalidArgumentException("Аргументы не требуются!");
        }
        catch(InvalidArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
