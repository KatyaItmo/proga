package command;

import collectionManager.CollectionManager;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для сортировки элементов коллекции по цвету волос.
 * Реализует интерфейс CommandInterface для обработки команды сортировки.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class SortHairColor implements CommandInterface {
    
    /**
     * Выполняет сортировку элементов коллекции по цвету волос.
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
                if (manager.getCollection() == null) throw new NullCollectionException("Невозможно сортировать, null коллекция!");
                
                if (manager.getSize() == 0) {
                    System.out.println("Коллекция пуста!");
                }
                else {
                    manager.sortHair();
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
        return "sort_hair: вывести отсортированные значения цвета волос.";
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