package commandManager;

import java.io.FileNotFoundException;
import exceptions.ScriptException;

/**
 * Интерфейс для обработки аргументов команд.
 * Предоставляет методы для проверки ключей и чтения данных как из пользовательского ввода, так и из скриптов.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public interface ArgumentInterface {
    /**
     * Проверяет существование ключа в коллекции.
     * 
     * @param key ключ для проверки
     * @return true если элемент с таким ключом существует, false иначе
     */
    boolean checkKey(Integer key);

    /**
     * Читает данные для нового элемента из пользовательского ввода.
     * 
     * @return массив строк с данными элемента
     */
    String[] readElement();

    /**
     * Читает данные для нового элемента из скрипта.
     * 
     * @return массив строк с данными элемента
     * @throws ScriptException если произошла ошибка при чтении из скрипта
     * @throws FileNotFoundException если файл не найден
     */
    String[] readScriptElement() throws ScriptException, FileNotFoundException;
}
