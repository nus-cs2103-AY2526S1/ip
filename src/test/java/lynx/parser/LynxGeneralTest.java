package lynx.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import objectclasses.exception.LynxException;

public class LynxGeneralTest {

    @Test
    public void testAddTodo() throws LynxException {
        try {
            LynxGeneral.addTodo("todo    ");
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addTodo("todo /aaa");
                fail();
            } catch (LynxException e2) {
                return;
            }
        }
    }

    @Test
    public void testAddDeadline() throws LynxException {
        try {
            LynxGeneral.addDeadline("deadline a/by2025-11-11");
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addDeadline("deadline /by 2025-11-11");
                fail();
            } catch (LynxException e2) {
                return;
            }
        }
    }

    @Test
    public void testAddEvent() throws LynxException {
        try {
            LynxGeneral.addEvent("event a /from 2025-11-11/to 2025-11-12");
            fail();
        } catch (LynxException e1) {
            try {
                LynxGeneral.addEvent("event a /from 2025-11-11 /to 2025-11-10");
                fail();
            } catch (LynxException e2) {
                return;
            }
        }
    }

}
