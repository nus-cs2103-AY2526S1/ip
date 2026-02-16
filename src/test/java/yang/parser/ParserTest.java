package yang.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import yang.YangException;
import yang.task.Deadline;
import yang.task.TaskList;
import yang.task.Todo;

/**
 * Tests the {@link Parser} class.
 */
public class ParserTest {

    @Test
    void todo_addsTaskAndReturnsResult() throws YangException {
        TaskList tl = new TaskList();
        CommandResult res = Parser.apply("todo read book", tl);

        assertEquals(CommandResult.Type.ADDED, res.type);
        assertEquals(1, tl.size());
        assertTrue(res.task instanceof Todo);
        assertTrue(res.task.toString().contains("read book"));
    }

    @Test
    void deadline_parsesIsoDate_andFormatsNicely() throws YangException {
        TaskList tl = new TaskList();
        CommandResult res = Parser.apply("deadline return book /by 2019-12-02", tl);

        assertEquals(CommandResult.Type.ADDED, res.type);
        assertEquals(1, tl.size());
        assertTrue(res.task instanceof Deadline);

        String expectedDate = LocalDate.parse("2019-12-02")
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assertTrue(res.task.toString().contains(expectedDate)); // e.g., "Dec 2 2019"
    }

    @Test
    void todo_emptyDescription_throws() {
        TaskList tl = new TaskList();
        assertThrows(YangException.class, () -> Parser.apply("todo   ", tl));
    }

    @Test
    void delete_invalidIndex_throws() throws YangException {
        TaskList tl = new TaskList();
        tl.add(new Todo("x"));
        assertThrows(YangException.class, () -> Parser.apply("delete 9", tl));
    }
}
