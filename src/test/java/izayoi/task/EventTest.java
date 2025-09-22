package izayoi.task;

import izayoi.input.TaskDescriptor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void commandify() {
        TaskDescriptor td = new TaskDescriptor(){
            @Override
            public Map<String, String> getTask() {
                return Map.of("message", "test event", "from", "2019-10-01", "to", "2019-10-02");
            }
        };

        try {
            Event e = new Event(td);
            List<String> s = e.commandify();
            assertEquals(1, s.size(), "correct number of string arguments");
            assertEquals("event test event /from 2019-10-01 /to 2019-10-02", s.get(0),
                    "correctly converts event to string");
        } catch (Exception e) {
            fail("failed to read task descriptor: " + e.getMessage());
        }
    }
}