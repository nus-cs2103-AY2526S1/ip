package bot.task;

import bot.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void removeTask_validIndex_success() throws InvalidCommandException {
        Task removeTarget = new Deadline("homework", "23-09-2025 2359", false);
        List<Task> data = new ArrayList<>();
        data.add(new Todo("buy food", true));
        data.add(removeTarget);
        data.add(new Event("Alibaba Earnings Call", "30-08-2025 0000",
                "30-08-2025 2359", false));

        TaskList taskList = new TaskList(data);

        Task removedTask = taskList.removeTask(2);
        assertEquals(removeTarget, removedTask);
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void removeTask_outOfBoundIndex_failure() {
        Task removeTarget = new Deadline("homework", "23-09-2025 2359", false);
        List<Task> data = new ArrayList<>();
        data.add(new Todo("buy food", true));
        data.add(removeTarget);
        data.add(new Event("Alibaba Earnings Call", "30-08-2025 0000",
                "30-08-2025 2359", false));

        TaskList taskList = new TaskList(data);

        try {
            taskList.removeTask(5);
        } catch (InvalidCommandException e) {
            assertEquals("Invalid Command: Invalid task number", e.getMessage());
        }
    }
}
