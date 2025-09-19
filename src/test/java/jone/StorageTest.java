package jone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class StorageTest {

    @Test
    public void load_noFile_returnsEmptyList() throws JoneException {
        Storage s = new Storage("nonexistent.txt");
        List<Task> tasks = s.load();
        assertTrue(tasks.isEmpty(), "Loading from nonexistent file should give an empty list");
    }

    @Test
    public void save_thenLoad_returnsSameTask() throws JoneException {
        String testFile = "testdata.txt";
        Storage s = new Storage(testFile);

        Task t = new Todo("testing save");
        s.save(new ArrayList<>(List.of(t)));

        ArrayList<Task> loaded = s.load();
        assertEquals(1, loaded.size(), "Should load exactly one task");
        assertEquals("[T][ ] testing save", loaded.get(0).toString());

        // cleanup
        new File(testFile).delete();
    }
}
