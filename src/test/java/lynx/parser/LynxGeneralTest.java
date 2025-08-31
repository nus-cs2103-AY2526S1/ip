package lynx.parser;

import objectclasses.exception.LynxException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.TodoTask;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class LynxGeneralTest {

    @Test
    public void testAddTodo() throws LynxException {
        String testString = new TodoTask("a").testRepresentation();
        assertEquals(testString, LynxGeneral.addTodo("todo    a").testRepresentation());

        try {
            LynxGeneral.addTodo("todo    ");
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addTodo("todo /aaa");
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
                LynxGeneral.addDeadline("deadline    a /by    2025-11-11").testRepresentation());

        try {
            LynxGeneral.addDeadline("deadline a/by2025-11-11");
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addDeadline("deadline /by 2025-11-11");
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
        assertEquals(testString, LynxGeneral.addEvent(
                "event a /from 2025-11-11-12 /to 2025-11-12-06-30").testRepresentation());

        try {
            LynxGeneral.addEvent("event a /from 2025-11-11/to 2025-11-12");
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addEvent("event a /from 2025-11-11 /to 2025-11-10");
                fail();
            } catch (LynxException e2) {

            }
        }
    }

}
