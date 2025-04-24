package command;

import collectionManager.CollectionManager;
import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;
import exceptions.NullCollectionException;

/**
 * Команда для вывода уникальных значений цвета волос из коллекции.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class UniqueHairColor implements CommandInterface {
    
    /**
     * Выполняет команду unique_hair для вывода уникальных цветов волос.
     * 
     * @param tokens массив аргументов командной строки
     * @throws InvalidArgumentException если количество аргументов некорректно
     * @throws NullCollectionException если коллекция равна null
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
                System.out.println("Коллекция пуста!");
            } else {
                manager.printUniqueHair();
            }
        
        }
    }

    /**
     * Выполняет команду в режиме скрипта.
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
     * @return строка с описанием команды unique_hair
     */
    @Override
    public String getDescription() {
        return "unique_hair: вывести уникальные значения цвета волос.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * Команда не должна принимать никаких аргументов.
     * 
     * @param lenght количество переданных аргументов
     * @return true если количество аргументов корректно, false иначе
     */
    @Override
    public boolean checkArguments(int lenght) {
        try {
            if (lenght != 1) throw new InvalidArgumentException("Аргументы не требуются!");
        }
        catch(InvalidArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}