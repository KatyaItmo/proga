package commandManager;

import java.util.Scanner;
import exceptions.ScriptException;

/**
 * Класс для обработки пользовательского ввода.
 * Реализует паттерн Singleton для обеспечения единого источника ввода.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Input implements InputInterface {
    private static final Input input = new Input();
    private Scanner sc = new Scanner(System.in);

    /**
     * Возвращает единственный экземпляр класса Input.
     * 
     * @return экземпляр Input
     */
    public static Input getInput() {
        return input;
    }

    /**
     * Читает и выполняет команды из пользовательского ввода.
     * Продолжает работу до тех пор, пока есть вводимые данные.
     */
    @Override
    public void readCommands() {
        CommandManager commandManager = new CommandManager();
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] tokens = line.split(" ");
            commandManager.executeCommand(tokens);
        }
    }

    /**
     * Читает поле из пользовательского ввода с валидацией.
     * 
     * @param message сообщение для пользователя
     * @param type тип валидации
     * @return введенное значение или null если пользователь выбрал пропустить поле
     */
    @Override
    public String readField(String message, int type) {
        Validator validator = new Validator();
        String[] str = null;
        System.out.print(message);
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            str = line.split(" ");
            if (validator.ifValid(str, type)) {
                break;
            }
            System.out.print(message);
        }
        if (str[0].equals("skip")) return null;
        return str[0];
    }

    /**
     * Закрывает сканер для освобождения системных ресурсов.
     */
    @Override
    public void closeScaner() {
        sc.close();
    }

    /**
     * Метод для чтения поля из скрипта.
     * В текущей реализации не реализован.
     * 
     * @param type тип валидации
     * @return null
     * @throws ScriptException если произошла ошибка при чтении из скрипта
     */
    @Override
    public String readField(int type) throws ScriptException {
        return null;
    }
}