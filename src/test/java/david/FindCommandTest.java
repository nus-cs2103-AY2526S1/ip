package david;

import static org.junit.jupiter.api.Assertions.assertEquals;

import david.command.FindCommand;
import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;
import david.task.Todo;
import org.junit.jupiter.api.Test;

/*
Used ChatGPT to generate the testcases
 */
class FindCommandTest {

    @Test
    void execute_findsMatchingTasks() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));

        String result = new FindCommand("book").execute(taskList, storage);

        // Should only show the matching "read book" task
        String expected = "    Eh here is your matching tasks ok, I took very long to find:\n"
                + "    1. [T][ ] read book\n";
        assertEquals(expected, result);
    }

    @Test
    void execute_noMatches_returnsEmptyMessage() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("do laundry"));

        String result = new FindCommand("book").execute(taskList, storage);

        assertEquals("    Bro you sure you search the write word? No results leh.", result);
    }

    @Test
    void execute_partialMatch_notFound() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("homework"));

        String result = new FindCommand("work").execute(taskList, storage);

        assertEquals("    Bro you sure you search the write word? No results leh.", result);
    }
}
