package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.storage.Storage;
import stewie.task.EventTask;
import stewie.task.TaskList;

/**
 * Tests for EventCommand.
 */
public class EventCommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = org.mockito.Mockito.mock(TaskList.class);
        storage = org.mockito.Mockito.mock(Storage.class);
    }

    @Test
    public void testExecute_success() throws CommandException {
        EventTask dummyEventTask = new EventTask(
                "Project meeting",
                LocalDateTime.of(2025, 11, 1, 10, 0),
                LocalDateTime.of(2025, 11, 1, 12, 0)
        );

        String expectedResponse =
                        "\t I've scribbled down your little task:\n"
                        + "\t  " + dummyEventTask.getDescription() + "\n"
                        + "\t Now, do try to keep up, won't you?\n"
                        + "\t You have " + 1 + " tasks left.\n";

        when(taskList.addTask(any(EventTask.class))).thenReturn(expectedResponse);

        String args = "Project meeting /from 01/11/2025 10:00 /to 01/11/2025 12:00";
        EventCommand eventCommand = new EventCommand(args);

        String actualResponse = eventCommand.execute(taskList, storage);

        assertEquals(expectedResponse, actualResponse);

        ArgumentCaptor<EventTask> taskCaptor = ArgumentCaptor.forClass(EventTask.class);
        verify(taskList).addTask(taskCaptor.capture());
        EventTask addedTask = taskCaptor.getValue();

        assertEquals(
                "[E][ ] Project meeting (from: 01 Nov 2025 10:00 to: 01 Nov 2025 12:00)",
                addedTask.getDescription()
        );

        verify(storage).saveTasks(taskList);
    }

    @Test
    public void testExecute_invalidFormat_notEnoughParts() {
        String args = "Project meeting /from 01/11/2025 10:00";
        EventCommand eventCommand = new EventCommand(args);
        assertThrows(InvalidCommandException.class, () -> eventCommand.execute(taskList, storage));
    }

    @Test
    public void testExecute_invalidFormat_emptyDescription() {
        String args = "  /from 01/11/2025 10:00 /to 01/11/2025 12:00";
        EventCommand eventCommand = new EventCommand(args);
        assertThrows(InvalidCommandException.class, () -> eventCommand.execute(taskList, storage));
    }

    @Test
    public void testExecute_invalidFormat_emptyStartTime() {
        String args = "Project meeting /from /to 01/11/2025 12:00";
        EventCommand eventCommand = new EventCommand(args);
        assertThrows(InvalidCommandException.class, () -> eventCommand.execute(taskList, storage));
    }

    @Test
    public void testExecute_invalidFormat_emptyEndTime() {
        String args = "Project meeting /from 01/11/2025 10:00 /to ";
        EventCommand eventCommand = new EventCommand(args);
        assertThrows(InvalidCommandException.class, () -> eventCommand.execute(taskList, storage));
    }

    @Test
    public void testExecute_invalidDateTimeFormat() {
        String args = "Project meeting /from 01/13/2025 /to 01/11/2025 12:00";
        EventCommand eventCommand = new EventCommand(args);
        assertThrows(InvalidCommandException.class, () -> eventCommand.execute(taskList, storage));
    }
}
