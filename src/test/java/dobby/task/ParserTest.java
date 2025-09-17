package dobby;

import dobby.exceptions.DobbyException;
import dobby.task.ToDo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @TempDir
    Path tempDir;

    @Test
    void handleTodoCommand_validAndEmptyDescription() throws DobbyException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Path testFile = tempDir.resolve("test.txt"); // safe test file
        Storage storage = new Storage(testFile);
        Parser parser = new Parser();

        // Valid todo
        parser.handleCommand("todo Buy milk");
        assertEquals(1, tasks.size());
        assertEquals("Buy milk", tasks.getTasks().get(0).getDescription());

        // Empty description
        Exception exception = assertThrows(DobbyException.class, () -> {
            parser.handleCommand("todo   ");
        });
        assertEquals("Task description cannot be empty!", exception.getMessage());
    }
}
