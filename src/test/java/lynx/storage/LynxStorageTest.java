package lynx.storage;

import lynx.command.LynxCommand;
import lynx.exception.LynxException;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LynxStorageTest {

    // This test checks the correctness of parsing, ensuring that different forms
    // of erroneous parsing are correctly detected. The first 3 strings are control parses.
    // Note that loadTasks returns the error count.
    @Test
    public void loadTasks_invalidStrings_errorCount() {
        List<String> tasks = new ArrayList<>();
        tasks.add("TODO|COMPLETE|1|a");
        tasks.add("DEADLINE|INCOMPLETE|2|b|by:2025-11-11");
        tasks.add("EVENT|COMPLETE|3|c|from:2025-11-11|to:2025-11-12");

        tasks.add("TODO|INCOMPLETE|4|");
        tasks.add("AAA|COMPLETE|5|e");
        tasks.add("DEADLINE|INCOMPLETE|6|f");
        tasks.add("DEADLINE|INCOMPLETE|7|by:2025-11-11|g");
        tasks.add("EVENT|COMPLETE|8|h|to:2025-11-12|from:2025-11-11");
        assertEquals(5, LynxStorage.loadTasks(tasks));
    }

    // This test checks that the parsing works in both directions.
    // I am unable to check for equality, since task objects before and after loading are different.
    // However, using the error count alongside the results of the previous test,
    // I can ensure with good precision the accuracy of the parsing.
    @Test
    public void unloadTasks_clearTasks_loadTasks() throws IllegalArgumentException {
        LynxTaskList.clearTasks(true);
        Task testTaskA = new TodoTask("a");
        Task testTaskB = new DeadlineTask("b",
                LocalDateTime.of(2025, 11, 11, 0, 0));
        Task testTaskC = new EventTask("c",
                LocalDateTime.of(2025, 11, 12, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0));
        LynxTaskList.addTask(testTaskA, true);
        LynxTaskList.addTask(testTaskB, true);
        LynxTaskList.addTask(testTaskC, true);

        List<String> tasks = LynxStorage.unloadTasks();
        LynxTaskList.clearTasks(true);
        assertEquals(0, LynxTaskList.getCount());

        assertEquals(0, LynxStorage.loadTasks(tasks));
        assertEquals(3, LynxTaskList.getCount());
    }

}
