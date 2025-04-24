package command;

import collectionManager.CollectionManager;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для отображения всех элементов коллекции.
 * Реализует интерфейс CommandInterface для обработки команды показа элементов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Show implements CommandInterface {
    
    /**
     * Выполняет отображение всех элементов коллекции.
     * Проверяет корректность аргументов и существование коллекции.
     * 
     * @param tokens массив строковых параметров команды
     * @throws InvalidArgumentException если переданы аргументы
     * @throws NullCollectionException если коллекция пуста
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            CollectionManager manager = CollectionManager.getManager();
            try {
                if (manager.getCollection() == null) throw new NullCollectionException("Null коллекция!");
                
                if (manager.getSize() == 0) {
                    System.out.println("Коллекция пуста!");
                }
                else {
                    manager.printCollection();
                }
            }
            catch (NullCollectionException ex) {
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
        return "show: вывести все элементы коллекции.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * Команда не должна иметь аргументов.
     * 
     * @param length количество переданных аргументов
     * @return true если количество аргументов корректно, false если нет
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