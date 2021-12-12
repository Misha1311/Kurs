package GUI;

import Classes.Process;
import Classes.*;

import java.util.List;

/**
 * Класс планировщик выполнения процессов
 */
public class Scheduler extends Thread {
    /**
     * Указатель на процессор
     */
    private final CPU cpu;
    /**
     * Указатель на очередь процессов
     */
    private final QueueCPU queueCPU;
    /**
     * Указатель на планировщик памяти
     */
    private final MemoryScheduler memoryScheduler;

    /**
     * Конструктор, принимающий кол-во ядер и максимальное значение памяти
     * и инициализирующий все поля класса и статическое поле MemoryScheduler.maxMemory
     */
    public Scheduler(final int coresNumber, int maxMemoryVolume) {
        this.cpu = new CPU(coresNumber);
        this.queueCPU = new QueueCPU();
        this.memoryScheduler = new MemoryScheduler();
        MemoryScheduler.maxMemory = maxMemoryVolume;
    }

    /**
     * Метод распределяющий процессы по свободным ядрам из очередей приоритетов и запуская потоки выполения (ядра)
     */
    @Override
    public void run() {
        while (getCpu().isActive()) {
            Core[] cores = cpu.getCores();
            if (queueCPU.getPriorityQueue().stream().anyMatch(list -> !list.isEmpty())) {
                for (List<Process> processList : queueCPU.getPriorityQueue()) {
                    do {
                        for (Process process : processList) {
                            for (int j = 0; j < cores.length; j++) {
                                if (cores[j].isFree() && process.getState() != Status.Running && MemoryScheduler.fill(process.getMemoryBlock())) {
                                    process.setState(Status.Running);
                                    cores[j] = new Core(queueCPU, process);
                                    cores[j].start();
                                    break;
                                }
                            }
                        }
                    } while (!processList.stream().allMatch(process -> process.getState().equals(Status.Running)) && !processList.isEmpty());
                }
            }
        }
    }

    public CPU getCpu() {
        return cpu;
    }

    public QueueCPU getQueueCPU() {
        return queueCPU;
    }

    public MemoryScheduler getMemoryScheduler() {
        return memoryScheduler;
    }
}
