package som;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import som.task.Deadline;

public class DeadlineTest {

    @Test
    void createdDeadlineWithCorrectDateTime() throws SomException {
        //Arrange & Act
        Deadline deadline = new Deadline("Submit report", "2025-03-11 2359");

        // Assert
        assertEquals(2025, deadline.getBy().getYear());
        assertEquals(3, deadline.getBy().getMonthValue());
        assertEquals(11, deadline.getBy().getDayOfMonth());
        assertEquals(23, deadline.getBy().getHour());
        assertEquals(59, deadline.getBy().getMinute());
    }


    @Test
    void invalidDateFormatThrowsSomException() {
        // Assert
        assertThrows(SomException.class, () ->
                new Deadline("Invalid date", "2025/03/11 23:59")
        );
    }
}
