package lux.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lux.exception.LuxException;

class StubUi extends lux.ui.Ui {
    private boolean byeShown = false;

    @Override
    public String showBye() {
        byeShown = true;
        String message = "Goodbye! Hope to see you again soon!";
        return message;
    }

    public boolean getByeShown() {
        return byeShown;
    }
}

class StubStorage extends lux.storage.Storage {
    private boolean saved = false;

    StubStorage() {
        super("dummy/");
    }

    @Override
    public void saveTasks(lux.data.TaskList tasks) {
        saved = true;
    }

    public boolean getSaved() {
        return saved;
    }
}

class StubTaskList extends lux.data.TaskList {
}

public class ByeCommandTest {
    @Test
    public void testIsExit() {
        ByeCommand cmd = new ByeCommand();
        assertTrue(cmd.isExit());
    }

    @Test
    public void testExecuteCallsShowByeAndSave() {
        StubUi ui = new StubUi();
        StubStorage storage = new StubStorage();
        StubTaskList tasks = new StubTaskList();
        ByeCommand cmd = new ByeCommand();

        try {
            cmd.execute(tasks, ui, storage, null);
            assertTrue(ui.getByeShown(), "showBye should be called");
            assertTrue(storage.getSaved(), "save should be called");
        } catch (LuxException e) {
            e.printStackTrace();
        }

    }
}
