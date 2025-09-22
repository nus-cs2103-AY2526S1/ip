package izayoi;

import izayoi.task.Actionable;
import izayoi.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void markTask() {
        final boolean[] taskStatus = {false, false, false};
        Actionable a = new StubTask(taskStatus, 0);
        Actionable b = new StubTask(taskStatus, 1);
        Actionable c = new StubTask(taskStatus, 2);

        TaskManager tm = new TaskManager();
        tm.addTask(a);
        tm.addTask(b);

        assertArrayEquals(new boolean[]{false, false, false}, taskStatus,
                "all tasks initially not marked");

        tm.markTask(2);
        assertArrayEquals(new boolean[]{false, true, false}, taskStatus,
                "task index marked is correct");

        tm.addTask(c);
        assertArrayEquals(new boolean[]{false, true, false}, taskStatus,
                "adding tasks keeps current tasks unchanged");

        tm.markTask(2);
        assertArrayEquals(new boolean[]{false, true, false}, taskStatus,
                "remarking completed task succeeds");

        tm.markTask(3);
        assertArrayEquals(new boolean[]{false, true, true}, taskStatus,
                "multiple tasks can be marked");
    }

    /**
     * Task Stub that reflects changes to an external array
     */
    private static final class StubTask implements Actionable {
        private final boolean[] tracker;
        private final int index;

        public StubTask(boolean[] tracker, int index) {
            this.tracker = tracker;
            this.index = index;
        }

        @Override
        public void markAsDone() {
            tracker[index] = true;
        }

        @Override
        public void markAsNotDone() {
            tracker[index] = false;
        }

        @Override
        public boolean isCompleted() {
            return tracker[index];
        }
    }
}