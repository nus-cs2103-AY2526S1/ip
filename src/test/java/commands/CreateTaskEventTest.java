package commands;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import models.Task;

public class CreateTaskEventTest {
    @Test
    public void eventTest() {
        try {
            CreateTaskEvent test = new CreateTaskEvent();

            HashMap<String, String> params = new HashMap<>(Map.of(
                "to", "11-9-25 5:37", 
                "from", "1/1/2025 1000"
            ));

            Task event = test.createTask("eve", params);

            assertEquals(event.toString(), "[E][ ] eve (from: Jan 1 2025 to: Sep 11 2025)");
        } catch (Exception e) {}
    }
}