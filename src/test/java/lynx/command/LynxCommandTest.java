package lynx.command;

import lynx.exception.LynxException;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
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
    public void addTodo_correctArgument_success() throws LynxException {
        String testString = new TodoTask("a").testRepresentation();
        assertEquals(testString, LynxCommand.addTodo("todo    a").testRepresentation());
    }

    @Test
    public void addTodo_incorrectArgument_exception() {
        try {
            LynxCommand.addTodo("todo    ");
            fail();
        } catch (LynxException e1) {
        }
    }

    @Test
    public void addDeadline_correctArgument_success() throws LynxException {
        String testString = new DeadlineTask("a",
                LocalDateTime.of(2025, 11, 11, 0, 0)).testRepresentation();
        assertEquals(testString,
                LynxCommand.addDeadline("deadline    a /by    2025-11-11").testRepresentation());
    }

    @Test
    public void addDeadline_incorrectArgument_exception() {
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
    public void addEvent_correctArgument_success() throws LynxException {
        String testString = new EventTask("a",
                LocalDateTime.of(2025, 11, 11, 12, 0),
                LocalDateTime.of(2025, 11, 12, 6, 30)).testRepresentation();
        assertEquals(testString, LynxCommand.addEvent(
                "event a /from 2025-11-11-12 /to 2025-11-12-06-30").testRepresentation());
    }

    @Test
    public void addEvent_incorrectArgument_exception() {
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
    public void listTasks_correctArgument_success() {
        try {
            LynxCommand.listTasks("list");
            LynxCommand.listTasks("list ");
            LynxCommand.listTasks("list    2025-11-11");
            LynxCommand.listTasks("list    2025-11-11-06-30");
        } catch (LynxException e) {
            fail();
        }
    }

    @Test
    public void listTasks_incorrectArgument_exception() {
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
