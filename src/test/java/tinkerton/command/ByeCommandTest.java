package tinkerton.command;

import org.junit.jupiter.api.Test;

import tinkerton.core.TinkertonException;
import tinkerton.storage.StubSave;
import tinkerton.task.StubTaskList;
import tinkerton.util.StubUi;

import static org.junit.jupiter.api.Assertions.*;

public class ByeCommandTest {
    @Test
    void testExecute() throws TinkertonException {
        StubUi ui = new StubUi();
        StubTaskList tasks = new StubTaskList();
        StubSave save = new StubSave();

        ByeCommand byeCommand = new ByeCommand("bye");
        byeCommand.execute(tasks, ui, save);

        assertEquals("Bye. Hope to see you again soon!", ui.getLastPrintedMessage());
    }
}
