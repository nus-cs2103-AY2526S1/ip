
package rafayel.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rafayel.Rafayel;
import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;

public class DeadlineCommandTest {

    private TaskList tasks;
    private Storage storage;
    private DeadlineCommand deadlineCommand;

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
    public void validDeadlineInput_success() throws RafayelException {
        String descriptionDate = "Submit report /by Feb 29 2024 14:00";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        // Should not throw any exception
        assertDoesNotThrow(() -> deadlineCommand.execute(tasks, storage));
    }

    @Test
    public void emptyInput_throwsException() {
        String descriptionDate = "";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                deadlineCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains("blank canvas"));
    }

    @Test
    public void missingByKeyword_throwsException() {
        String descriptionDate = "Submit report Feb 29 2024 14:00";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                deadlineCommand.execute(tasks, storage));
        assertEquals(DeadlineCommand.DEADLINE_FORMAT_ERROR,
                exception.getMessage());
    }

    @Test
    public void execute_validDeadline_addsToTaskList() throws RafayelException {
        String descriptionDate = "Complete assignment /by Mar 15 2024 23:59";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        String result = deadlineCommand.execute(tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(result.contains("Complete assignment"));
        assertTrue(result.contains("added"));
    }

    @Test
    public void execute_multipleDeadlines_addsCorrectly() throws RafayelException {
        String descriptionDate1 = "Task 1 /by Mar 15 2024 10:00";
        String descriptionDate2 = "Task 2 /by Mar 16 2024 12:00";

        DeadlineCommand deadline1 = new DeadlineCommand(descriptionDate1);
        DeadlineCommand deadline2 = new DeadlineCommand(descriptionDate2);

        deadline1.execute(tasks, storage);
        deadline2.execute(tasks, storage);

        assertEquals(2, tasks.getSize());
    }

    @Test
    public void deadlineWithWhitespace_trimsCorrectly() throws RafayelException {
        String descriptionDate = "   Project submission   /by   Apr 1 2024 17:00   ";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        String result = deadlineCommand.execute(tasks, storage);

        assertEquals(1, tasks.getSize());
        assertTrue(result.contains("Project submission"));
    }

    @Test
    public void validDeadlineWithSlashDateFormat_success() throws RafayelException {
        String descriptionDate = "Submit report /by 2024/02/29 14:00";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        assertDoesNotThrow(() -> deadlineCommand.execute(tasks, storage));
    }

    @Test
    public void validDeadlineWithDashDateFormat_success() throws RafayelException {
        String descriptionDate = "Submit report /by 29-02-2024 14:00";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        assertDoesNotThrow(() -> deadlineCommand.execute(tasks, storage));
    }

    @Test
    public void deadlineWithEmptyDescription_throwsException() {
        String descriptionDate = "/by Feb 29 2024 14:00";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                deadlineCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains("blank canvas"));
    }

    @Test
    public void deadlineWithEmptyDate_throwsException() {
        String descriptionDate = "Submit report /by";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                deadlineCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().equals(DeadlineCommand.DEADLINE_FORMAT_ERROR));
    }

    @Test
    public void deadlineWithInvalidDateFormat_throwsException() {
        String descriptionDate = "Submit report /by invalid-date-format";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                        deadlineCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains(Rafayel.DATE_FORMAT_ERROR));
    }

    @Test
    public void deadlineWithOnlyByKeyword_throwsException() {
        String descriptionDate = "/by";
        deadlineCommand = new DeadlineCommand(descriptionDate);

        RafayelException exception = assertThrows(RafayelException.class, () ->
                deadlineCommand.execute(tasks, storage));
        assertTrue(exception.getMessage().contains(DeadlineCommand.DEADLINE_FORMAT_ERROR));
    }
}
