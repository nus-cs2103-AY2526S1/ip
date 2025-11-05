package uxie.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import uxie.exceptions.UxieIOException;
import uxie.tasks.Deadline;
import uxie.tasks.Event;
import uxie.tasks.Task;
import uxie.tasks.ToDo;

public class StorageTest {

    @Test
    public void readTasks_validInput_populatedTaskList() {
        String validInputPath = "./src/test/resources/readTasks_validInput.csv";
        Storage storage = new Storage(validInputPath);
        List<Task> expectedOutput = new ArrayList<>();
        expectedOutput.add(new ToDo(true, "eat breakfast"));
        expectedOutput.add(new Deadline("kill god",
                LocalDateTime.of(2004, 12, 3, 11, 0)));
        expectedOutput.add(new Event(true, "drive",
                LocalDateTime.of(2004, 12, 4, 18, 0),
                LocalDateTime.of(2004, 12, 4, 21, 0)));
        try {
            assertEquals(expectedOutput, storage.readTasks());
        } catch (UxieIOException e) {
            Assertions.fail();
        }
    }

    @Test
    public void readTasks_missingField_throwsException() {
        String missingFieldPath = "./src/test/resources/readTasks_missingField.csv";
        Storage storage = new Storage(missingFieldPath);
        List<Task> expectedOutput = new ArrayList<>();
        expectedOutput.add(new ToDo(true, "eat breakfast"));
        expectedOutput.add(new Event(true, "drive",
                LocalDateTime.of(2004, 12, 4, 18, 0),
                LocalDateTime.of(2004, 12, 4, 21, 0)));
        try {
            assertEquals(expectedOutput, storage.readTasks());
        } catch (UxieIOException e) {
            Assertions.fail();
        }
    }

}
