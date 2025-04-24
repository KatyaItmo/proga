package exceptions;

/**
 * Исключение, возникающее при работе с пустой коллекцией.
 * Используется для обработки ошибок при попытке выполнить операции над null коллекцией.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class NullCollectionException extends Exception {
    private static final long serialVersionUID = -6727187533706417534L;
    private String message;

    /**
     * Создает исключение с указанным сообщением об ошибке пустой коллекции.
     * 
     * @param message текстовое описание ошибки
     */
    public NullCollectionException(String message) {
        this.message = message;
    }

    /**
     * Возвращает сообщение об ошибке пустой коллекции.
     * 
     * @return текстовое описание ошибки
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}