package commandManager;

import exceptions.ScriptException;

/**
 * Интерфейс для обработки пользовательского ввода.
 * Определяет методы для чтения команд и полей ввода.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public interface InputInterface {
    /**
     * Читает и обрабатывает команды из пользовательского ввода.
     * 
     * @throws Exception если произошла ошибка при чтении команд
     */
    void readCommands();

    /**
     * Читает поле из пользовательского ввода с валидацией.
     * 
     * @param message сообщение для пользователя
     * @param type тип валидации
     * @return введенное значение
     */
    String readField(String message, int type);

    /**
     * Читает поле из скрипта.
     * 
     * @param type тип валидации
     * @return введенное значение
     * @throws ScriptException если произошла ошибка при чтении из скрипта
     */
    String readField(int type) throws ScriptException;

    /**
     * Закрывает ресурсы ввода.
     */
    void closeScaner();
}