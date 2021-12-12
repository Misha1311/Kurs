package Classes;


/**
 * Класс процессор, для хранения ядер
 */
public class CPU {
    /**
     * Состояние процессора
     */
    private boolean isActive = true;
    /**
     * Массив ядер процессора
     */
    private final Core[] cores;

    /**
     * Коструктор принимающий количество ядер процессора и инициализирующий ядра(потоки)
     */
    public CPU(final int coresNumber) {
        this.cores = new Core[coresNumber];
        for (int i = 0; i < coresNumber; i++) {
            cores[i] = new Core();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Core[] getCores() {
        return cores;
    }
}
