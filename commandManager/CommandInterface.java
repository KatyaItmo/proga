package commandManager;

/**
 * Интерфейс для команд, определяющий общий контракт поведения для всех команд.
 * Предоставляет методы для выполнения команд как в интерактивном режиме, так и в режиме скрипта.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public interface CommandInterface {
    /**
     * Выполняет команду в интерактивном режиме.
     * 
     * @param tokens массив аргументов командной строки
     */
    void execute(String[] tokens);

    /**
     * Выполняет команду в режиме скрипта.
     * 
     * @param tokens массив аргументов командной строки
     */
    void executeScript(String[] tokens);

    /**
     * Возвращает описание команды для справки.
     * 
     * @return строка с описанием команды
     */
    String getDescription();

    /**
     * Проверяет корректность количества аргументов команды.
     * 
     * @param lenght количество переданных аргументов
     * @return true если количество аргументов корректно, false иначе
     */
    boolean checkArguments(int lenght);
}