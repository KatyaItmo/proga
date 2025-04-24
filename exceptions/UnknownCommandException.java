/**
 * Исключение, возникающее при попытке выполнить неизвестную команду.
 * Расширяет базовый класс Exception для обработки ситуаций, когда команда не распознана системой.
 *
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
package exceptions;

/**
 * Класс исключения для неизвестных команд.
 * Предназначен для обработки ситуаций, когда система встречает команду, которую не может распознать или обработать.
 */
public class UnknownCommandException extends Exception {
    private static final long serialVersionUID = -6521801332001232825L;
    
    /**
     * Сообщение об ошибке, описывающее неизвестную команду.
     */
    private String message;
    
    /**
     * Создает новое исключение с указанным сообщением об ошибке.
     * 
     * @param message текстовое описание неизвестной команды
     */
    public UnknownCommandException(String message) {
        this.message = message;
    }
    
    /**
     * Возвращает сообщение об ошибке, описывающее неизвестную команду.
     * 
     * @return текстовое описание ошибки
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}