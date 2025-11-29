package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Deadline}.
 */
public class DeadlineTest {

    @Test
    public void toString_prefixAndBy_included() {
        final Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        assertTrue(d.toString().startsWith("[D]"), "toString() should start with [D].");
        assertTrue(d.toString().contains("by:"), "toString() should contain 'by:'.");
    }

    @Test
    public void typeCode_returnsD() {
        final Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        assertEquals("D", d.typeCode());
    }

    @Test
    public void extraFieldsForSave_returnsIsoDate() {
        final Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        final String[] fields = d.extraFieldsForSave();
        assertEquals("2025-08-30T23:59:00", fields[0]);
    }

    @Test
    public void toSaveString_containsTypeDoneNameAndIso() {
        final Deadline d = new Deadline("submit report", "2025-08-30T23:59");
        final String s = d.toSaveString();
        assertTrue(s.startsWith("D|0|submit report|2025-08-30T23:59:00"));
    }
}
