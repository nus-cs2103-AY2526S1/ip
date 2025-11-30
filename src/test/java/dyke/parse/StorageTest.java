package dyke.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dyke.data.Library;

public class StorageTest {
    @Test
    public void testFailedDecode() {
        String filePath = "./data/test.txt";
        Storage storage = new Storage(filePath);
        String error = storage.loadTasks(new Library());
        assertEquals("Error loading tasks: \nUnknown task type in file: K", error);
    }
    @Test
    public void testLoadTasks() {
        String filePath = "./data/normal.txt";
        Storage storage = new Storage(filePath);
        Library library = new Library();
        String normal = storage.loadTasks(library);
        assertEquals("Loaded tasks: 1", normal);
        assertEquals(
                "Here it is!\n"
                        + "1. [E] [ ] Research Duration (from: 2125-09-30 to: 2125-10-25)",
                library.printList());
    }
}
