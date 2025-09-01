package lynx.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import objectclasses.exception.LynxException;

// The main purpose of this test is to check that the parsing works as intended
// Since all 4 action commands follow the same structure, testing one is enough
public class LynxTaskEditorTest {

    @Test
    public void testListTasks() {
        try {
            LynxTaskEditor.listTasks("list    /all /key a    /key b /all /key c");
            LynxTaskEditor.listTasks("list /key aaa(BBB)");
            LynxTaskEditor.listTasks("list    /on 2025-11-11 /id    3");
            LynxTaskEditor.listTasks("list /on    2025-11-11-06-30 /status expired");
            LynxTaskEditor.listTasks("list /status complete /status INCOMPLETE /status exPIRed");
            LynxTaskEditor.listTasks("list /type toDO /type deadline /type EVENT");
        } catch (LynxException e) {
            fail();
        }

        try {
            LynxTaskEditor.listTasks("list /on 2025-11-11 /on 2025-11-12");
            fail();
        } catch (LynxException e1) {
            try {
                LynxTaskEditor.listTasks("list /id 1 /id 2");
                fail();
            } catch (LynxException e2) {
                try {
                    LynxTaskEditor.listTasks("list /on2025-11-11");
                    fail();
                } catch (LynxException e3) {
                    try {
                        LynxTaskEditor.listTasks("list    ");
                        fail();
                    } catch (LynxException e4) {
                        try {
                            LynxTaskEditor.listTasks("list /status ex");
                            fail();
                        } catch (LynxException e5) {
                            try {
                                LynxTaskEditor.listTasks("list /key a b");
                                fail();
                            } catch (LynxException e6) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }


}
