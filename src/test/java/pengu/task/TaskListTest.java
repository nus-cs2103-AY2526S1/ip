package pengu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import pengu.exception.InvalidFieldException;
import pengu.exception.PenguException;

public class TaskListTest {
    @Test
    public void testAdd() throws PenguException {
        Todo todo1 = new Todo("Sleep", false);
        Todo todo2 = new Todo("Wake Up", false);
        Todo todo3 = new Todo("Eat", false);

        TaskList taskList = new TaskList();

        taskList.add(todo1);
        assertEquals(todo1, taskList.get(1));

        taskList.add(todo2);
        taskList.add(todo3);

        assertEquals(todo1, taskList.get(1));
        assertEquals(todo2, taskList.get(2));
        assertEquals(todo3, taskList.get(3));
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void testDelete() throws PenguException {
        Todo todo1 = new Todo("Sleep", false);
        Todo todo2 = new Todo("Wake Up", false);
        Todo todo3 = new Todo("Eat", false);

        TaskList taskList = new TaskList();

        taskList.add(todo1);
        taskList.add(todo2);
        taskList.add(todo3);

        taskList.remove(2);
        assertEquals(todo1, taskList.get(1));
        assertEquals(todo3, taskList.get(2));
        assertEquals(2, taskList.getSize());

        taskList.remove(1);
        assertEquals(todo3, taskList.get(1));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void delete_outOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        Todo todo1 = new Todo("Sleep", false);
        taskList.add(todo1);

        try {
            taskList.remove(0);
            fail();
        } catch (InvalidFieldException e) {
            assertEquals("Oh no! Your field(s) provided caused the following error:\n"
                    + "Expected: integer value in range [1, 1]\n" + "Given: 0", e.getMessage());
        }

        try {
            taskList.remove(2);
            fail();
        } catch (InvalidFieldException e) {
            assertEquals("Oh no! Your field(s) provided caused the following error:\n"
                    + "Expected: integer value in range [1, 1]\n" + "Given: 2", e.getMessage());
        }
    }
}
