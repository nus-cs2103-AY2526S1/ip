package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventCommandTest extends CommandTest {

    @Test
    void eventCommand_addsTaskWithCorrectDates() {
        String response = lmbd.getResponse("event project meeting /from 2023-10-20 /to 2023-10-21");
        assertEquals("Added the EVENT task project meeting (from: Oct 20 2023, to: Oct 21 2023), you now have 1 tasks.",
            response);
        assertEquals(1, lmbd.tasks.getTaskSize());
        assertEquals("[E][ ] project meeting (from: Oct 20 2023, to: Oct 21 2023)", lmbd.tasks.getTaskToString(0));
    }

    @Test
    void eventCommand_missingFromFlag_returnsErrorMessage() {
        String response = lmbd.getResponse("event project meeting /to 2023-10-21");
        assertEquals("Expected /from, reached end of line instead", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void eventCommand_missingToFlag_returnsErrorMessage() {
        String response = lmbd.getResponse("event project meeting /from 2023-10-20");
        assertEquals("Expected /to, reached end of line instead", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void eventCommand_invalidFromDateFormat_returnsErrorMessage() {
        String response = lmbd.getResponse("event project meeting /from 20-10-2023 /to 2023-10-21");
        assertEquals("Expected \"from\" to be in YYYY-MM-DD format", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void eventCommand_invalidToDateFormat_returnsErrorMessage() {
        String response = lmbd.getResponse("event project meeting /from 2023-10-20 /to 21/10/2023");
        assertEquals("Expected \"to\" to be in YYYY-MM-DD format", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void eventCommand_emptyDescription_returnsErrorMessage() {
        String response = lmbd.getResponse("event /from 2023-10-20 /to 2023-10-21");
        assertEquals("The event task name cannot be empty.", response); // Assuming similar improvements
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void eventCommand_emptyFromDate_returnsErrorMessage() {
        String response = lmbd.getResponse("event task /from /to 2023-10-21");
        assertEquals("The '/from' date cannot be empty.", response); // Empty string also fails parse
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void eventCommand_emptyToDate_returnsErrorMessage() {
        String response = lmbd.getResponse("event task /from 2023-10-20 /to");
        assertEquals("The '/to' date cannot be empty.", response); // Empty string also fails parse
        assertEquals(0, lmbd.tasks.getTaskSize());
    }
}
