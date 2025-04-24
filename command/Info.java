package command;

import collectionManager.CollectionManager;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для вывода информации о коллекции.
 * Отображает тип коллекции, дату создания и количество элементов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Info implements CommandInterface {
    /**
     * Выполняет вывод информации о коллекции.
     * Проверяет корректность аргументов и состояние коллекции перед выводом информации.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            CollectionManager manager = CollectionManager.getManager();
            try {
                if (manager.getCollection() == null) throw new NullCollectionException("Невозможно вывести информацию, null коллекция!");
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            
            System.out.println("Тип коллекции: " + manager.getType());
            System.out.println("Дата создания: " + manager.getDate());
            System.out.println("Количество элементов: " + manager.getSize());
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
        return "info: вывести информацию о коллекции.";
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