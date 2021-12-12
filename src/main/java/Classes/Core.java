package Classes;


/**
 * Класс-поток, ядро процессора которое выполняет процессы
 */
public class Core extends Thread {
    /**
     * Указатель на процесс
     */
    private Process process;
    /**
     * Указатель на очередь
     */
    private QueueCPU queueCPU;
    /**
     * Состояние ядра
     */
    private boolean isFree = true;

    /**
     * "Пустой" конструктор для инициализации ядер
     */
    public Core() {
    }

    /**
     * Конструтор принимающий очередь и процесс для создания нового потока
     */
    public Core(QueueCPU queueCPU, Process process) {
        this.queueCPU = queueCPU;
        this.process = process;
    }

    /**
     * Возвращение состояние ядра
     */
    public boolean isFree() {
        return isFree;
    }

    /**
     * Переопределение run(), операции которые выполняет поток после запуска, посчет тактов выполнения процессов ,
     * окончание выполнение процсса и вытеснение процесса по приоритету
     */
    @Override
    public void run() {
        try {
            if (!isInterrupted()) {
                isFree = false;
                Timer timer = new Timer();
                int k = 0;
                do {
                    timer.incWorkTime(Configurations.Tact);
                    k++;
                    Thread.sleep(1000);
                    process.setBurstTime(timer.getWorkTime());
                } while (k < process.getInterval());
                process.setState(Status.Finished);
                queueCPU.complete(process);
            }
        } catch (InterruptedException e) {
            MemoryScheduler.release(process.getId());
            System.out.println("Процесс " + process.getName() + " вытеснен");
        }
        isFree = true;
    }

    public Process getProcess() {
        return process;
    }
}