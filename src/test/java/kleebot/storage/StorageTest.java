package kleebot.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kleebot.task.Deadline;
import kleebot.task.Task;
import kleebot.task.TaskList;

public class StorageTest {


    @Test
    public void saveAndLoadRoundTrip() throws Exception {
        Storage storage = new Storage("KleeData/test_tasks.txt");
        storage.load();
        Deadline testTask = new Deadline("read book", "2025-10-01");

        TaskList tasks = new TaskList();
        tasks.addToList(testTask);

        storage.saveTasksToLocal(tasks.getTasks());

        ArrayList<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals(tasks.getTask(0).toString(), loaded.get(0).toString());
    }
}