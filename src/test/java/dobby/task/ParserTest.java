package dobby;

import dobby.exceptions.DobbyException;
import dobby.task.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Test;

class ParserTest {

    @TempDir
    Path tempDir;

    @Test
    void handleTodoCommand_validAndEmptyDescription() throws DobbyException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Path testFile = tempDir.resolve("test.txt"); // safe test file
        Storage storage = new Storage(testFile);
        Parser parser = new Parser(tasks, storage);

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
