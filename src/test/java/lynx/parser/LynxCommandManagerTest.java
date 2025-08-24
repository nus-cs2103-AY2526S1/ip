package lynx.parser;

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
public class LynxCommandManagerTest {

    @Test
    public void addTodo() throws LynxException {
        String testString = new TodoTask("a").testRepresentation();
        assertEquals(testString, LynxCommandManager.addTodo("todo    a").testRepresentation());

        try {
            LynxCommandManager.addTodo("todo    ");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommandManager.addTodo("todo /aaa");
                fail();
            } catch (LynxException e2) {

            }
        }
    }

    @Test
    public void addDeadline() throws LynxException {
        String testString = new DeadlineTask("a",
                LocalDateTime.of(2025, 11, 11, 0, 0)).testRepresentation();
        assertEquals(testString,
                LynxCommandManager.addDeadline("deadline    a /by    2025-11-11").testRepresentation());

        try {
            LynxCommandManager.addDeadline("deadline a/by2025-11-11");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommandManager.addDeadline("deadline /by 2025-11-11");
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
        assertEquals(testString, LynxCommandManager.addEvent(
                "event a /from 2025-11-11-12 /to 2025-11-12-06-30").testRepresentation());

        try {
            LynxCommandManager.addEvent("event a /from 2025-11-11/to 2025-11-12");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommandManager.addEvent("event a /from 2025-11-11 /to 2025-11-10");
                fail();
            } catch (LynxException e2) {

            }
        }
    }

    @Test
    public void listTasks() {
        try {
            LynxCommandManager.listTasks("list");
            LynxCommandManager.listTasks("list ");
            LynxCommandManager.listTasks("list aaa (bbb)");
            LynxCommandManager.listTasks("list    /on 2025-11-11");
            LynxCommandManager.listTasks("list /on    2025-11-11-06-30");
            LynxCommandManager.listTasks("list    /id    -999");
            LynxCommandManager.listTasks("list    /id    999");
        } catch (LynxException e) {
            fail();
        }

        try {
            LynxCommandManager.listTasks("listaaa");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommandManager.listTasks("list/on2025-11-11");
                fail();
            } catch (LynxException e2) {
                try {
                    LynxCommandManager.listTasks("list/id1");
                    fail();
                } catch (LynxException e3) {

                }
            }
        }
    }

}
