package bobbywasabi.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bobbywasabi.exceptions.BobbyWasabiException;



public class StorageTest {

    @Test
    public void load_fileNotFound_exceptionThrown() {
        try {
            Storage storage = new Storage("./src/main/data/BobbyWasabiTasks.txt", "./src/main/data");
            storage.load();
            fail();
        } catch (BobbyWasabiException e) {
            assertEquals("File not found!", e.getMessage());
        }
    }
}
