package command;

import java.util.HashMap;

import commandManager.CommandInterface;
import commandManager.CommandManager;
import exceptions.InvalidArgumentException;

/**
 * Команда для вывода справки по доступным командам.
 * Отображает описание всех доступных команд в системе.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Help implements CommandInterface {
    /**
     * Выполняет вывод справки по всем доступным командам.
     * Проверяет корректность аргументов и выводит описание каждой команды.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            CommandManager commandManager = new CommandManager();
            HashMap<String, CommandInterface> commands = commandManager.getCommandMap();
            
            for (CommandInterface c : commands.values()) {
                System.out.println(c.getDescription());
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
        return "help: вывести справку по доступным командам.";
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