package bobbywasabi.storage;

import bobbywasabi.exceptions.BobbyWasabiException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
