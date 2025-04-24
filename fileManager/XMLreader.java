package fileManager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import collection.ColorEye;
import collection.ColorHair;
import collection.Coordinates;
import collection.Location;
import collection.Person;
import collectionManager.CollectionManager;

/**
 * Класс для чтения данных из XML файла и преобразования их в коллекцию объектов Person.
 * Реализует парсинг XML документа с использованием DOM парсера.
 *
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class XMLreader {

	/**
     * Читает XML файл и создает карту объектов Person на основе его содержимого.
     * 
     * @param filename путь к XML файлу для чтения
     * @throws ParserConfigurationException если возникла ошибка конфигурации парсера
     * @throws FileNotFoundException если файл не найден
     * @throws IOException при ошибке ввода-вывода
     * @throws SAXException при ошибке разбора XML
     */
	public void readMapFromXML(String filename) throws ParserConfigurationException, FileNotFoundException, IOException, SAXException {
		
		HashMap<Integer, Person> personMap = new HashMap<>();
		
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename))) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(bis);
            
            Element root = document.getDocumentElement();
            NodeList people = root.getElementsByTagName("person");
            
            for (int i = 0; i < people.getLength(); i++) {
            	Node personNode = people.item(i);
            	
            	if (personNode.getNodeType() == Node.ELEMENT_NODE) {
            		Element personElement = (Element) personNode;
            		Integer key = Integer.parseInt(personElement.getElementsByTagName("id").item(0).getTextContent());
            		Person person = parsePersonElement(personElement);
            		personMap.put(key, person);
            	}
            	
            }
		}
		
		CollectionManager manager = CollectionManager.getManager();
		manager.setCollection(personMap);
		
	}
	
	/**
     * Преобразует XML элемент в объект Person.
     * Обрабатывает все необходимые поля включая координаты и локацию.
     * 
     * @param personElement XML элемент содержащий данные о человеке
     * @return объект Person со всеми заполненными полями
     */
	private Person parsePersonElement(Element personElement) {
        String name = personElement.getElementsByTagName("name").item(0).getTextContent();
        
        Element coordinatesElement = (Element) personElement.getElementsByTagName("coordinates").item(0);
        Coordinates coordinates = new Coordinates(
            Float.parseFloat(coordinatesElement.getElementsByTagName("x").item(0).getTextContent().replace(',', '.')),
            (long)Long.parseLong(coordinatesElement.getElementsByTagName("y").item(0).getTextContent())
        );
        
        int height = Integer.parseInt(personElement.getElementsByTagName("height").item(0).getTextContent());
        
        Person person = new Person(name, coordinates, height);
        
        NodeList birthdayNodes = personElement.getElementsByTagName("birthday");
        if (birthdayNodes.getLength() > 0) {
            person.setBirthday(LocalDate.parse(birthdayNodes.item(0).getTextContent()));
        }
        
        NodeList eyeColorNodes = personElement.getElementsByTagName("eyeColor");
        if (eyeColorNodes.getLength() > 0) {
            person.setEyeColor(ColorEye.valueOf(eyeColorNodes.item(0).getTextContent()));
        }
        
        NodeList hairColorNodes = personElement.getElementsByTagName("hairColor");
        if (hairColorNodes.getLength() > 0) {
            person.setHairColor(ColorHair.valueOf(hairColorNodes.item(0).getTextContent()));
        }
        
        NodeList locationNodes = personElement.getElementsByTagName("location");
        if (locationNodes.getLength() > 0) {
            Element locationElement = (Element) locationNodes.item(0);
            person.setLocation(new Location(
                Long.parseLong(locationElement.getElementsByTagName("x").item(0).getTextContent()),
                Float.parseFloat(locationElement.getElementsByTagName("y").item(0).getTextContent().replace(',', '.')),
                Integer.parseInt(locationElement.getElementsByTagName("z").item(0).getTextContent()),
                personElement.getElementsByTagName("name").item(1).getTextContent()
            ));
        }
        
		return person;
	}

}
