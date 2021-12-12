package Classes;

import GUI.Scheduler;
import javafx.scene.control.TextArea;

public class PrintThread extends Thread {
    TextArea textArea;
    TextArea completedArea;
    TextArea memoryBlocksArea;
    Scheduler scheduler;

    public PrintThread(TextArea memoryBlocksArea, TextArea completedArea, TextArea textArea, Scheduler scheduler) {
        this.completedArea = completedArea;
        this.memoryBlocksArea = memoryBlocksArea;
        this.textArea = textArea;
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        while (scheduler.getCpu().isActive()) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textArea.setText(scheduler.getQueueCPU().printPriorityQueue());
            completedArea.setText(scheduler.getQueueCPU().printCompleted());
            memoryBlocksArea.setText(scheduler.getMemoryScheduler().print());
        }
    }
}
