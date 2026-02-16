package snowy;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
        @Test
        public void taskListCheck() throws SnowyException, IOException {
            Path temp = Files.createTempFile("snowy-test", ".txt");
            TaskList tasks = new TaskList();
            Storage storage = new Storage(temp.toString());
            String output1 = Parser.parse("todo return book").execute(tasks, storage);
            String output2 = Parser.parse("deadline finish assignment /by 2025-09-19").execute(tasks, storage);
            tasks.deleteTask(1);
            assertEquals(tasks.getSize(), 1);
        }
}
