package july.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import july.datetime.StringTime;
import july.error.InvalidDeadlineException;

class DeadlineTest {

    @Test
    void testConstructorAndToString() {
        Deadline d = new Deadline("submit report", "01/09/2025 18:00");
        assertEquals("[D][ ] submit report (by: 01 Sep 2025, 18:00)", d.toString());
    }

    @Test
    void testProcessValid() throws InvalidDeadlineException {
        Deadline d = Deadline.process("finish homework /by 02/09/2025 12:00");
        assertEquals("[D][ ] finish homework (by: 02 Sep 2025, 12:00)", d.toString());
    }

    @Test
    void testProcessInvalid() {
        assertThrows(InvalidDeadlineException.class, () -> {
            Deadline.process("finish homework no delimiter");
        });
    }

    @Test
    void testToSave() {
        Deadline d = new Deadline("read book", "03/09/2025 09:00");
        String expected = "D 0 read book /by 03/09/2025 09:00";
        assertEquals(expected, d.toSave());
    }

    @Test
    void testCheck1() {
        Deadline d = new Deadline("exam", "05/09/2025 18:00");
        StringTime timeEarlier = new StringTime("05/09/2025 17:00");
        StringTime timeSame = new StringTime("05/09/2025 18:00");

        assertTrue(d.check(timeEarlier));
        assertTrue(d.check(timeSame));
    }

    @Test
    void testCheck2() {
        Deadline d = new Deadline("exam", "05/09/2025 12:00");
        StringTime timeLater = new StringTime("05/09/2025 18:00");

        assertFalse(d.check(timeLater));
    }

    @Test
    void testCheck3() {
        Deadline d = new Deadline("exam", "sometime soon"); // invalid date
        StringTime time = new StringTime("05/09/2025 18:00");

        assertFalse(d.check(time));
    }
}
