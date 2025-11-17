package listmanager;

import customexceptions.IncompleteTaskException;
import customexceptions.NoSuchTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListManagerTest {
    @Test
    public void taskClassifier_todoTaskDescriptor_todoTask()
            throws IncompleteTaskException, NoSuchTaskException {
        ListManager listManager = new ListManager();
        assertEquals(new Todo("todo Todo Task").getTaskWithStatus(),
                listManager.taskClassifier("todo Todo Task").getTaskWithStatus());
    }

    @Test
    public void taskClassifier_incompleteTodoTaskDescriptor_IncompleteTaskException()
            throws IncompleteTaskException, NoSuchTaskException {
        ListManager listManager = new ListManager();
        try {
            listManager.taskClassifier("todo");
            fail();
        } catch (Exception e) {
            assertEquals("I do not recognize your input.",
                    e.getMessage());
        }
    }

    @Test
    public void taskClassifier_deadlineTaskDescriptor_deadlineTask()
            throws IncompleteTaskException, NoSuchTaskException{
        ListManager listManager = new ListManager();
        assertEquals(new Deadline("deadline Deadline Task /by 2022-01-01").getTaskWithStatus(),
                        listManager.taskClassifier("deadline Deadline Task /by 2022-01-01").getTaskWithStatus());
    }

    @Test
    public void taskClassifier_incompleteDeadlineTaskDescriptor_IncompleteTaskException()
            throws IncompleteTaskException, NoSuchTaskException {
        ListManager listManager = new ListManager();
        try {
            listManager.taskClassifier("deadline Test1");
            fail();
        } catch (Exception e) {
            assertEquals("Please add a deadline.\n" +
                            " Example: deadline go home /by 2pm",
                    e.getMessage());
        }
    }

    @Test
    public void taskClassifier_eventTaskDescriptor_eventTask()
            throws IncompleteTaskException, NoSuchTaskException{
        ListManager listManager = new ListManager();
        assertEquals(new Event("event Event Task /from 2022-01-01 /to 2022-01-01").getTaskWithStatus(),
                listManager.taskClassifier("event Event Task /from 2022-01-01 /to 2022-01-01").getTaskWithStatus());
    }

    @Test
    public void taskClassifier_incompleteEventTaskDescriptor_IncompleteTaskException()
            throws IncompleteTaskException, NoSuchTaskException {
        ListManager listManager = new ListManager();
        try {
            listManager.taskClassifier("event event1");
            fail();
        } catch (Exception e) {
            assertEquals("please include start and end time using /from and /to for events",
                    e.getMessage());
        }
    }

    @Test
    public void taskClassifier_randomString_NoSuchTaskException() {
        ListManager listManager = new ListManager();
        try {
            listManager.taskClassifier("randomString");
            fail();
        } catch (Exception e) {
            assertEquals("I do not recognize your input.",
                    e.getMessage());
        }
    }
}
