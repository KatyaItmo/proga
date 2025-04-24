package command;

import commandManager.CommandInterface;
import exceptions.InvalidArgumentException;

/**
 * Команда для завершения работы программы.
 * Завершает выполнение программы без сохранения данных.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Exit implements CommandInterface {
    /**
     * Выполняет завершение работы программы.
     * Проверяет корректность аргументов перед завершением.
     * 
     * @param tokens Массив строковых аргументов команды
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            System.out.println("Вы завершили работу программы. Goodbye!");
            System.exit(0);
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
        return "exit: завершить программу (без сохранения в файл).";
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