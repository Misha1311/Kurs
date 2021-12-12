package Classes;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static Classes.Configurations.OSMemory;

/**
 * Класс планировщик памяти
 */
public class MemoryScheduler {
    /**
     * Максимально допустимая память
     */
    public static int maxMemory;
    /**
     * Список блоков памяти
     */
    private static final List<MemoryBlock> memoryBlocks = new CopyOnWriteArrayList<>();

    /**
     * Метод нахождения свободного места для блока
     */
    public static boolean findFreeBlock(MemoryBlock memoryBlock) {
        if (memoryBlocks.isEmpty()) {
            memoryBlock.setParameters(OSMemory);
            return true;
        }
        memoryBlocks.sort(MemoryBlock.byEnd);
        for (int i = 0; i < memoryBlocks.size(); i++) {
            if (i == memoryBlocks.size() - 1) {
                if (maxMemory - memoryBlocks.get(i).getEnd() >= memoryBlock.getSize()) {
                    memoryBlock.setParameters(memoryBlocks.get(i).getEnd());
                    return true;
                } else return false;
            }
            if (memoryBlocks.get(i + 1).getStart() - memoryBlocks.get(i).getEnd() >= memoryBlock.getSize()) {
                memoryBlock.setParameters(memoryBlocks.get(i).getEnd());
                return true;
            }
        }
        return false;
    }

    /**
     * Метод добавления блока памяти
     */
    public static boolean fill(MemoryBlock memoryBlock) {
        if (!memoryBlocks.contains(memoryBlock) && findFreeBlock(memoryBlock)) {
            memoryBlocks.add(memoryBlock);
            return true;
        }
        return false;
    }

    /**
     * Метод удаления блока памяти
     */
    static public void release(int id) {
        memoryBlocks.removeIf(memoryBlock -> memoryBlock.getId() == id);
    }

    /**
     * Метод вывод всех блоков памяти
     */
    public String print() {
        StringBuilder result = new StringBuilder();
        for (MemoryBlock memoryBlock : memoryBlocks) {
            result.append("Name: P").append(memoryBlock.getId()).append(memoryBlock).append('\n');
        }
        return result.toString();
    }


}
