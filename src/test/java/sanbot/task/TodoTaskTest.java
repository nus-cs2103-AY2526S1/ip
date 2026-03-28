package sanbot.task;

import org.junit.jupiter.api.Test;
import sanbot.command.AddTodoTaskCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class TodoTaskTest {

    @Test
    public void toSaveString_success() {
        ArrayList<Task> taskList = new ArrayList<>();
        new AddTodoTaskCommand().execute("todo 2100 quiz", taskList);
        assertEquals("T | 0 | 2100 quiz", taskList.get(0).toSaveString());
    }

}
