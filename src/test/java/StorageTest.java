import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import itachi.Storage;

public class StorageTest {

    @Test
    void loadNonExistentFile_shouldCreateFile() throws Exception {
        String fakePath = "data/nonexistent.txt";
        File file = new File(fakePath);
        if (file.exists()) {
            file.delete();
        }

        Storage storage = new Storage(fakePath);
        storage.load(); // should create the file

        assertTrue(file.exists(), "The missing file should be created automatically.");
        file.delete(); // cleanup
    }
}

