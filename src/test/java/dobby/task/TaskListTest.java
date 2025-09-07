package dobby;

import dobby.exceptions.DobbyException;
import dobby.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void handleTodoCommand_validAndEmptyDescription() throws DobbyException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage(java.nio.file.Paths.get("test.txt"));
        Parser parser = new Parser();

        // Valid todo
        parser.handleCommand("todo Buy milk", tasks, ui, storage);
        assertEquals(1, tasks.size());
        assertEquals("Buy milk", tasks.getAll().get(0).getDescription());

        // Empty description
        Exception exception = assertThrows(DobbyException.class, () -> {
            parser.handleCommand("todo   ", tasks, ui, storage);
        });
        assertEquals("Task description cannot be empty!", exception.getMessage());
    }
}
