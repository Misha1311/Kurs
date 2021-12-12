package Classes;


import java.util.Comparator;

/**
 * Класс для хранения блоков памяти
 */

public class MemoryBlock {
    /**
     * Старт блока
     */
    private int start;
    /**
     * Конец блока
     */
    private int end;
    /**
     * Размер блока
     */
    private final int size;
    /**
     * ID блока
     */
    private int id;

    /**
     * Функция сравнения блоков памяти по концам
     */
    public static Comparator<MemoryBlock> byEnd = Comparator.comparingInt(o -> o.end);

    /**
     * Конструктор, инициалищирующий размер блока памяти
     */
    public MemoryBlock(int id, int size) {
        this.size = size;
        this.id = id;
    }

    /**
     * Метод, принимающий старт блока и задающий старт и конец блока
     */
    public void setParameters(int start) {
        this.start = start;
        this.end = start + this.size;
    }

    public int getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }

    /**
     * Вывод блока памяти
     */
    @Override
    public String toString() {
        return "{" + start + "," + end + '}';
    }
}
