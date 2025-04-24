package collection;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс для представления человека с различными характеристиками.
 * Реализует интерфейс Comparable для сравнения людей по различным параметрам.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 1.0
 */
public class Person implements Comparable<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int height; //Значение поля должно быть больше 0
    private LocalDate birthday; //Поле может быть null
    private ColorEye eyeColor; //Поле может быть null
    private ColorHair hairColor; //Поле может быть null
    private Location location; //Поле может быть null

    /**
     * Создает нового человека со всеми возможными характеристиками.
     * 
     * @param name Имя человека (не может быть null или пустым)
     * @param coordinates Координаты человека (не может быть null)
     * @param creationDate Дата создания записи (не может быть null)
     * @param height Рост человека (должен быть больше 0)
     * @param birthday Дата рождения (может быть null)
     * @param eyeColor Цвет глаз (может быть null)
     * @param hairColor Цвет волос (может быть null)
     * @param location Местоположение (может быть null)
     */
    public Person(String name, Coordinates coordinates, LocalDateTime creationDate, int height, 
                 LocalDate birthday, ColorEye eyeColor, ColorHair hairColor, Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
    }

    /**
     * Создает пустого человека с null значениями.
     * Используется для инициализации через сеттеры.
     */
    public Person() {}

    /**
     * Создает человека с минимальным набором обязательных полей.
     * Автоматически устанавливает текущее время как дату создания.
     * 
     * @param name Имя человека (не может быть null или пустым)
     * @param coordinates Координаты человека (не может быть null)
     * @param height Рост человека (должен быть больше 0)
     */
    public Person(String name, Coordinates coordinates, int height) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.height = height;
    }

    /**
     * Возвращает имя человека.
     * 
     * @return Имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя человека.
     * Значение не может быть null или пустым.
     * 
     * @param name Новое имя человека
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает координаты человека.
     * 
     * @return Координаты человека
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты человека.
     * Значение не может быть null.
     * 
     * @param coordinates Новые координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Возвращает дату создания записи.
     * 
     * @return Дата создания записи
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания записи.
     * Значение не может быть null.
     * 
     * @param creationDate Новая дата создания
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Возвращает рост человека.
     * 
     * @return Рост человека
     */
    public int getHeight() {
        return height;
    }

    /**
     * Устанавливает рост человека.
     * Значение должно быть больше 0.
     * 
     * @param height Новый рост
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Возвращает дату рождения человека.
     * 
     * @return Дата рождения (может быть null)
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Устанавливает дату рождения человека.
     * 
     * @param birthday Новая дата рождения (может быть null)
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Возвращает цвет глаз человека.
     * 
     * @return Цвет глаз (может быть null)
     */
    public ColorEye getEyeColor() {
        return eyeColor;
    }

    /**
     * Устанавливает цвет глаз человека.
     * 
     * @param eyeColor Новый цвет глаз (может быть null)
     */
    public void setEyeColor(ColorEye eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Возвращает цвет волос человека.
     * 
     * @return Цвет волос (может быть null)
     */
    public ColorHair getHairColor() {
        return hairColor;
    }

    /**
     * Устанавливает цвет волос человека.
     * 
     * @param hairColor Новый цвет волос (может быть null)
     */
    public void setHairColor(ColorHair hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Возвращает местоположение человека.
     * 
     * @return Местоположение (может быть null)
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Устанавливает местоположение человека.
     * 
     * @param location Новое местоположение (может быть null)
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Возвращает строковое представление человека в формате:
     * "name = X; coordinates = Y; creationDate = Z; height = W; birthday = V; 
     * eyeColor = U; hairColor = T; location = S"
     * 
     * @return Строковое представление человека
     */
    @Override
    public String toString() {
        String s = "name = " + name + "; coordinates = " + coordinates.toString() + "; creationDate = " + creationDate.toString()
                + "; height = " + height + "; birthday = " + birthday.toString() + "; eyeColor = " + eyeColor + "; hairColor = "
                + hairColor + "; location = ";
        if (location == null) return s + "null";
        else return s + location.toString();
    }

    /**
     * Сравнивает текущего человека с другим по следующим правилам:
     * 1. Сначала сравниваются имена
     * 2. Если имена равны, сравниваются координаты
     * 3. Если координаты равны, сравниваются даты создания
     * 4. Если даты создания равны, сравниваются росты
     * 5. Если росты равны, сравниваются дни рождения (null считается меньше)
     * 6. Если дни рождения равны, сравниваются цвета глаз (null считается меньше)
     * 7. Если цвета глаз равны, сравниваются цвета волос (null считается меньше)
     * 8. Если цвета волос равны, сравниваются местоположения (null считается меньше)
     * 
     * @param o Человек для сравнения
     * @return Отрицательное число, если текущий человек меньше указанного
     *         Ноль, если люди равны
     *         Положительное число, если текущий человек больше указанного
     */
    @Override
    public int compareTo(Person o) {
        int nameCompare = this.name.compareTo(o.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        int coordinatesCompare = this.coordinates.compareTo(o.coordinates);
        if (coordinatesCompare != 0) {
            return coordinatesCompare;
        }
        int creationDateCompare = this.creationDate.compareTo(o.creationDate);
        if (creationDateCompare != 0) {
            return creationDateCompare;
        }
        int heightCompare = Integer.compare(this.height, o.height);
        if (heightCompare != 0) {
            return heightCompare;
        }
        if (this.birthday == null) {
            return -1;
        }
        if (o.birthday == null) {
            return 1;
        }
        int birthdayCompare = this.birthday.compareTo(o.birthday);
        if (birthdayCompare != 0) {
            return birthdayCompare;
        }
        if (this.eyeColor == null) {
            return -1;
        }
        if (o.eyeColor == null) {
            return 1;
        }
        int eyeColorCompare = this.eyeColor.compareTo(o.eyeColor);
        if (eyeColorCompare != 0) {
            return eyeColorCompare;
        }
        if (this.hairColor == null) {
            return -1;
        }
        if (o.hairColor == null) {
            return 1;
        }
        int hairColorCompare = this.hairColor.compareTo(o.hairColor);
        if (hairColorCompare != 0) {
            return hairColorCompare;
        }
        if (this.location == null) {
            return -1;
        }
        if (o.location == null) {
            return 1;
        }
        return this.location.compareTo(o.location);
    }
}