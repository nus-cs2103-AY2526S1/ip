package phuc.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList;
    private DeadlineTask deadlineTask;
    private EventTask eventTask;
    private ToDoTask todoTask;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();

        deadlineTask = new DeadlineTask(
                "Submit report",
                LocalDateTime.of(2023, 12, 15, 17, 0)
        );

        eventTask = new EventTask(
                "Team meeting",
                LocalDateTime.of(2023, 12, 15, 14, 0),
                LocalDateTime.of(2023, 12, 15, 16, 0)
        );

        todoTask = new ToDoTask("Buy groceries");
    }

    @Test
    public void testAddAndGetTask() {
        taskList.add(todoTask);
        assertEquals(1, taskList.size());
        assertEquals(todoTask, taskList.get(0));
    }

    @Test
    public void testRemoveTask() {
        taskList.add(todoTask);
        Task removed = taskList.remove(0);
        assertEquals(todoTask, removed);
        assertEquals(0, taskList.size());
        assertEquals(true, taskList.isEmpty());
    }
}