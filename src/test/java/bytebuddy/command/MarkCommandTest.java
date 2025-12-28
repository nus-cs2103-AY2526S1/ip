package bytebuddy.command;

import org.junit.jupiter.api.Test;
import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.task.TaskType;
import bytebuddy.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkCommandTest {

     public static String LINE = "__________________________________";

    @Test
    void execute_validIndex_marksTaskAsDone() {
        TaskList tasks = new TaskList();
        Task t = new Task("read book", TaskType.TODO);
        tasks.addTask(t);

        Storage storage = new Storage("src/test/data/tasks.txt");
        MarkCommand cmd = new MarkCommand(0);

        String result = cmd.execute(storage, tasks, new Ui()); // now we assert on returned string

        assertEquals("T | 1 | read book", tasks.getTask(0).toString());
        // Optionally: check output message
        assertEquals(String.format("%s\nNice! I've marked this task as done:\n  T | 1 | read book\n%s", LINE, LINE), result);
    }

    @Test
    void execute_invalidIndex_returnsErrorMessage() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Task("read book", TaskType.TODO));

        Storage storage = new Storage("src/test/data/tasks.txt");
        MarkCommand cmd = new MarkCommand(5); // invalid index

        String result = cmd.execute(storage, tasks, new Ui());

        assertEquals(String.format("%s\nError: Invalid task index.\n%s", LINE, LINE), result);
    }
}
