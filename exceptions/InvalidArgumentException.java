package exceptions;

/**
 * Исключение, возникающее при некорректных аргументах команды.
 * Используется для обработки ошибок валидации аргументов команд.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class InvalidArgumentException extends Exception {
    private static final long serialVersionUID = 816365171309674345L;
    private String message;

    /**
     * Создает исключение с указанным сообщением об ошибке валидации аргументов.
     * 
     * @param message текстовое описание ошибки валидации
     */
    public InvalidArgumentException(String message) {
        this.message = message;
    }

    /**
     * Возвращает сообщение об ошибке валидации аргументов.
     * 
     * @return текстовое описание ошибки
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}