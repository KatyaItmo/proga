package commandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import exceptions.ScriptException;

/**
 * Класс для обработки ввода из скриптов.
 * Реализует паттерн Singleton для обеспечения единого источника ввода из скрипта.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class InputScript implements InputInterface {
    private static final InputScript input = new InputScript();
    private String fileName;
    private Scanner sc;

    /**
     * Возвращает единственный экземпляр класса InputScript.
     * 
     * @return экземпляр InputScript
     */
    public static InputScript getInput() {
        return input;
    }

    /**
     * Устанавливает имя файла скрипта и инициализирует сканер для чтения.
     * 
     * @param fileName имя файла скрипта
     * @throws FileNotFoundException если файл не найден
     */
    public void setFileName(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        File scriptFile = new File(fileName);
        sc = new Scanner(scriptFile);
    }

    /**
     * Возвращает имя текущего файла скрипта.
     * 
     * @return имя файла скрипта
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Читает и выполняет команды из скрипта.
     * Продолжает работу до тех пор, пока есть строки в скрипте.
     */
    @Override
    public void readCommands() {
        CommandManager commandManager = new CommandManager();
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] tokens = line.split(" ");
            commandManager.executeScriptCommand(tokens);
        }
    }

    /**
     * Читает поле из скрипта с валидацией.
     * 
     * @param type тип валидации
     * @return введенное значение или null если указано "skip"
     * @throws ScriptException если произошла ошибка при чтении из скрипта
     */
    @Override
    public String readField(int type) throws ScriptException {
        Validator validator = new Validator();
        String[] str = null;
        if (sc.hasNext()) {
            String line = sc.nextLine().trim();
            str = line.split(" ");
            if (validator.ifValid(str, type)) {
                if (str[0].equals("skip")) return null;
                return str[0];
            }
            else throw new ScriptException("Невозможно прочитать элемент! Пропуск команды.");
        }
        else throw new ScriptException("Не обнаружена следующая строка! Пропуск команды.");
    }

    /**
     * Закрывает сканер для освобождения системных ресурсов.
     */
    @Override
    public void closeScaner() {
        sc.close();
    }

    /**
     * Метод не реализован для работы со скриптами.
     * 
     * @param message сообщение для пользователя
     * @param type тип валидации
     * @return null
     */
    @Override
    public String readField(String message, int type) {
        return null;
    }
}