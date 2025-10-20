package uy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageParseUnknownTaskTest {
    @Test
    public void testUnknownTaskTypeThrows() {
        Storage storage = new Storage("data");
        String badLine = "[Z][] Mystery task"; // Z is not a known type
        assertThrows(UnknownTaskError.class, () -> {
            storage.parseTask(badLine);
        });
    }
}
