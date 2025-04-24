package exceptions;

/**
 * Исключение, возникающее при работе со скриптами.
 * Используется для обработки ошибок при выполнении команд из скриптов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class ScriptException extends Exception {
    private static final long serialVersionUID = -7914735846319214912L;
    private String message;

    /**
     * Создает исключение с указанным сообщением об ошибке скрипта.
     * 
     * @param message текстовое описание ошибки
     */
    public ScriptException(String message) {
        this.message = message;
    }

    /**
     * Возвращает сообщение об ошибке скрипта.
     * 
     * @return текстовое описание ошибки
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}