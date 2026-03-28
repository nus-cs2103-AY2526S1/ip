package chlo.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import chlo.ui.Parser;
import chlo.exception.ChloException;

public class EventTest {

    @Test
    public void toString_validInputs_returnsExpectedString() {
        String description = "Team lunch";
        String from = "25/8/2025 1300";
        String to = "25/8/2025 1400";
        try {
            Event event = new Event(description, from, to);
            String formattedFrom = Parser.getFormattedDate(event.from);
            String formattedTo = Parser.getFormattedDate(event.to);
            String expected = "[E][" + event.getStatusIcon() + "] " + description + " (from: " + formattedFrom + " to: " + formattedTo + ")";
            assertEquals(expected, event.toString());
        } catch (ChloException e) {
            fail("toString test threw an unexpected exception");
        }
    }
}
