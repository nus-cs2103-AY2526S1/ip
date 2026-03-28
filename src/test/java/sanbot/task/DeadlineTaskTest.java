package sanbot.task;

import org.junit.jupiter.api.Test;
import sanbot.command.AddDeadlineTaskCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class DeadlineTaskTest {

    @Test
    public void toSaveString_success() {
        ArrayList<Task> taskList = new ArrayList<>();
        new AddDeadlineTaskCommand().execute("deadline 3231 tutorial /by 10-09-2025 23:59", taskList);
        assertEquals("D | 0 | 3231 tutorial | 10-09-2025 23:59", taskList.get(0).toSaveString());
    }

}
