package lucid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void lineToEvent_success() throws IncorrectDataFormatException {
        Storage storage = new Storage();
        assertEquals("[E][ ] birthday party (from: DECEMBER 12 2025 12:00 to: DECEMBER 12 2025 16:30)",
                storage.lineToEvent("E | NOT DONE | birthday party | 2025-12-12-1200 | 2025-12-12-1630").toString());
        assertEquals("[E][X] birthday party (from: DECEMBER 12 2025 to: DECEMBER 12 2025)",
                storage.lineToEvent("E | DONE | birthday party | 2025-12-12 | 2025-12-12").toString());
    }

    // Used ChatGPT to add this test to account for file corruption / unexpected 3rd party editing - 18 September 2025
    @Test
    public void lineToEvent_invalidLine_throwsException() {
        Storage storage = new Storage();
        String invalidLine = "E | DONE | birthday party | 2025-12-12"; // only 4 parts

        // CHECKSTYLE.OFF: SeparatorWrap
        IncorrectDataFormatException e = assertThrows(
                IncorrectDataFormatException.class,
                () -> storage.lineToEvent(invalidLine)
        );
        // CHECKSTYLE.ON: SeparatorWrap
    }
}
