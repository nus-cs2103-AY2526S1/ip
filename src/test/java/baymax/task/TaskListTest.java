package baymax.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import baymax.exception.BaymaxException;

public class TaskListTest {

    private class TaskStub extends Task {
        private boolean isDone;

        private TaskStub() {
            super(false, "baymax");
            this.isDone = false;
        }

        @Override
        public String getStatus() {
            return this.isDone ? "X" : " ";
        }

        @Override
        public String toString() {
            return String.format("[%s] baymax", this.getStatus());
        }

        @Override
        public String mark() {
            this.isDone = true;
            return "Marked baymax";
        }

        @Override
        public String unmark() {
            this.isDone = false;
            return "Unmarked baymax";
        }

        @Override
        public String toSaveFormat() {
            return "baymax";
        }
    }

    @Test
    public void addTask_success() {
        TaskList tasks = new TaskList();
        TaskStub t = new TaskStub();

        assertEquals("""
                        Got it. I've added this task:
                        \t[ ] baymax
                        Now you have 1 tasks in the list.""",
                tasks.addTask(t));

        assertEquals(1, tasks.getAll().size());
    }

    @Test
    public void mark_validIndex_marksTask() throws BaymaxException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());

        assertEquals("Marked baymax", tasks.mark(0));
        assertEquals("[X] baymax", tasks.getAll().get(0).toString());
    }

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();

        assertThrows(
                BaymaxException.InvalidIndexException.class, () -> tasks.mark(0));
    }

    @Test
    public void unmark_validIndex_unmarksTask() throws BaymaxException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());

        assertEquals("Unmarked baymax", tasks.unmark(0));
        assertEquals("[ ] baymax", tasks.getAll().get(0).toString());
    }

    @Test
    public void unmark_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();

        assertThrows(
                BaymaxException.InvalidIndexException.class, () -> tasks.unmark(0));
    }

    @Test
    public void delete_validIndex_deletesTask() throws BaymaxException {
        TaskList tasks = new TaskList();
        TaskStub t = new TaskStub();

        tasks.addTask(t);

        assertEquals("""
                        Noted. I've removed this task:
                        \t[ ] baymax
                        Now you have 0 tasks in the list.""",
                tasks.delete(0));
        assertEquals(0, tasks.getAll().size());
    }

    @Test
    public void delete_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();

        assertThrows(
                BaymaxException.InvalidIndexException.class, () -> tasks.delete(0));
    }
}
