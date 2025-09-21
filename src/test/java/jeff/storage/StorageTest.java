package jeff.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

class StorageTest {

    @Test
    void testLoad() {
        Storage storage = new Storage();
        ArrayList<String> result = storage.load();
        assertNotNull(result);
    }

    @Test
    void testSaveAndLoad() {
        Storage storage = new Storage();
        ArrayList<String> testData = new ArrayList<>();
        testData.add("T|0|Test todo");

        storage.save(testData);

        ArrayList<String> loadedData = storage.load();
        assertTrue(loadedData.contains("T|0|Test todo"));
    }

    @Test
    void testSaveEmpty() {
        Storage storage = new Storage();
        ArrayList<String> emptyData = new ArrayList<>();
        storage.save(emptyData);

        ArrayList<String> loadedData = storage.load();
        assertEquals(0, loadedData.size());
    }
}
