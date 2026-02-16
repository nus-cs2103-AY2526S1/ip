package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineCommandTest extends CommandTest {

    @Test
    void deadlineCommand_addsTaskWithCorrectDate() {
        String response = lmbd.getResponse("deadline submit assignment /by 2023-12-31");
        assertEquals("Added the DEADLINE task submit assignment (by: Dec 31 2023), you now have 1 tasks.", response);
        assertEquals(1, lmbd.tasks.getTaskSize());
        assertEquals("[D][ ] submit assignment (by: Dec 31 2023)", lmbd.tasks.getTaskToString(0));
    }

    @Test
    void deadlineCommand_missingByFlag_returnsErrorMessage() {
        String response = lmbd.getResponse("deadline submit assignment");
        assertEquals("Expected /by, reached end of line instead", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void deadlineCommand_invalidDateFormat_returnsErrorMessage() {
        String response = lmbd.getResponse("deadline submit assignment /by 31-12-2023"); // Incorrect format
        assertEquals("Expected a date in YYYY-MM-DD format", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void deadlineCommand_emptyDescription_returnsErrorMessage() {
        String response = lmbd.getResponse("deadline /by 2023-12-31"); // Name is empty before /by
        assertEquals("The deadline task name cannot be empty.", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void deadlineCommand_emptyByDate_returnsErrorMessage() {
        String response = lmbd.getResponse("deadline task name /by "); // /by with no date
        assertEquals("The '/by' date cannot be empty.", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }
}
