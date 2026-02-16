package july.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StringTimeTest {

    @Test
    void testValidDateTime_ddMMyyyyHHmm() {
        StringTime st = new StringTime("05/07/2025 18:30");
        assertFalse(st.isString());
        assertEquals("05 Jul 2025, 18:30", st.toString());
        assertEquals("05/07/2025 18:30", st.toSave());
    }

    @Test
    void testValidDateTime_isString() {
        StringTime st = new StringTime("2025-07-05 07:15");
        assertFalse(st.isString());
        assertEquals("05 Jul 2025, 07:15", st.toString());
    }

    @Test
    void testValidDate_slashFormat() {
        StringTime st = new StringTime("5/7/2025"); // should become 00:00
        assertFalse(st.isString());
        assertEquals("05 Jul 2025, 00:00", st.toString());
    }

    @Test
    void testValidDate_dashFormat() {
        StringTime st = new StringTime("05-07-2025");
        assertFalse(st.isString());
        assertEquals("05 Jul 2025, 00:00", st.toString());
    }

    @Test
    void testInvalidString() {
        StringTime st = new StringTime("tomorrow evening");
        assertTrue(st.isString());
        assertEquals("tomorrow evening", st.toString());
        assertEquals("tomorrow evening", st.toSave());
    }

    @Test
    void testCompareToLessOrMore() {
        StringTime earlier = new StringTime("05/07/2025 01:00");
        StringTime later = new StringTime("05/07/2025 10:00");

        assertTrue(earlier.compareTo(later) < 0);
        assertTrue(later.compareTo(earlier) > 0);
    }

    @Test
    void testCompareToEqual() {
        StringTime t1 = new StringTime("05/07/2025 05:00");
        StringTime t2 = new StringTime("05/07/2025 05:00");

        assertEquals(0, t1.compareTo(t2));
    }
}
