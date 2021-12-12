package Classes;


import java.util.Random;

import static Classes.Configurations.maxInterval;
import static Classes.Configurations.maxPriority;
import static Classes.MemoryScheduler.maxMemory;

/**
 * Класс для хранения данных процесса
 */

public class Process {
    /**
     * Имя процесса
     */
    private final String name;
    /**
     * Идентификатор процесса
     */
    private final int id;
    /**
     * Приоритет процесса
     */
    private int priority;
    /**
     * Количество тактов работы для выполнения процесса
     */
    private final int interval;
    /**
     * Память занимающаяся процессом
     */
    private final int memory;
    /**
     * Время создания процесса
     */
    private final int timeAdmission;
    /**
     * Время выполнения процесса
     */
    private int burstTime;
    /**
     * Статус процесса
     */
    private Status state;
    /**
     * Блок памяти процесса
     */
    private final MemoryBlock memoryBlock;

    Random random = new Random();

    /**
     * Конструктор принимающий id процесса, инициализирующий все поля класса
     */
    public Process(int id) {
        this.id = id;
        this.memory = random.nextInt(maxMemory / 2);
        this.priority = random.nextInt(maxPriority);
        this.interval = random.nextInt(maxInterval);
        this.burstTime = 0;
        this.timeAdmission = Timer.time;
        this.name = "P" + id;
        this.state = Status.Ready;
        this.memoryBlock = new MemoryBlock(id, this.memory);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getInterval() {
        return interval;
    }

    public Status getState() {
        return state;
    }

    public MemoryBlock getMemoryBlock() {
        return memoryBlock;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setState(Status state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' +
                ", id=" + id +
                ", priority=" + priority +
                ", interval=" + interval +
                ", memory=" + memory +
                ", timeAdmission=" + timeAdmission +
                ", burstTime=" + burstTime +
                ", state=" + state +
                '}';
    }
}
