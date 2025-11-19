package mochibot.command;

import mochibot.MochiBotException;

import mochibot.task.TaskList;
import mochibot.task.Todo;

import mochibot.ui.Gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnmarkCommandTest {
    private TaskList tasks;
    private Gui gui;

    @BeforeEach
    void setup() {
        tasks = new TaskList();
        gui = new Gui();
        Todo todo = new Todo("eat bread");
        todo.markDone();
        this.tasks.addTask(todo);
    }

    @Test
    public void execute_validTaskIndex_successUnmarkTask() throws MochiBotException {
        UnmarkCommand c = new UnmarkCommand(0);
        c.execute(tasks, gui);
        assertEquals(" ", tasks.getTask(0).getStatusIcon(), "Task was not unmarked.");
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        UnmarkCommand c = new UnmarkCommand(1);
        assertThrows(MochiBotException.InvalidTaskIndexException.class, () -> c.execute(tasks, gui));
    }
}
