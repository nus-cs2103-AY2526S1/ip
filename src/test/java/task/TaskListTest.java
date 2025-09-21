package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void loadTask_validInput_success() {
        TaskList tl = new TaskList();
        try {
            tl.loadTask("todo|1|homework");
        } catch (Exception ignored) {

        }
        assertEquals("Here are the tasks in your list:\n" +
                "    1.[T][X] homework\n",tl.toString());
    }

    @Test
    public void loadTask_invalidInput_errorPrinted() {
        TaskList tl = new TaskList();
        try {
            tl.loadTask("tod homework");
            fail();
        } catch (Exception e) {
            assertEquals("couldn't load task: tod homework", e.getMessage());
        }
    }
}
