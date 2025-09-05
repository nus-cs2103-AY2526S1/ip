package lynx.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lynx.storage.LynxTaskList;
import objectclasses.exception.LynxException;

// The main purpose of this test is to check that the parsing works as intended
// Since all 4 action commands follow the same structure, testing one is enough
public class LynxTaskEditorTest {

    private final LynxTaskEditor taskEditor = new LynxTaskEditor(new LynxTaskList());

    @Test
    public void testListTasks() {
        try {
            taskEditor.listTasks("list    /all /key a    /key b /all /key c");
            taskEditor.listTasks("list /key aaa(BBB)");
            taskEditor.listTasks("list    /on 2025-11-11 /id    3");
            taskEditor.listTasks("list /on    2025-11-11-06-30 /status expired");
            taskEditor.listTasks("list /status complete /status INCOMPLETE /status exPIRed");
            taskEditor.listTasks("list /type toDO /type deadline /type EVENT");
        } catch (LynxException e) {
            fail();
        }

        try {
            taskEditor.listTasks("list /on 2025-11-11 /on 2025-11-12");
            fail();
        } catch (LynxException e1) {
            try {
                taskEditor.listTasks("list /id 1 /id 2");
                fail();
            } catch (LynxException e2) {
                try {
                    taskEditor.listTasks("list /on2025-11-11");
                    fail();
                } catch (LynxException e3) {
                    try {
                        taskEditor.listTasks("list    ");
                        fail();
                    } catch (LynxException e4) {
                        try {
                            taskEditor.listTasks("list /status ex");
                            fail();
                        } catch (LynxException e5) {
                            try {
                                taskEditor.listTasks("list /key a b");
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
