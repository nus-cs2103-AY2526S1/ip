package focus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Test
    // Perform various tests on todo command and associated edge cases
    void parseTodo_valid() throws Exception {
        int currentTaskListSize = 0;
        FocusCommand fc;
        try {
            fc = InputParser.parse("todo", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     OOPS!!! The description of a todo command cannot be empty.");
        }
        fc = InputParser.parse("todo read book", currentTaskListSize);
        assertTrue(fc instanceof TodoCommand);
    }

    @Test
    // Perform various tests on deadline command and associated edge cases
    void parseDeadline_valid() throws Exception {
        int currentTaskListSize = 0;
        FocusCommand fc;
        try {
            fc = InputParser.parse("deadline", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     OOPS!!! The description of a deadline command cannot be empty.");
        }
        try {
            fc = InputParser.parse("deadline e", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     Usage: deadline <description> /by yyyy-MM-dd HHmm");
        }
        fc = InputParser.parse("deadline Work on CS2105 Assignment -1 /by 2025-10-01 2359", currentTaskListSize);
        assertTrue(fc instanceof DeadlineCommand);
    }

    @Test
    // Perform various tests on parsing event command and associated edge cases
    void parseEvent_valid() throws Exception {
        int currentTaskListSize = 0;
        FocusCommand fc;
        try {
            fc = InputParser.parse("event", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     OOPS!!! The description of a event command cannot be empty.");
        }
        try {
            fc = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /FORGOTTO 2025-10-19 0800",
                    currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "     Usage: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        fc = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 2025-10-19 0800", currentTaskListSize);
        assertTrue(fc instanceof EventCommand);
    }

    @Test
    // Checks validing of mark command for edge cases
    void parseMark_valid() throws Exception {
        TaskList list = new TaskList();
        FocusCommand fc;
        fc = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 2025-10-19 0800", 0);
        fc.execute(list);
        fc = InputParser.parse("deadline Work on CS2105 Assignment -1 /by 2025-10-01 2359", 0);
        fc.execute(list);
        int currentTaskListSize = list.size();
        try {
            fc = InputParser.parse("mark -1", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Indices have to be positive integers!");
        }
        try {
            fc = InputParser.parse("mark 0", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Indices have to be positive integers!");
        }
        try {
            fc = InputParser.parse("mark 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. The following input index exceeds current list size: 3");
        }
        try {
            fc = InputParser.parse("mark 1 2 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. The following input index exceeds current list size: 3");
        }
        try {
            fc = InputParser.parse("mark -1 2 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Indices have to be positive integers!");
        }
        fc = InputParser.parse("mark 1 2", currentTaskListSize);
        assertTrue(fc instanceof MarkCommand);
    }

    @Test
    // Checks validing of unmark command for edge cases
    void parseUnmark_valid() throws Exception {
        TaskList list = new TaskList();
        FocusCommand fc;
        fc = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 2025-10-19 0800", 0);
        fc.execute(list);
        fc = InputParser.parse("deadline Work on CS2105 Assignment -1 /by 2025-10-01 2359", 0);
        fc.execute(list);
        int currentTaskListSize = list.size();
        list.get(0).markAsDone();
        list.get(1).markAsDone();
        try {
            fc = InputParser.parse("unmark -1", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Indices have to be positive integers!");
        }
        try {
            fc = InputParser.parse("unmark 0", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Indices have to be positive integers!");
        }
        try {
            fc = InputParser.parse("unmark 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. The following input index exceeds current list size: 3");
        }
        try {
            fc = InputParser.parse("unmark 1 2 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. The following input index exceeds current list size: 3");
        }
        try {
            fc = InputParser.parse("unmark -1 2 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Indices have to be positive integers!");
        }
        fc = InputParser.parse("unmark 1 2", currentTaskListSize);
        assertTrue(fc instanceof UnmarkCommand);
    }

    @Test
    // Checks validing of delete command for edge cases
    void parseDelete_valid() throws Exception {
        TaskList list = new TaskList();
        FocusCommand fc;
        fc = InputParser.parse("event LINE CTF 2025 /from 2025-10-18 0800 /to 2025-10-19 0800", 0);
        fc.execute(list);
        fc = InputParser.parse("deadline Work on CS2105 Assignment -1 /by 2025-10-01 2359", 0);
        fc.execute(list);
        int currentTaskListSize = list.size();
        try {
            fc = InputParser.parse("delete -1", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Index has to be a positive integer!");
        }
        try {
            fc = InputParser.parse("delete 0", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Index has to be a positive integer!");
        }
        try {
            fc = InputParser.parse("delete 3", currentTaskListSize);
        } catch (FocusException fe) {
            assertEquals(fe.getMessage(), "Invalid input. Index exceeds current list size!");
        }
        fc = InputParser.parse("delete 2", currentTaskListSize);
        assertTrue(fc instanceof DeleteCommand);
    }

}
