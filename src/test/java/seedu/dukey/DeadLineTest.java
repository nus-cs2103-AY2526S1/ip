package seedu.dukey;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import exceptions.DukeyException;
import tasks.DeadLine;




public class DeadLineTest {

    @Test
    public void initialisationTest() throws DukeyException {
        DeadLine dummy = new DeadLine("return book /by 01/02/2025 1400", false);
        assertEquals("return book", dummy.getDesc());

        String input = "01/02/2025 1400";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);

        assertEquals(dateTime.getDayOfMonth(), dummy.getDueDate().getDayOfMonth());
        assertEquals(dateTime.getMonthValue(), dummy.getDueDate().getMonthValue());
        assertEquals(dateTime.getYear(), dummy.getDueDate().getYear());

    }
    @Test
    void testDeadlineInitializationThrows() {

        DukeyException exceptionDescription = assertThrows(DukeyException.class, () ->
                new DeadLine("/by 01/02/2025 1400", true)); // the code you expect to fail
        assertEquals("Description Missing!", exceptionDescription.getMessage());

        DukeyException exceptionDate = assertThrows(DukeyException.class, () ->
                new DeadLine("todo book", true)); // the code you expect to fail
        assertEquals("Date Missing!", exceptionDate.getMessage());

        DukeyException exceptionDate2 = assertThrows(DukeyException.class, () ->
                new DeadLine("return book 01/02/2025 1400", true)); // the code you expect to fail
        assertEquals("Date Missing!", exceptionDate2.getMessage());
    }

}
