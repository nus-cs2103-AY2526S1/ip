package robert.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/test.txt");
    }

    @Test
    public void byeCommand_execute_returnsExitMessage() throws RobertException, IOException {
        Command bye = new ByeCommand();
        String response = bye.execute(tasks, ui, storage);
        
        assertTrue(bye.isExit());
        assertEquals("Bye. Hope to see you again soon!", response);
    }

    @Test
    public void listCommand_emptyList_returnsEmptyMessage() throws RobertException, IOException {
        Command list = new ListCommand();
        String response = list.execute(tasks, ui, storage);
        
        assertFalse(list.isExit());
        assertEquals("You have no tasks in your list.", response);
    }

    @Test
    public void markCommand_invalidIndex_throwsException() {
        Command mark = new MarkCommand(5);
        
        assertThrows(RobertException.class, () -> mark.execute(tasks, ui, storage));
    }
}