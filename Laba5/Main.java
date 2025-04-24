/**
 * Главный класс приложения, отвечающий за запуск и управление программы.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */

package lab5;

import java.io.FileNotFoundException;

import commandManager.Input;
import exceptions.InputException;
import fileManager.XMLreader;

public class Main {
	/**
     * Имя файла по умолчанию для хранения коллекции.
     * Может быть переопределено через параметр командной строки.
     */
	private static String fileName = "collection.xml";

	/**
     * Точка входа в программу.
     * Обрабатывает параметры командной строки и запускает основной цикл работы программы.
     * 
     * @param args массив параметров командной строки
     * @throws InputException если передано более одного параметра
     */
	public static void main(String[] args) {
		System.out.println("Hello!");
		
		try {
			if (args.length == 1) fileName = args[0];
			if (args.length > 1) throw new InputException("Ошибка при распознавании имени файла.");
		}
		catch(InputException ex) {
			System.out.println(ex.getMessage());
			return;
		}
		
		XMLreader reader = new XMLreader();
		try {
			reader.readMapFromXML(fileName);
		}
		catch (FileNotFoundException ex) {
			System.out.println("XML файл с коллекцией не найден!");
		}
		catch (Exception ex) {
			System.out.println("Не удалось прочитать коллецию.");
		}
		
		System.out.println("Вы можете вводить команды. Используйте help для справки.");
		Input input = Input.getInput();
		input.readCommands();
		System.out.println("Программа завершена.");
		input.closeScaner();
	}

}
