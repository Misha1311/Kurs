package Classes;

/**
 * Класс-поток, счиатет время с начала выполнения программы и время выполнения процесса
 */

public class Timer extends Thread {
    /**
     * Статическое поле хранящее время роботы программы
     */
    public static int time = 0;
    /**
     * Указатель на процессор
     */
    private CPU cpu;
    /**
     * Время выполнения процесса
     */
    private int workTime = 0;

    public Timer() {
    }

    /**
     * Конструктор, принимающий и инициализирующий процессор
     */
    public Timer(CPU cpu) {
        this.cpu = cpu;
    }

    /**
     * Переопределение run(), посчет времени выполнения программы
     */
    @Override
    public void run() {
        while (cpu.isActive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incTime();
        }
    }

    /**
     * Метод добавления такта к работе процесса
     */
    public void incWorkTime(int tact) {
        workTime += tact;
    }

    /**
     * Метод добавления такта к работе программы
     */
    public static void incTime(int tact) {
        time += tact;
    }

    /**
     * Метод инкрементирующий время работы программы
     */
    public static void incTime() {
        time++;
    }

    public int getWorkTime() {
        return workTime;
    }

}
