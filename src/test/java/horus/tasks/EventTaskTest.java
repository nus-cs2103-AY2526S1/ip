package horus.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventTaskTest {
    @Test
    void testInvalidEventFormat() {
        // Missing /from should throw an exception
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            new EventTask("Party /to 20:00");
        });

        // Missing /to should throw an exception
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            new EventTask("Party /from 1800");
        });
    }

    @Test
    void testGetTaskData() throws InvalidInputException {
        EventTask eventTask = new EventTask("Team meeting /from 1000 /to 1200");
        String taskData = eventTask.getTaskData();

        Task newEventTask = Task.readTaskData(taskData);
        assertEquals(eventTask, newEventTask);
    }

    @Test
    void testEquals() {
        EventTask eventTask1 = new EventTask("Conference /from 0900 /to 1700");
        EventTask eventTask2 = new EventTask("Conference /from 0900 /to 1700");
        EventTask eventTask3 = new EventTask("Conference /from 1000 /to 1800");
        EventTask eventTask4 = new EventTask("Workshop /from 0900 /to 1700");

        Task baseTask = new Task("Conference");

        // Handles null and different class objects
        assertEquals(false, eventTask1.equals(null));
        assertEquals(false, eventTask1.equals(baseTask));

        // Tasks with the same description and same times are equal
        assertEquals(true, eventTask1.equals(eventTask2));

        // Same description but different times are not equal
        assertEquals(false, eventTask1.equals(eventTask3));

        // Different description but same times are not equal
        assertEquals(false, eventTask1.equals(eventTask4));
    }

    @Test
    void testToString() {
        String taskStr = "Workshop /from 1400 /to 1800";
        EventTask eventTask = new EventTask(taskStr);

        Task baseTask = new Task("Workshop");
        CustomDateTime from = new CustomDateTime("1400");
        CustomDateTime to = new CustomDateTime("1800");

        assertEquals("[E]" + baseTask.toString() + " (from: " + from + " to: " + to + ")", eventTask.toString());
    }
}