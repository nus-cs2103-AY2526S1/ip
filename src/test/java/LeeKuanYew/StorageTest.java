package LeeKuanYew;

import LeeKuanYew.Task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void formatTaskTest() {
        Storage s = new Storage("../../../../data/LeeKuanYew.txt");

        // test case 1
        ToDoTask t = new ToDoTask("Meng Shuo");
        assertEquals("T|0|Meng Shuo", s.formatTask(t));
    }
}
