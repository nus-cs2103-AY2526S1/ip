package cookie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList listOfTasks;
    private Task task;

    @BeforeEach
    void createTaskAndList() {
        listOfTasks = new TaskList();
        task = new Todo("sleep");
    }

    @Test
    public void size_emptyList_zeroReturned() {
        int returnedSize = listOfTasks.size();
        assertEquals(0, returnedSize);
    }

    @Test
    public void add_validInput_oneReturned() {
        listOfTasks.add(task);
        int newSize = listOfTasks.size();
        assertEquals(1, newSize);
    }

    @Test
    public void get_validIndex_returnsTask() {
        listOfTasks.add(task);
        Task returnedTask = listOfTasks.get(0);
        assertEquals(task, returnedTask);
    }

    @Test
    public void remove_validIndex_removesTask() {
        listOfTasks.add(task);
        listOfTasks.remove(0);
        int newReturnedSize = listOfTasks.size();
        assertEquals(0, newReturnedSize);
    }
}
