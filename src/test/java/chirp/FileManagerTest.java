package chirp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import chirp.exceptions.ChirpException;
import chirp.tasks.Deadline;
import chirp.tasks.Event;
import chirp.tasks.TaskList;
import chirp.tasks.Todo;

public class FileManagerTest {
    private final String filePath = "data/test.txt";

    @Test
    public void emptyFileTest() throws IOException, ChirpException {
        deleteFile();
        FileManager fileManager = new FileManager(filePath);
        TaskList taskList = fileManager.loadTasks();
        assertEquals(0, taskList.getNumOfTasks());
    }

    @Test
    public void saveTasksTest() throws IOException, ChirpException {
        deleteFile();
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Test1"));
        taskList.addTask(new Deadline("Test2", "2025-12-12"));
        taskList.addTask(new Event("Test3", "2025-12-12", "2025-12-13"));
        FileManager fileManager = new FileManager(filePath);
        fileManager.saveTasks(taskList);
        TaskList newTaskList = fileManager.loadTasks();
        assertEquals(3, newTaskList.getNumOfTasks());
    }

    private void deleteFile() throws IOException {
        Path file = Paths.get(filePath);
        Files.deleteIfExists(file);
    }
}
