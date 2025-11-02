package olaf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import olaf.tasks.Task;
import olaf.tasks.TaskList;
import olaf.tasks.ToDo;

public class TaskListTest {

    @Test
    public void addTask_singleToDo_incrementsCount() throws OlafException {
        Storage dummySt = new Storage("testData/dummy.txt");
        TaskList taskList = new TaskList(new ArrayList<>(), dummySt);
        int initialCount = taskList.getCount();

        taskList.addTask(new ToDo("Sample"));
        assertEquals(initialCount+1, taskList.getCount());
    }

    @Test
    public void deleteTask_singleTaskDeletion_decrementsCount() {
        Storage dummySt = new Storage("testData/dummy.txt");
        ArrayList<Task> initial = new ArrayList<>();
        initial.add(new ToDo("Sample"));
        TaskList taskList = new TaskList(initial, dummySt);
        int initialCount = taskList.getCount();

        taskList.deleteTask(1);
        assertEquals(initialCount-1, taskList.getCount());
    }

    @Test
    public void deleteTask_emptyList_throwsException() {
        Storage dummySt = new Storage("testData/dummy.txt");
        TaskList taskList = new TaskList(new ArrayList<>(), dummySt);

        assertThrows(IndexOutOfBoundsException.class, ()->taskList.deleteTask(1));
    }

}
