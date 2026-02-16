package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoCommandTest extends CommandTest {

    @Test
    void todoCommand_addsTaskAndReturnsMessage() {
        String response = lmbd.getResponse("todo read book");
        assertEquals("Added the TODO task read book, you now have 1 tasks.", response);
        assertEquals(1, lmbd.tasks.getTaskSize());
        assertEquals("[T][ ] read book", lmbd.tasks.getTaskToString(0));
    }

    @Test
    void todoCommand_emptyDescription_returnsErrorMessage() {
        String response = lmbd.getResponse("todo");
        assertEquals("todo command requires at least 1 args", response);
        assertEquals(0, lmbd.tasks.getTaskSize());
    }

    @Test
    void todoCommand_multipleWords_addsCorrectTaskName() {
        String response = lmbd.getResponse("todo finish project report");
        assertEquals("Added the TODO task finish project report, you now have 1 tasks.", response);
        assertEquals(1, lmbd.tasks.getTaskSize());
        assertEquals("[T][ ] finish project report", lmbd.tasks.getTaskToString(0));
    }
}
