package buddy.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    void acceptsIsoDate() {
        Deadline d = new Deadline("submit report", "2019-12-02");
        assertEquals("D", d.getType());
        assertTrue(d.toString().contains("(by:"), "toString should include '(by:'");
    }
}
