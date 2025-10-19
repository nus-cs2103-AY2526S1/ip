package reboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import reboot.task.Deadline;
import reboot.task.Event;
import reboot.task.Task;
import reboot.task.Todo;

public class TaskListTest {

    @Test
    public void size_taskListWith3Tasks_success() {

        TaskList tasks = new TaskList();
        Task todo = new Todo("Read book", false);
        Task deadline = new Deadline("Do iP", false,
                LocalDateTime.of(2025, 9, 2, 21, 0));
        Task event = new Event("Yoga Class", false,
                LocalDateTime.of(2025, 9, 2, 17, 0),
                LocalDateTime.of(2025, 9, 2, 19, 0));

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        assertEquals(3, tasks.size());
    }

    @Test
    public void addTask_todoTask_success() {

        TaskList tasks = new TaskList();
        Task todo = new Todo("Read book", false);

        tasks.add(todo);
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Read book", tasks.get(0).toString());
    }

    @Test
    public void addTask_deadlineTask_success() {

        TaskList tasks = new TaskList();
        Task deadline = new Deadline("Do iP", false,
                LocalDateTime.of(2025, 9, 2, 21, 0));

        tasks.add(deadline);
        assertEquals(1, tasks.size());
        assertEquals("[D][ ] Do iP (by: 02 09 2025 2100)", tasks.get(0).toString());
    }

    @Test
    public void addTask_eventTask_success() {

        TaskList tasks = new TaskList();
        Task event = new Event("Yoga Class", false,
                LocalDateTime.of(2025, 9, 2, 17, 0),
                LocalDateTime.of(2025, 9, 2, 19, 0));

        tasks.add(event);
        assertEquals(1, tasks.size());
        assertEquals("[E][ ] Yoga Class (from: 02 09 2025 1700 to: 02 09 2025 1900)",
                tasks.get(0).toString());
    }

    @Test
    public void removeTask_todoTaskRemoval_success() {

        TaskList tasks = new TaskList();
        Task todo = new Todo("Read book", false);
        tasks.add(todo);

        tasks.remove(todo);

        assertEquals(0, tasks.size());
    }

    @Test
    public void clear_filledTaskList_success() {

        TaskList tasks = new TaskList();
        Task todo = new Todo("Read book", false);
        Task deadline = new Deadline("Do iP", false,
                LocalDateTime.of(2025, 9, 2, 21, 0)
        );
        Task event = new Event("Yoga Class", false,
                LocalDateTime.of(2025, 9, 2, 17, 0),
                LocalDateTime.of(2025, 9, 2, 19, 0));

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        tasks.clear();
        assertEquals(0, tasks.size());
    }

    @Test
    public void isEmpty_emptyTaskList_success() {

        TaskList tasks = new TaskList();
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void isOutOfRange_outOfRange_success() {

        TaskList tasks = new TaskList();
        Task todo = new Todo("Read book", false);
        Task deadline = new Deadline("Do iP", false,
                LocalDateTime.of(2025, 9, 2, 21, 0));
        Task event = new Event("Yoga Class", false,
                LocalDateTime.of(2025, 9, 2, 17, 0),
                LocalDateTime.of(2025, 9, 2, 19, 0));

        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        assertTrue(tasks.isOutOfRange(4));
    }

    // Test recommended by ChatGPT for the findTask function in TaskList.java
    @Test
    public void findTask_returnsCorrectTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book", false));
        tasks.add(new Todo("write essay", false));
        assertEquals(1, tasks.findTasks("book").size());
    }
}
