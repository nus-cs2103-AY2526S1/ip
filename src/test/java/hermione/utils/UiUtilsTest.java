package hermione.utils;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hermione.tasks.Task;
import hermione.tasks.TaskList;
import hermione.tasks.ToDo;

public class UiUtilsTest {

    @Test
    public void getGreeting_validName_returnsFormattedGreeting() {
        String name = "Hermione";
        String result = UiUtils.getGreeting(name);
        String expected = "Hello! I'm Hermione, your personal assistant!\n"
                + "What magical tasks can I help you organize today?";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getGreeting_emptyName_returnsFormattedGreeting() {
        String name = "";
        String result = UiUtils.getGreeting(name);
        String expected = "Hello! I'm , your personal assistant!\n"
                + "What magical tasks can I help you organize today?";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getGreeting_nullName_returnsFormattedGreeting() {
        String name = null;
        String result = UiUtils.getGreeting(name);
        String expected = "Hello! I'm null, your personal assistant!\n"
                + "What magical tasks can I help you organize today?";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAddTaskString_singleTask_returnsCorrectString() {
        // Create a mock TaskStorage
        MockTaskStorage mockStorage = new MockTaskStorage(1);
        Task task = new ToDo("Read book", false);

        String result = UiUtils.getAddTaskString(task, mockStorage);
        String expected = "Brilliant! I've added this task to your list:\n"
                + "[T][ ] Read book"
                + "\nNow you have 1 tasks to complete. Time to get organized!";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAddTaskString_multipleTasks_returnsCorrectString() {
        // Create a mock TaskStorage with 5 tasks
        MockTaskStorage mockStorage = new MockTaskStorage(5);
        Task task = new ToDo("Read book", false);

        String result = UiUtils.getAddTaskString(task, mockStorage);
        String expected = "Brilliant! I've added this task to your list:\n"
                + "[T][ ] Read book"
                + "\nNow you have 5 tasks to complete. Time to get organized!";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAddTaskString_zeroTasks_returnsCorrectString() {
        // Create a mock TaskStorage with 0 tasks
        MockTaskStorage mockStorage = new MockTaskStorage(0);
        Task task = new ToDo("Read book", false);

        String result = UiUtils.getAddTaskString(task, mockStorage);
        String expected = "Brilliant! I've added this task to your list:\n"
                + "[T][ ] Read book"
                + "\nNow you have 0 tasks to complete. Time to get organized!";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getAddTaskString_completedTask_returnsCorrectString() {
        // Create a mock TaskStorage
        MockTaskStorage mockStorage = new MockTaskStorage(3);
        Task task = new ToDo("Read book", true);

        String result = UiUtils.getAddTaskString(task, mockStorage);
        String expected = "Brilliant! I've added this task to your list:\n"
                + "[T][X] Read book"
                + "\nNow you have 3 tasks to complete. Time to get organized!";
        Assertions.assertEquals(expected, result);
    }

    // Mock TaskStorage class for testing
    private static class MockTaskStorage implements hermione.storage.TaskStorage {
        private final int taskCount;

        public MockTaskStorage(int taskCount) {
            this.taskCount = taskCount;
        }

        @Override
        public TaskList getTasks() {
            return new MockTaskList(taskCount);
        }

        // Implement other required methods with default behavior
        @Override
        public void addTask(Task task) {
            // Mock implementation - not used in tests
        }

        @Override
        public Task deleteTask(int index) {
            // Mock implementation - not used in tests
            return null;
        }

        @Override
        public void setTaskCompletion(Task task, boolean isCompleted) {
            // Mock implementation - not used in tests
        }
    }

    // Mock TaskList class for testing
    private static class MockTaskList extends TaskList {
        private final int size;

        public MockTaskList(int size) {
            super(new ArrayList<>()); // Initialize with empty list
            this.size = size;
        }

        @Override
        public int getSize() {
            return size;
        }
    }
}
