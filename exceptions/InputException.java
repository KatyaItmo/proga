package exceptions;

/**
 * Исключение, возникающее при некорректном пользовательском вводе.
 * Используется для обработки ошибок валидации данных.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class InputException extends Exception {
    private static final long serialVersionUID = 5162862696662929648L;
    private String message;

    /**
     * Создает исключение с указанным сообщением об ошибке.
     * 
     * @param message текстовое описание ошибки
     */
    public InputException(String message) {
        this.message = message;
    }

    /**
     * Возвращает сообщение об ошибке.
     * 
     * @return текстовое описание ошибки
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
