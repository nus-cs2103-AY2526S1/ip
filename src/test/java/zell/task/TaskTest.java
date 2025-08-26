package zell.task;

import zell.exception.ZellException;
import zell.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void stringToTask_validTodo_success() throws ZellException {
        Task task = Task.stringToTask("T | false | some stuff");
        assertEquals("[T][ ] some stuff", task.toString());

        Task task2 = Task.stringToTask("T | true | some stuff");
        assertEquals("[T][X] some stuff", task2.toString());
    }

    @Test
    public void stringToTask_invalidTodo_exceptionThrown() throws ZellException {
        try {
            Task task = Task.stringToTask("T | false");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("T");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }
    }

    @Test
    public void stringToTask_validDeadline_success() throws ZellException {
        Task task = Task.stringToTask("D | false | read book | 2025-08-31");
        assertEquals("[D][ ] read book (by: Aug 31 2025)", task.toString());

        Task task2 = Task.stringToTask("D | true | read book | 2025-08-31");
        assertEquals("[D][X] read book (by: Aug 31 2025)", task2.toString());
    }

    @Test
    public void stringToTask_invalidDeadline_exceptionThrown() throws ZellException {
        try {
            Task task = Task.stringToTask("D | false | read book | 08-2025-31");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                    + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("D | false | read book | 08-31-2025");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                            + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("D | false | read book | aaaa");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                            + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("D | false | read book | ");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("D | false | read book");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("D | false");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("D");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }
    }

    @Test
    public void stringToTask_validEvent_success() throws ZellException {
        Task task = Task.stringToTask("E | false | go to bookstore | 2025-08-27 14:00 | 2025-08-29 15:00");
        assertEquals("[E][ ] go to bookstore (from: Aug 27 2025 02:00 pm to: Aug 29 2025 03:00 pm)", task.toString());

        Task task2 = Task.stringToTask("E | true | go to bookstore | 2025-08-27 14:00 | 2025-08-29 15:00");
        assertEquals("[E][X] go to bookstore (from: Aug 27 2025 02:00 pm to: Aug 29 2025 03:00 pm)", task2.toString());
    }

    @Test
    public void stringToTask_invalidEvent_exceptionThrown() throws ZellException {
        try {
            Task task = Task.stringToTask("E | false | go to bookstore | 08-2025-27 14:00 | 2025-08-29 15:00");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                            + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E | false | go to bookstore | 08-27-2025 14:00 | 2025-08-29 15:00");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                            + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E | false | go to bookstore | 08-27-2025 14:0 | 2025-08-29 15:00");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                            + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E | false | go to bookstore | 08-27-2025 30:14 | 2025-08-29 15:00");
            fail();
        } catch (ZellException ze) {
            assertEquals("Date or DateTime should be in the respective formats "
                            + "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E | false | go to bookstore | 2025-03-13 17:14");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E | false | go to bookstore");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E | false");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("E");
            fail();
        } catch (ZellException ze) {
            assertEquals("Invalid task string provided! Certain parameters are missing.",
                    ze.getMessage());
        }
    }

    @Test
    public void stringToTask_invalidTaskType_exceptionThrown() throws ZellException {
        try {
            Task task = Task.stringToTask("A | false | go to bookstore | 08-2025-27 14:00 | 2025-08-29 15:00");
            fail();
        } catch (ZellException ze) {
            assertEquals("Unknown task type encountered when converting tasks",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("B | false | go to bookstore | 08-2025-27 14:00");
            fail();
        } catch (ZellException ze) {
            assertEquals("Unknown task type encountered when converting tasks",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("C | false | go to bookstore");
            fail();
        } catch (ZellException ze) {
            assertEquals("Unknown task type encountered when converting tasks",
                    ze.getMessage());
        }

        try {
            Task task = Task.stringToTask("");
            fail();
        } catch (ZellException ze) {
            assertEquals("Unknown task type encountered when converting tasks",
                    ze.getMessage());
        }
    }
}
