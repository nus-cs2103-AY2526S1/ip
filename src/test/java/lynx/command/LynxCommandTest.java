package lynx.command;

import lynx.exception.LynxException;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.TodoTask;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

// Primarily tests the parsing responsible by LynxCommand
// mark, unmark, delete and find are not tested since they primarily rely on the
// correctness of methods within Task and LynxTaskList
public class LynxCommandTest {

    @Test
    public void addTodo() throws LynxException {
        String testString = new TodoTask("a").testRepresentation();
        assertEquals(testString, LynxCommand.addTodo("todo    a").testRepresentation());

        try {
            LynxCommand.addTodo("todo    ");
            fail();
        } catch (LynxException e1) {
        }
    }

    @Test
    public void addDeadline() throws LynxException {
        String testString = new DeadlineTask("a",
                LocalDateTime.of(2025, 11, 11, 0, 0)).testRepresentation();
        assertEquals(testString,
                LynxCommand.addDeadline("deadline    a /by    2025-11-11").testRepresentation());

        try {
            LynxCommand.addDeadline("deadline a/by2025-11-11");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommand.addDeadline("deadline /by 2025-11-11");
                fail();
            } catch (LynxException e2) {
            }
        }
    }

    @Test
    public void addEvent() throws LynxException {
        String testString = new EventTask("a",
                LocalDateTime.of(2025, 11, 11, 12, 0),
                LocalDateTime.of(2025, 11, 12, 6, 30)).testRepresentation();
        assertEquals(testString, LynxCommand.addEvent(
                "event a /from 2025-11-11-12 /to 2025-11-12-06-30").testRepresentation());

        try {
            LynxCommand.addEvent("event a /from 2025-11-11/to 2025-11-12");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommand.addEvent("event a /from 2025-11-11 /to 2025-11-10");
                fail();
            } catch (LynxException e2) {

            }
        }
    }

    @Test
    public void listTasks() {
        try {
            LynxCommand.listTasks("list");
            LynxCommand.listTasks("list ");
            LynxCommand.listTasks("list    2025-11-11");
            LynxCommand.listTasks("list    2025-11-11-06-30");
        } catch (LynxException e) {
            fail();
        }

        try {
            LynxCommand.listTasks("list2025-11-11");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommand.listTasks("list a");
                fail();
            } catch (LynxException e2) {
                try {
                    LynxCommand.listTasks("lista2025-11-11");
                    fail();
                } catch (LynxException e3) {

                }
            }
        }
    }

}
