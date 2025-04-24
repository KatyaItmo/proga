package collection;

/**
 * Класс для представления координат.
 * Предоставляет методы для работы с координатами x и y.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Coordinates implements Comparable<Coordinates>{
	
	/**
     * Координата X. Не может быть null.
     * Используется тип Float вместо int для возможности явного указания null.
     */
	private Float x; //Поле не может быть null
	
	/**
     * Координата Y. Должна быть больше -273 (абсолютный ноль).
     * Используется тип long для точности измерения.
     */
    private long y; //Значение поля должно быть больше -273
	
    /**
     * Конструктор для создания объекта с заданными координатами.
     * 
     * @param x координата X (не может быть null)
     * @param y координата Y (должна быть больше -273)
     * @throws IllegalArgumentException если x равен null
     * @throws IllegalArgumentException если y меньше или равно -273
     */
	public Coordinates(Float x, long y) {
		this.x = x;
		this.y = y;
	}
	
	/**
     * Пустой конструктор для создания объекта без начальных значений.
     * После создания необходимо установить координаты через сеттеры.
     */
	public Coordinates() {}

	/**
     * Возвращает значение координаты X.
     * 
     * @return значение координаты X
     */
	public Float getX() {
		return x;
	}
	
	/**
     * Возвращает значение координаты X.
     * 
     * @return значение координаты X
     */
	public void setX(Float x) {
		this.x = x;
	}
	
	/**
     * Устанавливает значение координаты X.
     * 
     * @param x новое значение координаты X
     * @throws IllegalArgumentException если x равен null
     */
	public long getY() {
		return y;
	}
	
	/**
     * Устанавливает значение координаты Y.
     * 
     * @param y новое значение координаты Y
     * @throws IllegalArgumentException если y меньше или равно -273
     */
	public void setY(long y) {
		this.y = y;
	}

	/**
     * Возвращает строковое представление координат.
     * 
     * @return строка в формате "Coordinates: x = x, y = y"
     */
	@Override
	public String toString() {
		return "Coordinates: x = " + x + ", y = " + y;
	}
	
	/**
     * Сравнивает текущие координаты с другими по следующим правилам:
     * 1. Сначала сравниваются значения X
     * 2. Если X равны, сравниваются значения Y
     * 
     * @param o координаты для сравнения
     * @return отрицательное число, если текущие координаты меньше указанных
     *         ноль, если координаты равны
     *         положительное число, если текущие координаты больше указанных
     */
	@Override
	public int compareTo(Coordinates o) {
		int xCompare = Float.compare(this.x, o.x);
		if (xCompare != 0) {
	        return xCompare;
	    }
		
		return Long.compare(this.y, o.y);
	}

}
