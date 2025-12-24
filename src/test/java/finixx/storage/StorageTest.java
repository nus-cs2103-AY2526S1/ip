package finixx.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import finixx.task.DeadlineTask;
import finixx.task.EventTask;
import finixx.task.Task;
import finixx.task.TaskCollection;
import finixx.task.TodoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Storage.
 */
public class StorageTest {
    @Test
    void saveTasks_writesCorrectFormat() throws IOException {
        Storage s = new Storage("tasks.txt");
        List<Task> tasks = List.of(
                new TodoTask("read book", false, ""),
                new DeadlineTask("return book", true, "nlb", "2025-11-20"),
                new EventTask("project meeting", false, "", "Mon 2pm", "4pm")
        );
        TaskCollection t = new TaskCollection(tasks);
        s.saveTasks(t);
        List<String> lines = Files.readAllLines(Paths.get("tasks.txt"));
        assertEquals("T | 0 | read book | ", lines.get(0));
        assertEquals("D | 1 | return book | 2025-11-20 | nlb", lines.get(1));
        assertEquals("E | 0 | project meeting | Mon 2pm | 4pm | ", lines.get(2));
    }
}
