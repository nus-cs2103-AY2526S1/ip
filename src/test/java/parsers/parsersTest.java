package parsers;

import exception.NicholasException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TaskList;
import tasks.ToDoTask;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class parsersTest {

    private Parser parser;
    private TaskList tasks;

    @BeforeEach
    public void setup() {
        parser = new Parser();
        tasks = new TaskList();
    }

    @Test
    public void parseCommand_testParseTodoCommand_addsTask() throws NicholasException {
        parser.parseCommand("todo Read book", tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDoTask);
        assertTrue(tasks.get(0).toString().contains("Read book"));
    }

    @Test
    public void parseCommand_testParseDeadlineCommand_addsDeadline() throws NicholasException {
        parser.parseCommand("deadline Submit report /by 2025-09-01 14:00", tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof DeadlineTask);
        assertTrue(tasks.get(0).toString().contains("Submit report"));
    }

    @Test
    public void parseCommand_testParseEventCommand_addsEvent() throws NicholasException {
        parser.parseCommand("event Meeting /from 2025-09-01 09:00 /to 2025-09-01 11:00", tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof EventTask);
        assertTrue(tasks.get(0).toString().contains("Meeting"));
    }

    @Test
    public void parseCommand_testInvalidCommand_throwsException() {
        assertThrows(NicholasException.class, () -> {
            parser.parseCommand("i want chicken", tasks);
        });
    }

    @Test
    public void prepareIndex_testPrepareIndex_validInput() throws NicholasException {
        int index = parser.prepareIndex("mark 3");
        assertEquals(3, index);
    }

    @Test
    public void parseCommand_missingNumber_throwsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            parser.prepareIndex("mark");
        });
    }

    @Test
    public void prepareDeadlineTask_testDeadlineMissingBy_throwsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            parser.prepareDeadlineTask("deadline Submit book"); // missing "/by"
        });
    }

    @Test
    public void parseCommand_testEventMissingFrom_throwsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            parser.prepareEventTask("event Meeting groupmates /to 2025-09-01 14:00"); // missing "/from"
        });
    }

    @Test
    public void parepareEventTask_testEventMissingTo_throwsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            parser.prepareEventTask("event Meeting friends for dinner /from 2025-09-01 09:00"); // missing "/to"
        });
    }

    @Test
    public void testPrepareIndex_notANumber_throwsException() {
        assertThrows(NumberFormatException.class, () -> {
            parser.prepareIndex("mark this");
        });
    }

    @Test
    public void testValidateDate_invalidFormat_throwsException() {
        assertThrows(DateTimeParseException.class, () -> { // could be DateTimeParseException
            parser.validateDate("2025/09/01"); // wrong format
        });
    }


}
