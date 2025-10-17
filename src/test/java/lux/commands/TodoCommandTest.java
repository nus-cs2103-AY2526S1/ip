package lux.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoCommandTest {
    static class StubUi extends lux.ui.Ui {
        private boolean todoAdded = false;

        public String addTodo(lux.data.Task task) {
            todoAdded = true;
            String message = "Added a new deadline task:\n    " + task;
            return message;
        }

        public boolean getTodoAdded() {
            return todoAdded;
        }
    }

    static class StubTaskList extends lux.data.TaskList {
        private boolean added = false;

        @Override
        public void addTasks(lux.data.Task task) {
            added = true;
        }

        public boolean getAdded() {
            return added;
        }
    }

    static class StubStorage extends lux.storage.Storage {
        StubStorage() {
            super("dummy");
        }
    }

    @Test
    public void testExecuteAddsTodo() {
        try {
            TodoCommand cmd = new TodoCommand("test task");
            StubUi ui = new StubUi();
            StubTaskList tasks = new StubTaskList();
            StubStorage storage = new StubStorage();
            cmd.execute(tasks, ui, storage, null);
            assertTrue(tasks.added);
            assertTrue(ui.todoAdded);
        } catch (lux.exception.LuxException e) {
            // Not expected in this test
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsExit() {
        assertFalse(new TodoCommand("test").isExit());
    }
}
