package steven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import steven.exception.StevenException;
import steven.storage.Storage;
import steven.task.Task;
import steven.task.TaskList;


public class TaskListTest {
    private final Storage storage = new Storage("");

    @Test
    public void addToDoTask_emptyDescription_emptyDescriptionExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addToDoTask("todo ", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tWhr is your description????", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_emptyDescription_emptyDescriptionExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addDeadlineTask("deadline  ", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tWhr is your description????", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_invalidFormat_invalidFormatExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addDeadlineTask("deadline task ", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tPls use the correct format for deadline task", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_emptyDeadline_missingDeadlineExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addDeadlineTask("deadline task /by ", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tWhr is your deadline???", e.getMessage());
        }
    }

    @Test
    public void addDeadlineTask_validInput_taskAddedSuccessfully() throws StevenException {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        taskList.addDeadlineTask("deadline return book /by 20-09-2023 1234", storage);
        assertEquals(1, list.size());
        assertEquals("[D][ ] return book (by: 20 Sept 2023 12:34)",
                list.get(0).toString());
    }

    @Test
    public void addEventTask_emptyDescription_emptyDescriptionExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addEventTask("event  ", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tWhr is your description????", e.getMessage());
        }
    }

    @Test
    public void addEventTask_invalidFormat_invalidFormatExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addEventTask("event party", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tPls use the correct format for event task", e.getMessage());
        }
    }

    @Test
    public void addEventTask_missingFromOrTo_invalidFormatExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addEventTask("event party /from 5pm", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tPls use the correct format for event task", e.getMessage());
        }
    }

    @Test
    public void addEventTask_emptyStartAndEndTime_missingStartAndEndTimeExceptionThrown() {
        try {
            new TaskList(new ArrayList<>()).addEventTask("event party /from  /to  ", storage);
            fail();
        } catch (StevenException e) {
            assertEquals("\tYour event from when to when????", e.getMessage());
        }
    }

    @Test
    public void addEventTask_validInput_taskAddedSuccessfully() throws StevenException {
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        taskList.addEventTask("event some event /from 16-09-2025 0000 /to 17-09-2025 0000", storage);
        assertEquals(1, list.size());
        assertEquals("[E][ ] some event (from: 16 Sept 2025 00:00 to: 17 Sept 2025 00:00)",
                list.get(0).toString());
    }
}
