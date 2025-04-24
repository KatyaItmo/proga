package fileManager;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

import collection.Person;

/**
 * Класс для записи коллекции объектов Person в XML файл.
 * Реализует форматированную запись всех полей объекта Person в XML структуру.
 *
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class XMLwriter {
	
	/**
     * Записывает коллекцию объектов Person в XML файл.
     * Создает форматированный XML документ со всеми полями объектов Person.
     * 
     * @param personMap карта объектов Person для записи
     * @param filename имя файла для записи
     * @throws FileNotFoundException если файл не может быть создан
     */
	public void writeMapToXML(HashMap<Integer, Person> personMap, String filename) throws FileNotFoundException {
		
		try (PrintWriter writer = new PrintWriter(filename)) {
			 writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			 writer.println("<collection>");
	         
			 for (HashMap.Entry<Integer, Person> entry : personMap.entrySet()) {
				 Person person = entry.getValue();
				 writer.printf("    <person>%n");
	             writer.printf("        <id>%d</id>%n", entry.getKey());
	             writer.printf("        <name>%s</name>%n", person.getName());
	             writer.printf("        <coordinates>%n");
	             writer.printf("            <x>%f</x>%n", person.getCoordinates().getX());
	             writer.printf("            <y>%d</y>%n", person.getCoordinates().getY());
	             writer.printf("        </coordinates>%n");
	             writer.printf("        <creationDate>%s</creationDate>%n", person.getCreationDate());
	             writer.printf("        <height>%d</height>%n", person.getHeight());
	                
	             if (person.getBirthday() != null) {
	            	 writer.printf("        <birthday>%s</birthday>%n", person.getBirthday());
	             }
	             if (person.getEyeColor() != null) {
	            	 writer.printf("        <eyeColor>%s</eyeColor>%n", person.getEyeColor());
	             }
	             if (person.getHairColor() != null) {
	             	writer.printf("        <hairColor>%s</hairColor>%n", person.getHairColor());
	             }
	             if (person.getLocation() != null) {
	             	writer.printf("        <location>%n");
	                writer.printf("            <x>%d</x>%n", person.getLocation().getX());
	                writer.printf("            <y>%f</y>%n", person.getLocation().getY());
	                writer.printf("            <z>%d</z>%n", person.getLocation().getZ());
	                writer.printf("            <name>%s</name>%n", person.getLocation().getName());
	                writer.printf("        </location>%n");
	             }
	             writer.println("    </person>");
			 }
			 writer.println("</collection>");
		}
	}
	
}
