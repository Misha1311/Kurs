package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static Classes.MemoryScheduler.release;

/**
 * Класс для хранения и обработки очереди процессов
 */
public class QueueCPU {
    /**
     * Список готовых процессов
     */
    private final List<Process> completed = new ArrayList<>();
    /**
     * Список приоритетных очередей процессов
     */
    private final List<List<Process>> priorityQueue = new CopyOnWriteArrayList<>();
    private int lastId = 1;

    /**
     * Конструктор очвереди, при создании очереди инициализируются приоритетные очереди
     */
    public QueueCPU() {
        this.setPriorityQueue();
    }

    /**
     * Метод добавления процесса
     */
    public void add() {
        Process process = new Process(lastId++);
        setProcessByPriority(process);
    }

    /**
     * Метод добавления N процессов
     */
    public void add(int N) {
        for (int i = 0; i < N; i++) {
            add();
        }
    }

    /**
     * Метод удаления процесса из очереди, т.к. процесс выполнился
     */
    public void complete(Process process) {
        release(process.getMemoryBlock().getId());
        if (!completed.contains(process))completed.add(process);
        priorityQueue.get(process.getPriority()).remove(process);
    }

    /**
     * Инициализация очередей приоритетов
     */
    public void setPriorityQueue() {
        for (int i = 0; i < Configurations.maxPriority; i++) {
            priorityQueue.add(new CopyOnWriteArrayList<>());
        }
    }

    /**
     * Метод добавления процесса в очередь приоритетов
     */
    public void setProcessByPriority(Process process) {
        priorityQueue.get(process.getPriority()).add(process);
    }

    /**
     * Метод динамического повышения приоритета
     */
    public void upPriority(Process process) {
        if (process.getPriority() != 1) {
            priorityQueue.get(process.getPriority()).remove(process);
            process.setPriority(process.getPriority() - 1);
            priorityQueue.get(process.getPriority()).add(process);
        }
    }

    public List<List<Process>> getPriorityQueue() {
        return priorityQueue;
    }

    public String printPriorityQueue() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < priorityQueue.size(); i++) {
            sb.append("Priority ").append(i).append(priorityQueue.get(i)).append('\n');
        }
        return sb.toString();
    }

    public String printCompleted() {
        StringBuilder sb = new StringBuilder();
        for (Process process : completed) {
            sb.append("ID: ").append(process.getId()).append(" ").append("Name: ").append(process.getName()).append('\n');
        }
        return sb.toString();
    }
}
