
package rafayel.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;

public class EventCommandTest {

    private TaskList tasks;
    private Storage storage;
    private EventCommand eventCommand;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        storage = new Storage("test.txt");
    }

    @AfterEach
    public void tearDown() {
        // Clean up any test files if needed
    }

    @Test
    public void validEventInput_success() throws RafayelException {
        String descriptionDate = "Project meeting /from Feb 29 2024 14:00 /to Feb 29 2024 16:00";
        eventCommand = new EventCommand(descriptionDate);

        assertDoesNotThrow(() -> eventCommand.execute(tasks, storage));
    }

    @Test
    public void emptyInput_throwsException() {
        String descriptionDate = "";
        eventCommand = new EventCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                eventCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains("blank canvas"));
        assertTrue(exception.getMessage().contains(EventCommand.EVENT_FORMAT_ERROR));
    }

    @Test
    public void missingFromKeyword_throwsException() {
        String descriptionDate = "Project meeting Feb 29 2024 14:00 /to Feb 29 2024 16:00";
        eventCommand = new EventCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                eventCommand.execute(tasks, storage));
        assertEquals(EventCommand.EVENT_FORMAT_ERROR,
                exception.getMessage());
    }

    @Test
    public void missingToKeyword_throwsException() {
        String descriptionDate = "Project meeting /from Feb 29 2024 14:00 Feb 29 2024 16:00";
        eventCommand = new EventCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                eventCommand.execute(tasks, storage));
        assertEquals(EventCommand.EVENT_FORMAT_ERROR,
                exception.getMessage());
    }

    @Test
    public void toBeforeFrom_throwsException() {
        String descriptionDate = "Project meeting /from Feb 29 2024 16:00 /to Feb 29 2024 14:00";
        eventCommand = new EventCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                eventCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains("Invalid time period"));
        assertTrue(exception.getMessage().contains("should be after"));
    }

    @Test
    public void sameFromAndTo_throwsException() {
        String descriptionDate = "Project meeting /from Feb 29 2024 14:00 /to Feb 29 2024 14:00";
        eventCommand = new EventCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                eventCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains("same date"));
    }

    @Test
    public void execute_validEvent_addsToTaskList() throws RafayelException {
        String descriptionDate = "Team lunch /from Mar 15 2024 12:00 /to Mar 15 2024 13:30";
        eventCommand = new EventCommand(descriptionDate);

        String result = eventCommand.execute(tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(result.contains("Team lunch"));
        assertTrue(result.contains("added"));
    }

    @Test
    public void execute_multipleEvents_addsCorrectly() throws RafayelException {
        String descriptionDate1 = "Meeting /from Mar 15 2024 10:00 /to Mar 15 2024 11:00";
        String descriptionDate2 = "Lunch /from Mar 15 2024 12:00 /to Mar 15 2024 13:00";

        EventCommand event1 = new EventCommand(descriptionDate1);
        EventCommand event2 = new EventCommand(descriptionDate2);

        event1.execute(tasks, storage);
        event2.execute(tasks, storage);

        assertEquals(2, tasks.getSize());
    }

    @Test
    public void eventWithWhitespace_trimsCorrectly() throws RafayelException {
        String descriptionDate = "   Conference   /from   Apr 1 2024 09:00   /to   Apr 1 2024 17:00   ";
        eventCommand = new EventCommand(descriptionDate);

        String result = eventCommand.execute(tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(result.contains("Conference"));
    }

    @Test
    public void validEventWithSlashDateFormat_success() throws RafayelException {
        String descriptionDate = "Project meeting /from 2024/02/29 14:00 /to 2024/02/29 16:00";
        eventCommand = new EventCommand(descriptionDate);

        assertDoesNotThrow(() -> eventCommand.execute(tasks, storage));
    }

    @Test
    public void validEventWithDashDateFormat_success() throws RafayelException {
        String descriptionDate = "Project meeting /from 29-02-2024 14:00 /to 29-02-2024 16:00";
        eventCommand = new EventCommand(descriptionDate);

        assertDoesNotThrow(() -> eventCommand.execute(tasks, storage));
    }

    @Test
    public void differentDateFormats_success() throws RafayelException {
        String descriptionDate = "Mixed formats /from Feb 29 2024 14:00 /to 2024/02/29 16:00";
        eventCommand = new EventCommand(descriptionDate);

        assertDoesNotThrow(() -> eventCommand.execute(tasks, storage));
    }

    @Test
    public void eventWithDifferentDateFormats_addsToTaskList() throws RafayelException {
        String descriptionDate = "Team lunch /from 15-03-2024 12:00 /to 2024/03/15 13:30";
        eventCommand = new EventCommand(descriptionDate);

        String result = eventCommand.execute(tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(result.contains("Team lunch"));
        assertTrue(result.contains("added"));
    }
}
