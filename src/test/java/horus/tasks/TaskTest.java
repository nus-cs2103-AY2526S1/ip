package horus.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

    @Test
    void testConstructorAndToString() {
        Task task = new Task("Read book");
        assertEquals("[ ] Read book", task.toString());
    }

    @Test
    void testMarkAndUnmark() {
        Task task = new Task("Finish homework");
        assertFalse(task.isMarked); // initially unmarked

        task.mark();
        assertTrue(task.isMarked);
        assertEquals("[X] Finish homework", task.toString());

        task.unmark();
        assertFalse(task.isMarked);
        assertEquals("[ ] Finish homework", task.toString());
    }

    @Test
    void testGetTaskData() {
        Task task = new Task("Clean room");
        assertEquals("F,Clean room", task.getTaskData());

        task.mark();
        assertEquals("T,Clean room", task.getTaskData());
    }

    @Test
    void testEquals() {
        Task task1 = new Task("Go jogging");
        Task task2 = new Task("Go jogging");
        Task task3 = new Task("Go swimming");

        ToDoTask todoTask1 = new ToDoTask("Go jogging");

        assertNotEquals(task1, null); //Handles null objects

        assertNotEquals(task1, todoTask1); //Task of a different class is not equal

        assertEquals(task1, task2);   // Tasks with the same description are equal
        assertNotEquals(task1, task3);

        task1.mark();
        assertNotEquals(task1, task2); // Marking changes equality
    }

    @Test
    void testReadTaskDataInvalidType() {
        assertThrows(InvalidInputException.class, () -> {
            Task.readTaskData("X,F,Some task");
        });
    }
}
