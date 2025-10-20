package larry.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTimeFormatsTest {

    @Test
    void pretty_handlesDateOnly() {
        String out = DateTimeFormats.pretty("2025-10-31");
        assertNotNull(out);
        assertTrue(out.contains("2025"));
        assertTrue(out.contains("31"));
    }

    @Test
    void pretty_handlesDatetimeWithColon() {
        String out = DateTimeFormats.pretty("2025-10-31 23:59");
        assertNotNull(out);
        assertTrue(out.contains("2025"));
        assertTrue(out.contains("23:59") || out.toLowerCase().contains("11:59"));
    }

    @Test
    void pretty_handlesDatetimeCompact() {
        String out = DateTimeFormats.pretty("2025-10-31 0905");
        assertNotNull(out);
        assertTrue(out.contains("2025"));
        assertTrue(out.contains("09:05") || out.contains("9:05"));
    }


}
