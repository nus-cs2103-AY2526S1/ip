package aqua.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aqua.exception.IllegalDataException;
import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;

public class TaskListTest {
    @Test
    public void delete_existingTask_taskDeleted() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Delete me");
        taskList.add(todo);
        Task removed = taskList.delete(0);
        assertEquals(todo, removed);
        assertEquals(0, taskList.size());
    }

    @Test
    public void delete_invalidIndex_throwsException() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Delete fail");
        taskList.add(todo);
        try {
            taskList.delete(1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(1, taskList.size());
        }
    }

    @Test
    public void setPriority_validIndex_prioritySet() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Priority");
        taskList.add(todo);
        taskList.setPriority(0, Task.Priority.HIGH);
        assertEquals(Task.Priority.HIGH, taskList.get(0).priority);
    }

    @Test
    public void setPriority_invalidIndex_throwsException() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Priority fail");
        taskList.add(todo);
        try {
            taskList.setPriority(1, Task.Priority.LOW);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(1, taskList.size());
        }
    }

    @Test
    public void markTaskDone_validIndex_taskMarkedDone() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Done");
        taskList.add(todo);
        taskList.markTaskDone(0);
        assertEquals(true, taskList.get(0).isDone);
    }

    @Test
    public void markTaskNotDone_validIndex_taskMarkedNotDone() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Not done");
        taskList.add(todo);
        taskList.markTaskDone(0);
        taskList.markTaskNotDone(0);
        assertEquals(false, taskList.get(0).isDone);
    }

    @Test
    public void find_keywordPresent_returnsMatchingTasks() throws InvalidArgumentException, StorageException {
        Task todo1 = new StubToDo("Alpha");
        Task todo2 = new StubToDo("Beta");
        taskList.add(todo1);
        taskList.add(todo2);
        assertEquals(1, taskList.find("Alpha").size());
        assertEquals(todo1, taskList.find("Alpha").get(0));
    }

    @Test
    public void find_keywordAbsent_returnsEmptyList() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Gamma");
        taskList.add(todo);
        assertEquals(0, taskList.find("Delta").size());
    }

    @Test
    public void isEmpty_noTasks_returnsTrue() {
        assertEquals(true, taskList.isEmpty());
    }

    @Test
    public void isEmpty_withTasks_returnsFalse() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Not empty");
        taskList.add(todo);
        assertEquals(false, taskList.isEmpty());
    }
    private static final Path testPath = Path.of("data", "tasklist_test.txt");
    private TaskList taskList;

    class StubToDo extends aqua.task.Task {
        private String tempDescription;

        public StubToDo(String description) throws InvalidArgumentException {
            super(description);
            this.tempDescription = description;
        }

        @Override
        public String toString() {
            return "T | 0 | " + tempDescription;
        }

        @Override
        public String toStorageString() {
            return "T | 0 | " + tempDescription;
        }
    }

    @BeforeEach
    public void setUp() throws IOException {
        Files.createDirectories(testPath.getParent());
        Files.deleteIfExists(testPath);
        Files.createFile(testPath);
        taskList = new TaskList(testPath);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(testPath);
    }

    @Test
    public void loadFromStorage_emptyFile_success() throws StorageException, IllegalDataException {
        taskList.loadFromStorage();
        assertEquals(0, taskList.size());
    }

    @Test
    public void add_toDoTask_success() throws InvalidArgumentException, StorageException {
        Task todo = new StubToDo("Test task");
        taskList.add(todo);
        assertEquals(1, taskList.size());
    }
}
