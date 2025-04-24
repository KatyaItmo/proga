package commandManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import collection.ColorEye;
import collection.ColorHair;
import exceptions.InputException;

/**
 * Класс для валидации входных данных.
 * Предоставляет методы для проверки корректности различных типов данных.
 * 
 * @author Ekaterina
 * @version 1.0
 * @since 2025-04-10
 */
public class Validator {
    
    /**
     * Проверяет корректность введенных данных в зависимости от типа валидации.
     * 
     * @param str массив строк для валидации
     * @param type тип валидации (0-9 для разных полей)
     * @return true если данные корректны, false иначе
     */
    public boolean ifValid(String[] str, int type) {
        if (type == 0) return nameValid(str);
        if (type == 1 || type == 8) return floatValid(str);
        if (type == 2) return yCoordinateValid(str);
        if (type == 3) return heightValid(str);
        if (type == 4) return dateValid(str);
        if (type == 5) return eyeColorValid(str);
        if (type == 6) return hairColorValid(str);
        if (type == 7) return xLocationValid(str);
        if (type == 9) return zLocationValid(str);
        return nameValid(str);
    }

    /**
     * Проверяет корректность имени.
     * 
     * @param name массив строк с именем
     * @return true если имя корректно, false иначе
     */
    public boolean nameValid(String[] name) {
        try {
            if (name.length < 1) throw new InputException("Имя не может быть пустым!");
            if (name.length >= 1) {
                if (!name[0].matches("[a-zA-Z]+")) throw new InputException("Используйте только латинские буквы!");
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        if (name.length > 1) System.out.println("В имя записано первое введенное слово.");
        return true;
    }

    /**
     * Проверяет корректность числового значения с плавающей точкой.
     * 
     * @param x массив строк с числом
     * @return true если число корректно, false иначе
     */
    public boolean floatValid(String[] x) {
        try {
            if (x.length < 1) throw new InputException("Поле не может быть пустым!");
            if (x.length > 1) throw new InputException("Введите одно число.");
            if (x.length == 1) {
                Float.parseFloat(x[0]);
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(NumberFormatException ex) {
            System.out.println("Неверный формат!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность координаты Y (должна быть больше -273).
     * 
     * @param y массив строк с координатой
     * @return true если координата корректна, false иначе
     */
    public boolean yCoordinateValid(String[] y) {
        try {
            if (y.length < 1) throw new InputException("Поле не может быть пустым!");
            if (y.length > 1) throw new InputException("Введите одно число.");
            if (y.length == 1) {
                long num = Long.parseLong(y[0]);
                if (num <= -273) throw new InputException("Y должно быть > -273!");
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(NumberFormatException ex) {
            System.out.println("Неверный формат!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность роста (должен быть больше 0).
     * 
     * @param h массив строк с ростом
     * @return true если рост корректен, false иначе
     */
    public boolean heightValid(String[] h) {
        try {
            if (h.length < 1) throw new InputException("Рост не может быть пустым!");
            if (h.length > 1) throw new InputException("Введите одно число.");
            if (h.length == 1) {
                int num = Integer.parseInt(h[0]);
                if (num <= 0) throw new InputException("Рост должен быть > 0!");
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(NumberFormatException ex) {
            System.out.println("Неверный формат!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность даты в формате YYYY-MM-DD.
     * 
     * @param d массив строк с датой
     * @return true если дата корректна, false иначе
     */
    public boolean dateValid(String[] d) {
        try {
            if (d.length < 1) throw new InputException("Введите 'skip' чтобы сделать поле пустым!");
            if (d.length > 1) throw new InputException("Введите одну дату.");
            if (d.length == 1) {
                if (d[0].equals("skip")) return true;
                LocalDate.parse(d[0]);
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(DateTimeParseException ex) {
            System.out.println("Неверный формат!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность цвета глаз.
     * 
     * @param c массив строк с цветом глаз
     * @return true если цвет корректен, false иначе
     */
    public boolean eyeColorValid(String[] c) {
        try {
            if (c.length < 1) throw new InputException("Введите 'skip' чтобы сделать поле пустым!");
            if (c.length > 1) throw new InputException("Введите одно значение.");
            if (c.length == 1) {
                if (c[0].equals("skip")) return true;
                ColorEye.valueOf(c[0]);
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Значение не найдено!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность цвета волос.
     * 
     * @param c массив строк с цветом волос
     * @return true если цвет корректен, false иначе
     */
    public boolean hairColorValid(String[] c) {
        try {
            if (c.length < 1) throw new InputException("Введите 'skip' чтобы сделать поле пустым!");
            if (c.length > 1) throw new InputException("Введите одно значение.");
            if (c.length == 1) {
                if (c[0].equals("skip")) return true;
                else ColorHair.valueOf(c[0]);
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Значение не найдено!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность координаты X локации.
     * 
     * @param x массив строк с координатой
     * @return true если координата корректна, false иначе
     */
    public boolean xLocationValid(String[] x) {
        try {
            if (x.length < 1) throw new InputException("Введите 'skip' чтобы сделать поле пустым!");
            if (x.length > 1) throw new InputException("Введите одно число.");
            if (x.length == 1) {
                if (x[0].equals("skip")) return true;
                Long.parseLong(x[0]);
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(NumberFormatException ex) {
            System.out.println("Неверный формат!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет корректность координаты Z локации.
     * 
     * @param z массив строк с координатой
     * @return true если координата корректна, false иначе
     */
    public boolean zLocationValid(String[] z) {
        try {
            if (z.length < 1) throw new InputException("Поле не может быть пустым!");
            if (z.length > 1) throw new InputException("Введите одно число.");
            if (z.length == 1) {
                Integer.parseInt(z[0]);
            }
        }
        catch(InputException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        catch(NumberFormatException ex) {
            System.out.println("Неверный формат!");
            return false;
        }
        return true;
    }
}