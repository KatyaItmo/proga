package command;

import collectionManager.CollectionManager;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;
import fileManager.XMLwriter;

/**
 * Команда для сохранения коллекции в XML файл.
 * Реализует интерфейс CommandInterface для обработки команды сохранения.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Save implements CommandInterface {
    
    /**
     * Выполняет сохранение коллекции в XML файл.
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
                if (manager.getCollection() == null) throw new NullCollectionException("Невозможно сохранить, null коллекция!");
                
                XMLwriter writer = new XMLwriter();
                writer.writeMapToXML(manager.getCollection(), "collection.xml");
                System.out.println("XML файл успешно создан!");
            }
            catch (NullCollectionException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            catch (Exception e) {
                System.out.println("Ошибка при создании файла.");
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
        return "save: сохранить коллекцию в файл.";
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