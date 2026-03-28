package ackermann.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ackermann.exceptions.CheckedException;
import ackermann.exceptions.InvalidCodeException;

public class UiTest {
    @Test
    public void validIsRun() throws CheckedException {
        TaskList tasks = new TaskList();
        Ui ui = new ackermann.functions.Ui(tasks);

        assertFalse(ui.isRun("bye"));
        assertTrue(ui.isRun("todo something"));
        assertTrue(ui.isRun("deadline test1 /by 2025-08-23"));
        assertTrue(ui.isRun("event project meeting /from 2019-12-01 /to 2019-12-15"));
        assertTrue(ui.isRun("list"));
        assertTrue(ui.isRun("mark 1"));
        assertTrue(ui.isRun("unmark 1"));
        assertTrue(ui.isRun("delete 1"));
    }

    @Test
    public void invalidCommand() {
        TaskList tasks = new TaskList();
        Ui ui = new ackermann.functions.Ui(tasks);

        assertThrows(InvalidCodeException.class, () -> ui.isRun("test"));
    }

    @Test
    public void invalidTask() {
        TaskList tasks = new TaskList();
        Ui ui = new ackermann.functions.Ui(tasks);

        assertThrows(CheckedException.class, () -> ui.isRun("todo"));
        assertThrows(CheckedException.class, () -> ui.isRun("deadline"));
        assertThrows(CheckedException.class, () -> ui.isRun("event"));
    }
}
