package jerome.command;

import jerome.TaskList;
import jerome.ui.Ui;
import jerome.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {

    @Test
    void testExecuteGoodbye() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        ByeCommand command = new ByeCommand();

        command.execute(tasks, ui, storage);

    }

    @Test
    void testIsExit() {
        ByeCommand command = new ByeCommand();
        assertTrue(command.isExit());
    }
}

