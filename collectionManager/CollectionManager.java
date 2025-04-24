package collectionManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import collection.*;

/**
 * Класс для управления коллекцией людей.
 * Предоставляет функционал для добавления, обновления, удаления и сортировки элементов.
 * Реализует паттерн Singleton для обеспечения единого экземпляра менеджера.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class CollectionManager {
    private static CollectionManager collectMan = new CollectionManager();
    private String creationDate = LocalDate.now().toString();
    private HashMap<Integer, Person> personMap = new HashMap<Integer, Person>();

    /**
     * Возвращает единственный экземпляр менеджера коллекции.
     * 
     * @return Единственный экземпляр CollectionManager
     */
    public static CollectionManager getManager() {
        return collectMan;
    }

    /**
     * Очищает коллекцию людей.
     */
    public void clearCollection() {
        personMap.clear();
    }

    /**
     * Добавляет нового человека в коллекцию.
     * Генерирует уникальный ключ на основе UUID.
     * 
     * @param newElement Массив строк с данными человека
     */
    public void insertToCollection(String[] newElement) {
        UUID randomUUID = UUID.randomUUID();
        Integer key = Math.abs(randomUUID.hashCode());
        Person element = makeElement(newElement);
        personMap.put(key, element);
    }

    /**
     * Обновляет существующую запись в коллекции.
     * 
     * @param key Ключ записи для обновления
     * @param newElement Массив строк с новыми данными
     */
    public void updateCollection(Integer key, String[] newElement) {
        Person element = makeElement(newElement);
        personMap.put(key, element);
    }

    /**
     * Проверяет, больше ли новый элемент текущего по сравнению.
     * 
     * @param key Ключ существующего элемента
     * @param newElement Массив строк с новыми данными
     * @return true если новый элемент больше существующего
     */
    public boolean checkElement(Integer key, String[] newElement) {
        Person element = makeElement(newElement);
        int res = element.compareTo(personMap.get(key));
        if (res > 0) return true;
        return false;
    }

    /**
     * Создает объект Person из массива строк.
     * 
     * @param newElement Массив строк с данными человека
     * @return Созданный объект Person
     */
    public Person makeElement(String[] newElement) {
        Person element = new Person();
        element.setName(newElement[0]);
        Coordinates newCoord = new Coordinates();
        newCoord.setX(Float.parseFloat(newElement[1]));
        newCoord.setY(Long.parseLong(newElement[2]));
        element.setCoordinates(newCoord);
        LocalDateTime genDate = LocalDateTime.now();
        element.setCreationDate(genDate);
        element.setHeight(Integer.parseInt(newElement[3]));
        if (newElement[4] == null) element.setBirthday(null);
        element.setBirthday(LocalDate.parse(newElement[4]));
        if (newElement[5] == null) element.setEyeColor(null);
        else element.setEyeColor(ColorEye.valueOf(newElement[5]));
        if (newElement[6] == null) element.setHairColor(null);
        else element.setHairColor(ColorHair.valueOf(newElement[6]));
        Location newLoc = new Location();
        if (newElement[6] == null) newLoc = null;
        else {
            newLoc.setX(Long.parseLong(newElement[7]));
            newLoc.setY(Float.parseFloat(newElement[8]));
            newLoc.setZ(Integer.parseInt(newElement[9]));
            newLoc.setName(newElement[10]);
        }
        element.setLocation(newLoc);
        return element;
    }

    /**
     * Возвращает всю коллекцию людей.
     * 
     * @return HashMap с коллекцией людей
     */
    public HashMap<Integer, Person> getCollection() {
        return personMap;
    }

    /**
     * Возвращает тип коллекции.
     * 
     * @return Строковое представление типа коллекции
     */
    public String getType() {
        return personMap.getClass().toString();
    }

    /**
     * Возвращает дату создания коллекции.
     * 
     * @return Строка с датой создания
     */
    public String getDate() {
        return creationDate;
    }

    /**
     * Возвращает количество элементов в коллекции.
     * 
     * @return Размер коллекции
     */
    public int getSize() {
        return personMap.size();
    }

    /**
     * Удаляет элемент из коллекции по ключу.
     * 
     * @param key Ключ элемента для удаления
     */
    public void removeElement(Integer key) {
        personMap.remove(key);
    }

    /**
     * Удаляет элементы с ключами меньше или больше заданного,
     * в зависимости от параметра sort.
     * 
     * @param key Ключ для сравнения
     * @param sort true - удалять ключи меньше заданного,
     *            false - удалять ключи больше заданного
     * @return Количество удаленных элементов
     */
    public int removeKeys(Integer key, boolean sort) {
        int res = 0;
        if (personMap == null) {
            return -1;
        }
        for (Integer k : personMap.keySet()) {
            if (sort) {
                if (k.compareTo(key) < 0) {
                    removeElement(k);
                    res++;
                }
            }
            else {
                if (k.compareTo(key) > 0) {
                    removeElement(k);
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * Сортирует коллекцию по ключам и выводит отсортированные элементы.
     */
    public void sortCollection() {
        List<Integer> sortedKeys = new ArrayList<>(personMap.keySet());
        Collections.sort(sortedKeys);
        for (Integer key : sortedKeys) {
            System.out.println("ключ: [" + key + "] = значение: " + personMap.get(key));
        }
    }

    /**
     * Сортирует и выводит все уникальные цвета волос в коллекции.
     */
    public void sortHair() {
        List<String> hair = new ArrayList<>();
        for (Person p : personMap.values()) {
            if (p.getHairColor() == null) continue;
            hair.add(p.getHairColor().toString());
        }
        Collections.sort(hair);
        System.out.println(hair);
    }

    /**
     * Выводит уникальные цвета волос, которые встречаются только один раз.
     */
    public void printUniqueHair() {
        HashMap<String, Integer> uniqueHair = new HashMap<>();
        for (Entry<Integer, Person> entry : personMap.entrySet()) {
            if (personMap.get(entry.getKey()).getHairColor() == null) continue;
            if (uniqueHair.get(entry.getValue().getHairColor().toString()) == null) 
                uniqueHair.put(entry.getValue().getHairColor().toString(), 1);
            else uniqueHair.put(entry.getValue().getHairColor().toString(), 
                uniqueHair.get(entry.getValue().getHairColor().toString()) + 1);
        }
        for (String s : uniqueHair.keySet()) {
            if (uniqueHair.get(s) == 1) {
                System.out.println(s);
            }
        }
    }

    /**
     * Выводит все элементы коллекции.
     */
    public void printCollection() {
        for (Entry<Integer, Person> entry : personMap.entrySet()) {
            System.out.println("ключ: [" + entry.getKey() + "] = значение: " + entry.getValue());
        }
    }

    /**
     * Устанавливает новую коллекцию.
     * 
     * @param map Новая коллекция для установки
     */
    public void setCollection(HashMap<Integer, Person> map) {
        personMap = map;
    }
}
