package peanutbutter.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.tasks.Todo;
import peanutbutter.tasks.Task;

public class StorageTest {

    @Test
    public void writeAndReadSingleTask() throws JuinException {
        String testFile = "Data/testStorage.txt";
        Storage storage = new Storage(testFile);

        File folder = new File("Data");
        if (!folder.exists()) folder.mkdir();

        File file = new File(testFile);
        if (file.exists()) file.delete();

        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("Test Todo"));

        storage.write(taskList);

        List<Task> loadedTasks = storage.read();

        assertEquals("Test Todo", loadedTasks.get(0).getDescription());

        if (file.exists()) file.delete();
    }
}
