package tasks;

import gokschat.tasks.EventTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/// This class contains tests for the EventTask
///
/// @author Ravichandran Gokul
public class EventTaskTest {

    // Test for undone task

    @Test
    void toFileString_unmarkedTask_returnsCorrectFormat() {
        EventTask task = new EventTask("Meet to discuss tP", "Sunday 9pm", "Sunday 10pm");
        String expectedString = "E | 0 | Meet to discuss tP | from Sunday 9pm | to Sunday 10pm";
        String actualString = task.toFileString();
        assertEquals(expectedString, actualString);
    }

    // Test for done task

    @Test
    void toFileString_markedTask_returnsCorrectFormat() {
        EventTask task = new EventTask("Meet to discuss tP", "Sunday 9pm", "Sunday 10pm");
        task.markAsDone();
        String expectedString = "E | 1 | Meet to discuss tP | from Sunday 9pm | to Sunday 10pm";
        String actualString = task.toFileString();
        assertEquals(expectedString, actualString);
    }
}
