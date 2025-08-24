package lynx.parser;

import lynx.exception.LynxException;
import lynx.storage.LynxTaskList;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.TodoTask;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

// Primarily tests the parsing responsible by LynxCommand
// mark, unmark and delete are not tested as they follow the same structure as list
public class LynxCommandManagerTest {

    @Test
    public void testAddTodo() throws LynxException {
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
    public void testAddDeadline() throws LynxException {
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
    public void testAddEvent() throws LynxException {
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
    public void testListTasks() {
        TodoTask testTask = new TodoTask("a");
        int testId = testTask.getId();
        LynxTaskList.addTask(testTask, true);
        try {
            LynxCommandManager.listTasks("list    /all");
            LynxCommandManager.listTasks("list aaa (bbb)");
            LynxCommandManager.listTasks("list    /on 2025-11-11");
            LynxCommandManager.listTasks("list /on    2025-11-11-06-30");
            LynxCommandManager.listTasks("list    /id    " + testId);
        } catch (LynxException e) {
            fail();
        }

        try {
            LynxCommandManager.listTasks("list /id 0");
            fail();
        } catch (LynxException e1) {
            try {
                LynxCommandManager.listTasks("list /id" + testId);
                fail();
            } catch (LynxException e2) {
                try {
                    LynxCommandManager.listTasks("list /on2025-11-11");
                    fail();
                } catch (LynxException e3) {
                    try {
                        LynxCommandManager.listTasks("list    ");
                        fail();
                    } catch (LynxException e4) {

                    }
                }
            }
        }
    }

}
