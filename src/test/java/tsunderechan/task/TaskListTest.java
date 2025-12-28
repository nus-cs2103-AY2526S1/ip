package tsunderechan.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void mark_emptyList_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            actual.mark(0);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("That's not a valid task, you iiiiidiot!", e.getMessage());
        }
    }

    @Test
    public void mark_negativeIndex_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            TaskList actual2 = actual.getTodoTask();
            actual2.mark(-1);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("That's not a valid task, you iiiiidiot!", e.getMessage());
        }
    }

    @Test
    public void mark_largerThanCurrentSizeIndex_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            TaskList actual2 = actual.getTodoTask();
            actual2.mark(10);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("That's not a valid task, you iiiiidiot!", e.getMessage());
        }
    }

    @Test
    public void mark_alreadyMarked_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            TaskList actual2 = actual.getTodoTask();
            actual2.mark(1);
            actual2.mark(1);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("You've already asked me to mark it, geez!", e.getMessage());
        }
    }

    @Test
    public void mark_todoTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getTodoTask();
        actual2.mark(1);
        assertEquals("[T][X] homework", actual2.getTask(0).toString());
    }

    @Test
    public void mark_deadlineTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getDeadlineTask();
        actual2.mark(1);
        assertEquals("[D][X] homework (by: Mar 29 2025 11:22 pm)", actual2.getTask(0).toString());
    }

    @Test
    public void mark_eventTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getEventTask();
        actual2.mark(1);
        assertEquals("[E][X] CCA (from: Mar 29 2025 11:22 pm to: Mar 30 2025 12:22 am)", actual2.getTask(0).toString());
    }

    @Test
    public void mark_multipleTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getMultipleTask();
        actual2.mark(1);
        actual2.mark(2);
        actual2.mark(3);
        assertEquals("[E][X] CCA (from: Mar 29 2025 11:22 pm to: Mar 30 2025 12:22 am)", actual2.getTask(0).toString());
        assertEquals("[D][X] homework (by: Mar 29 2025 11:22 pm)", actual2.getTask(1).toString());
        assertEquals("[T][X] homework", actual2.getTask(2).toString());
    }

    @Test
    public void delete_emptyList_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            actual.delete(0);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("That's not a valid task, you iiiiidiot!", e.getMessage());
        }
    }

    @Test
    public void delete_negativeIndex_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            TaskList actual2 = actual.getTodoTask();
            actual2.delete(-1);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("That's not a valid task, you iiiiidiot!", e.getMessage());
        }
    }

    @Test
    public void delete_largerThanCurrentSizeIndex_exceptionThrown() {
        try {
            TaskListStub actual = new TaskListStub();
            TaskList actual2 = actual.getTodoTask();
            actual2.delete(10);
            assertEquals(new TaskListStub(), actual);
            fail(); // the test should not reach this line
        } catch (IllegalArgumentException e) {
            assertEquals("That's not a valid task, you iiiiidiot!", e.getMessage());
        }
    }

    @Test
    public void delete_todoTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getTodoTask();
        actual2.delete(1);
        assertEquals(0, actual2.getSize());
    }

    @Test
    public void delete_deadlineTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getDeadlineTask();
        actual2.delete(1);
        assertEquals(0, actual2.getSize());
    }

    @Test
    public void delete_eventTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getEventTask();
        actual2.delete(1);
        assertEquals(0, actual2.getSize());
    }

    @Test
    public void delete_multipleTask_success() {
        TaskListStub actual = new TaskListStub();
        TaskList actual2 = actual.getMultipleTask();
        actual2.delete(1);
        assertEquals(2, actual2.getSize());
        actual2.delete(2);
        assertEquals(1, actual2.getSize());
        actual2.delete(1);
        assertEquals(0, actual2.getSize());
    }

}
