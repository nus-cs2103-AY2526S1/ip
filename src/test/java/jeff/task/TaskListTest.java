package jeff.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

class TaskListTest {

    private TaskList taskList;
    private Task testTask1;
    private Task testTask2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        testTask1 = new Todo("Test task 1");
        testTask2 = new Todo("Test task 2");
    }

    @Test
    void testConstructor() {
        assertNotNull(taskList);
        assertEquals(0, taskList.size());
    }

    @Test
    void testAdd() {
        taskList.add(testTask1);
        assertEquals(1, taskList.size());
        assertEquals(testTask1, taskList.get(0));
    }

    @Test
    void testRemove() {
        taskList.add(testTask1);
        taskList.add(testTask2);

        Task removedTask = taskList.remove(0);
        assertEquals(testTask1, removedTask);
        assertEquals(1, taskList.size());
        assertEquals(testTask2, taskList.get(0));
    }

    @Test
    void testSize() {
        assertEquals(0, taskList.size());

        taskList.add(testTask1);
        assertEquals(1, taskList.size());

        taskList.remove(0);
        assertEquals(0, taskList.size());
    }
}
