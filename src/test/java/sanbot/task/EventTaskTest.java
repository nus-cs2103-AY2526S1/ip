package sanbot.task;

import org.junit.jupiter.api.Test;
import sanbot.command.AddEventTaskCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class EventTaskTest {

    @Test
    public void toSaveString_success() {
        ArrayList<Task> taskList = new ArrayList<>();
        new AddEventTaskCommand().execute("event lunch with nazeef /from 05-09-2025 12:00 /to 05-09-2025 14:00", taskList);
        assertEquals("E | 0 | lunch with nazeef | 05-09-2025 12:00 | 05-09-2025 14:00", taskList.get(0).toSaveString());
    }

}
