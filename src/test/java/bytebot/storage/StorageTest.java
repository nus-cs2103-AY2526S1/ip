package bytebot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bytebot.task.TaskList;

public class StorageTest {

    @BeforeEach
    public void cleanData() throws Exception {
        File dir = new File("data");
        File file = new File(dir, "byte.txt");
        if (file.exists()) {
            file.delete();
        }
        if (dir.exists() && dir.isDirectory()) {
        }
    }

    @Test
    public void load_createFileWhenMissing_andReturnsEmptyList() throws Exception {
        Storage s = new Storage();
        TaskList tl = s.load();
        assertEquals(0, tl.size());
        assertTrue(new File("data/byte.txt").exists());
    }
}


