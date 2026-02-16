package nerpbot;

import nerpbot.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() throws IOException {
        TaskList tasks = new TaskList(new Storage("data/test.txt"));
        int before = tasks.getTasks().size();

        tasks.addTask(new ToDo("read book"));
        int after = tasks.getTasks().size();

        assertEquals(before + 1, after);
        assertEquals("[T][ ] read book", tasks.getTasks().get(after - 1).toString());
    }
}
