package collection;

/**
 * Класс для представления локации.
 * Предоставляет методы для работы с координатами (x, y, z) и именем локации.
 * Все поля являются обязательными и не могут быть null.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Location implements Comparable<Location>{
	private Long x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private Integer z; //Поле не может быть null
    private String name; //Поле не может быть null
    
    /**
     * Создает новую локацию с заданными координатами и именем.
     * Все параметры обязательны и не могут быть null.
     * 
     * @param x Координата X
     * @param y Координата Y
     * @param z Координата Z
     * @param name Имя локации
     */
    public Location(Long x, Float y, Integer z, String name) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.name = name;
    }
    
    /**
     * Создает пустую локацию с null значениями.
     * Используется для инициализации через сеттеры.
     */
    public Location() {}

    /**
     * Возвращает координату X локации.
     * 
     * @return Координата X
     */
	public Long getX() {
		return x;
	}

	/**
     * Устанавливает координату X локации.
     * Значение не может быть null.
     * 
     * @param x Новая координата X
     */
	public void setX(Long x) {
		this.x = x;
	}

	/**
     * Возвращает координату Y локации.
     * 
     * @return Координата Y
     */
	public Float getY() {
		return y;
	}

	/**
     * Устанавливает координату Y локации.
     * Значение не может быть null.
     * 
     * @param y Новая координата Y
     */
	public void setY(Float y) {
		this.y = y;
	}

	/**
     * Возвращает координату Z локации.
     * 
     * @return Координата Z
     */
	public Integer getZ() {
		return z;
	}

	/**
     * Устанавливает координату Z локации.
     * Значение не может быть null.
     * 
     * @param z Новая координата Z
     */
	public void setZ(Integer z) {
		this.z = z;
	}

	/**
     * Возвращает имя локации.
     * 
     * @return Имя локации
     */
	public String getName() {
		return name;
	}

	/**
     * Устанавливает имя локации.
     * Значение не может быть null.
     * 
     * @param name Новое имя локации
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Возвращает строковое представление локации в формате:
     * "Location: x = X, y = Y, z = Z, name = NAME"
     * 
     * @return Строковое представление локации
     */
	@Override
	public String toString() {
		return "Location: x = " + x + ", y = " + y + ", z = " + z + ", name = " + name;
	}

	/**
     * Сравнивает текущую локацию с другой по следующим правилам:
     * 1. Сначала сравниваются координаты X
     * 2. Если они равны, сравниваются координаты Y
     * 3. Если они равны, сравниваются координаты Z
     * 4. Если все координаты равны, сравниваются имена
     * 
     * @param o Локация для сравнения
     * @return Отрицательное число, если текущая локация меньше указанной
     *         Ноль, если локации равны
     *         Положительное число, если текущая локация больше указанной
     */
	@Override
	public int compareTo(Location o) {
		int xCompare = Long.compare(this.x, o.x);
		if (xCompare != 0) {
	        return xCompare;
	    }
		
		int yCompare = Float.compare(this.y, o.y);
		if (yCompare != 0) {
	        return yCompare;
	    }
		
		int zCompare = Integer.compare(this.z, o.z);
		if (zCompare != 0) {
	        return zCompare;
	    }
		
		return this.name.compareTo(o.name);
	}
    
}
