package pero;

import org.junit.jupiter.api.Test;
import pero.exception.PeroException;
import pero.model.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void saveList_tasks_tasksWrittenToFile() throws IOException {
        //create test task list for testing reading and writing
        TaskList tasksTest = new TaskList();

        try {
            tasksTest.addTaskFromInput("todo test1");
            tasksTest.addTaskFromInput("event test2 /from 2025-02-12 2030 /to 2025-02-12 2030");
            tasksTest.addTaskFromInput("deadline test3 /by 2025-01-09 2359");
        } catch (PeroException e) {
            fail("Unexpected PeroException: " + e.getMessage());
        }

        Storage storage = new Storage("testStorage.txt");

        try {
            storage.saveList(tasksTest);
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }

        //check if tasks are correctly written to testStorage
        List<String> lines = Files.readAllLines(Paths.get("testStorage.txt"));
        assertEquals(3, lines.size());

        assertEquals("T | 0 | test1", lines.get(0));
        assertEquals("E | 0 | test2 | 2025-02-12 2030 | 2025-02-12 2030", lines.get(1));
        assertEquals("D | 0 | test3 | 2025-01-09 2359", lines.get(2));
    }
}
