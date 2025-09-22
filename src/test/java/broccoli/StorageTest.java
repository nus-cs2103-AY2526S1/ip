package broccoli;

import broccoli.Tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    TaskList emptyTaskList = new TaskList(new ArrayList<Task>());
    Storage testStorage = new Storage(emptyTaskList, "./data/dataForTest.txt");
    @Test
    public void isLoadFromFile_Success(){
        testStorage.loadFromFile(testStorage.getFilePath());
        ArrayList<Task> loadedTasks = testStorage.getTaskList();
       // System.out.println(loadedTasks.get(0).toString());
        assertEquals("[D] [ ]  finish lec (by: Dec 6 2019 7pm)", loadedTasks.get(0).toString());
    }

}
