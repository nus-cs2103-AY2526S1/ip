package joe.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void testToStringReflectsIsDoneCorrectly() {
        // Task created as done
        Task doneTask = new Task("Write report", true);
        assertEquals("[1] Write report", doneTask.toString(), "toString should reflect done status");

        // Task created as not done
        Task notDoneTask = new Task("Go jogging", false);
        assertEquals("[0] Go jogging", notDoneTask.toString(), "toString should reflect not done status");
    }
}
