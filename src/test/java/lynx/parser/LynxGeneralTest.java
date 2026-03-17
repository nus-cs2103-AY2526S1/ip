package lynx.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lynx.storage.LynxTaskList;
import objectclasses.exception.LynxException;

public class LynxGeneralTest {

    private final LynxTaskList taskList = new LynxTaskList();

    @Test
    public void testAddTodo() throws LynxException {
        try {
            LynxGeneral.addTodo("todo    ", taskList);
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addTodo("todo /aaa", taskList);
                fail();
            } catch (LynxException e2) {
                return;
            }
        }
    }

    @Test
    public void testAddDeadline() throws LynxException {
        try {
            LynxGeneral.addDeadline("deadline a/by2025-11-11", taskList);
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addDeadline("deadline /by 2025-11-11", taskList);
                fail();
            } catch (LynxException e2) {
                return;
            }
        }
    }

    @Test
    public void testAddEvent() throws LynxException {
        try {
            LynxGeneral.addEvent("event a /from 2025-11-11/to 2025-11-12", taskList);
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addEvent("event a /from 2025-11-11 /to 2025-11-10", taskList);
                fail();
            } catch (LynxException e2) {
                return;
            }
        }
    }

}
