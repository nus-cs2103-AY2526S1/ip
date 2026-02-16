package horus.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineTaskTest {

    @Test
    void testInvalidDeadlineFormat() {
        // Missing /by should throw an exception
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            new DeadlineTask("Homework without deadline");
        });
    }

    @Test
    void testGetTaskData() throws InvalidInputException {
        DeadlineTask deadlineTask = new DeadlineTask("Submit homework /by 20/9/2025");
        String taskData = deadlineTask.getTaskData();

        Task newDeadlineTask = Task.readTaskData(taskData);
        assertEquals(deadlineTask, newDeadlineTask);
    }

    @Test
    void testEquals() {
        DeadlineTask deadlineTask1 = new DeadlineTask("Finish report /by 20/9/2025");
        DeadlineTask deadlineTask2 = new DeadlineTask("Finish report /by 20/9/2025");
        DeadlineTask deadlineTask3 = new DeadlineTask("Finish report /by 21/9/2025");
        DeadlineTask deadlineTask4 = new DeadlineTask("Finish homework /by 20/9/2025");

        Task task = new Task("Finish report");

        // Handles null and different class objects
        assertEquals(deadlineTask1.equals(null), false);
        assertEquals(deadlineTask1.equals(task), false);


        // Tasks with the same description and same deadline are equal
        assertEquals(deadlineTask1.equals(deadlineTask2), true);

        // Different deadlines are not equal
        assertEquals(deadlineTask1.equals(deadlineTask3), false);

        // Tasks with different description are not equal
        assertEquals(deadlineTask1.equals(deadlineTask4), false);
    }

    @Test
    void testToString() {
        String taskStr = "Submit assignment /by 20/9/2025";
        DeadlineTask deadlineTask = new DeadlineTask(taskStr);

        Task baseTask = new Task("Submit assignment");
        CustomDateTime byDate = new CustomDateTime("20/9/2025");

        assertEquals("[D]" + baseTask.toString() + " (by: " + byDate + ")", deadlineTask.toString());
    }


}