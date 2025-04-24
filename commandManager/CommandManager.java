package commandManager;

import java.util.HashMap;

import command.*;
import exceptions.UnknownCommandException;

/**
 * Менеджер команд, отвечающий за хранение и выполнение всех доступных команд.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class CommandManager {
    
    private HashMap<String, CommandInterface> commands;
    
    /**
     * Инициализирует менеджер команд с полным набором доступных команд.
     */
    public CommandManager() {
        this.commands = new HashMap<>();
        
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("save", new Save());
        commands.put("exit", new Exit());
        commands.put("insert", new Insert());
        commands.put("update", new Update());
        commands.put("remove", new Remove());
        commands.put("script", new Script());
        commands.put("remove_greater_key", new RemoveGreater());
        commands.put("remove_lower_key", new RemoveLower());
        commands.put("sort_print", new SortPrint());
        commands.put("unique_hair", new UniqueHairColor());
        commands.put("sort_hair", new SortHairColor());
        commands.put("replace_if_greater", new Replace());
    }
    
    /**
     * Возвращает map всех доступных команд.
     * 
     * @return HashMap с командами
     */
    public HashMap<String, CommandInterface> getCommandMap() {
        return commands;
    }
    
    /**
     * Выполняет команду в интерактивном режиме.
     * 
     * @param tokens массив аргументов командной строки
     * @throws UnknownCommandException если введена неизвестная команда
     */
    public void executeCommand(String[] tokens) {
        CommandInterface command = commands.get(tokens[0]);
        
        try {
            if (command == null) throw new UnknownCommandException("Вы ввели неизвестную команду! Используйте help, чтобы посмотреть список доступных команд.");
            else {
                command.execute(tokens);
            }
        }
        catch(UnknownCommandException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Выполняет команду в режиме скрипта.
     * 
     * @param tokens массив аргументов командной строки
     * @throws UnknownCommandException если введена неизвестная команда
     */
    public void executeScriptCommand(String[] tokens) {
        CommandInterface command = commands.get(tokens[0]);
        
        try {
            if (command == null) throw new UnknownCommandException("Пропуск неизвестной команды.");
            else {
                command.executeScript(tokens);
            }
        }
        catch(UnknownCommandException ex) {
            System.out.println(ex.getMessage());
        }
    }
}