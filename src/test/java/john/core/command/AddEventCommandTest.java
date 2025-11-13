package john.core.command;

import john.model.Event;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FakeStorage implements Storage {
    TaskList lastSaved;

    @Override
    public TaskList load() {
        return new TaskList();
    }

    @Override
    public void save(TaskList tasks) {
        lastSaved = tasks;
    }
}

class StubUi implements Ui {
    @Override
    public String nextCommand() {
        return null;
    }

    @Override
    public void showMessage(String msg) {
    }

    @Override
    public void close() {
    }

    @Override
    public void showWelcome(int taskCount) {

    }
}

class AddEventCommandTest {

    @Test
    void execute_addsEvent_andPersists_andReturnsFeedback() throws Exception {
        TaskList tasks = new TaskList();
        FakeStorage storage = new FakeStorage();
        Ui ui = new StubUi();

        LocalDateTime from = LocalDateTime.of(2025, 9, 5, 9, 0, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 5, 10, 0, 0);
        Command cmd = new AddEventCommand("Standup", from, to);

        CommandResult res = cmd.execute(tasks, storage, ui);

        assertEquals(1, tasks.size());
        assertNotNull(storage.lastSaved, "Should save after adding");
        assertEquals(1, storage.lastSaved.size());

        assertTrue(res.feedback().toLowerCase().contains("added"));
        assertTrue(res.feedback().toLowerCase().contains("standup"));

        assertTrue(tasks.get(0) instanceof Event);
        Event e = (Event) tasks.get(0);
        assertTrue(e.toString().toLowerCase().contains("standup"));
    }
}
