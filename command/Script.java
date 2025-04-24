package command;

import java.io.FileNotFoundException;
import commandManager.CommandInterface;
import commandManager.InputScript;
import exceptions.InvalidArgumentException;
import exceptions.ScriptException;

/**
 * Команда для выполнения команд из скриптового файла.
 * Реализует интерфейс CommandInterface для обработки командной строки
 * и выполнения команд из файла.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Script implements CommandInterface {
    
    /**
     * Имя файла скрипта для выполнения.
     */
    private String fileName;

    /**
     * Выполняет команды из указанного скриптового файла.
     * Проверяет корректность аргументов и существование файла.
     * 
     * @param tokens массив строковых параметров команды
     * @throws InvalidArgumentException если количество аргументов некорректно
     * @throws FileNotFoundException если файл скрипта не найден
     */
    @Override
    public void execute(String[] tokens) {
        if (checkArguments(tokens.length)) {
            fileName = tokens[1];
            try {
                InputScript input = InputScript.getInput();
                input.setFileName(fileName);
                input.readCommands();
                input.closeScaner();
                System.out.println("Выполнение команд из скрипта завершено.");
            }
            catch(FileNotFoundException ex) {
                System.out.println("Файл скрипта не найден!");
                return;
            }
        }
    }

    /**
     * Выполняет команды из скрипта при выполнении из другого скрипта.
     * Проверяет наличие рекурсии и обрабатывает дополнительные исключения.
     * 
     * @param tokens массив строковых параметров команды
     */
    @Override
    public void executeScript(String[] tokens) {
        if (checkArguments(tokens.length)) {
            fileName = tokens[1];
            try {
                if (fileName == InputScript.getInput().getFileName()) 
                    throw new ScriptException("Рекурсия запрещена! Пропуск команды.");
                
                InputScript input = InputScript.getInput();
                input.setFileName(fileName);
                input.readCommands();
                input.closeScaner();
                System.out.println("Выполнение команд из скрипта завершено.");
            }
            catch(ScriptException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            catch(FileNotFoundException ex) {
                System.out.println("Файл скрипта не найден!");
                return;
            }
        }
    }

    /**
     * Возвращает краткое описание команды.
     * 
     * @return строка описания команды
     */
    @Override
    public String getDescription() {
        return "script [file_name]: выполнить команды из скрипта.";
    }

    /**
     * Проверяет корректность количества аргументов команды.
     * Команда должна иметь ровно один аргумент - имя файла скрипта.
     * 
     * @param length количество переданных аргументов
     * @return true если количество аргументов корректно, false если нет
     */
    @Override
    public boolean checkArguments(int length) {
        try {
            if (length < 2) throw new InvalidArgumentException("Недостаточно аргументов!");
            if (length > 2) throw new InvalidArgumentException("Лишние аргументы! Имя файла должно быть одним словом.");
        }
        catch(InvalidArgumentException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}