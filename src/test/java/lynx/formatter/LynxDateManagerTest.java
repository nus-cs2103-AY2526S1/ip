package lynx.formatter;

import lynx.exception.LynxException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class LynxDateManagerTest {

    @Test
    public void parseDateTime() throws LynxException {
        LocalDateTime testDateA = LocalDateTime.of(2025, 11, 11, 0, 0);
        LocalDateTime testDateB = LocalDateTime.of(2025, 11, 11, 6, 0);
        LocalDateTime testDateC = LocalDateTime.of(2025, 11, 11, 6, 30);
        assertEquals(testDateA, LynxDateManager.parseDateTime("2025-11-11"));
        assertEquals(testDateB, LynxDateManager.parseDateTime("2025-11-11-06"));
        assertEquals(testDateC, LynxDateManager.parseDateTime("2025-11-11-06-30"));

        try {
            LynxDateManager.parseDateTime("2025-13-11");
            fail();
        } catch (LynxException e1) {
            try {
                LynxDateManager.parseDateTime("2025-11-11-6");
                fail();
            } catch (LynxException e2) {
                try {
                    LynxDateManager.parseDateTime("2025-11-11-00-00-00");
                    fail();
                } catch (LynxException e3) {

                }
            }
        }
    }

    @Test
    public void defaultDateTime_translate_textDateTime() {
        LocalDateTime testDate = LocalDateTime.of(2025, 11, 11, 6, 30);
        assertEquals("2025-11-11-06-30", LynxDateManager.defaultDateTime(testDate));
        assertEquals("Nov 11 2025 06:30", LynxDateManager.textDateTime(testDate));
    }

}
