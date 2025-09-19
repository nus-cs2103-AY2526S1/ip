package bot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    public void createTaskFromFileString_todoString_success() {
        Task expectedTask = new Todo("buy food", true);
        String input = "T | 1 | buy food";
        assertEquals(expectedTask.toString(), Task.createTaskFromFileString(input).toString());
    }

    @Test
    public void createTaskFromFileString_deadlineString_success() {
        Task expectedTask = new Deadline("homework", "23-09-2025 2359", false);
        String input = "D | 0 | homework | 23-09-2025 2359";
        assertEquals(expectedTask.toString(), Task.createTaskFromFileString(input).toString());
    }

    @Test
    public void createTaskFromFileString_eventString_success() {
        Task expectedTask = new Event(
                "Alibaba Earnings Call",
                "30-08-2025 0000",
                "30-08-2025 2359",
                false);

        String input = "E | 0 | Alibaba Earnings Call | 30-08-2025 0000 | 30-08-2025 2359";
        assertEquals(expectedTask.toString(), Task.createTaskFromFileString(input).toString());
    }

    @Test
    public void createTaskFromFileString_invalidStringFormat_failure() {
        Task expectedTask = new Todo("buy food", true);
        String input = "I | 1 | buy food";

        try {
            assertEquals(expectedTask.toString(), Task.createTaskFromFileString(input).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid task format in file: " + input, e.getMessage());
        }
    }

    @Test
    public void createTaskFromFileString_invalidDateDeadline_failure() {
        Task expectedTask = new Deadline("homework", "23-09-2025 2359", false);
        String input = "D | 0 | homework | 23.09.2025 2359";

        try {
            assertEquals(expectedTask.toString(), Task.createTaskFromFileString(input).toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("""
                        Oh no! I don't support this date format, but you can choose one of these format:\
                        
                        dd-mm-yyyy\
                        
                        dd-mm-yyyy HHmm\
                        
                        dd/mm/yyyy\
                        
                        dd/mm/yyyy HHmm""", e.getMessage());
        }
    }
}
