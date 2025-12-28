import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import models.Deadline;

public class DeadlineTest {
    @Test
    public void constructor_noArgs_emptyFields() {
        Deadline deadline = new Deadline();
        assertEquals(deadline.getName(), null);
        assertEquals(deadline.getDue(), null);
        assertFalse(deadline.getIsDone()); // default value of boolean is false
    }

    @Test
    public void constructor_nameDue_successful() {
        Deadline deadline = new Deadline("name", LocalDate.parse("2024-12-31"));
        assertEquals(deadline.getName(), "name");
        assertEquals(deadline.getDue(), LocalDate.parse("2024-12-31"));
        assertFalse(deadline.getIsDone());
    }

    @Test
    public void constructor_nameIsDoneDue_successful() {
        Deadline deadline = new Deadline("name", true, LocalDate.parse("2024-12-31"));
        assertEquals(deadline.getName(), "name");
        assertEquals(deadline.getDue(), LocalDate.parse("2024-12-31"));
        assertTrue(deadline.getIsDone());
    }
}
