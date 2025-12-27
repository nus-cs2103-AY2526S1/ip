package ip.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.storage.StorageStub;
import ip.tasks.Deadline;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

public class AddDeadlineCommandTest {
    @Test
    public void execute_success() throws UnknownInputException, FileCorruptedException {
        AddDeadlineCommand testCommand = new AddDeadlineCommand();
        Ui ui = new Ui();
        Storage storageStub = new StorageStub();

        TaskList testListOne = new TaskList();
        testCommand.execute("deadline homework /by 2025-01-01", ui, storageStub, testListOne);
        Task testOne = testListOne.get(0);
        assertEquals(new Deadline("homework", LocalDate.parse("2025-01-01")).toString(),
                testOne.toString());

        TaskList testListTwo = new TaskList();
        testCommand.execute("Deadline wash the clothes /by 2022-10-15", ui, storageStub, testListTwo);
        Task testTwo = testListTwo.get(0);
        assertEquals(new Deadline("wash the clothes", LocalDate.parse("2022-10-15")).toString(),
                testTwo.toString());

        TaskList testListThree = new TaskList();
        testCommand.execute("deadline Finish 2030 Ip /by 2026-08-31", ui, storageStub, testListThree);
        Task testThree = testListThree.get(0);
        assertEquals(new Deadline("Finish 2030 Ip", LocalDate.parse("2026-08-31")).toString(),
                testThree.toString());

    }

    @Test
    public void execute_noDescription_exceptionThrown() throws FileCorruptedException {
        AddDeadlineCommand testCommand = new AddDeadlineCommand();
        Ui ui = new Ui();
        Storage storageStub = new StorageStub();
        TaskList testList = new TaskList();

        try {
            testCommand.execute("deadline", ui, storageStub, testList);
            fail();
        } catch (UnknownInputException e) {
            assertEquals("Your Deadline has to have a description!", e.getMessage());
        }

        try {
            testCommand.execute("deadline /by 2022-05-30", ui, storageStub, testList);
            fail();
        } catch (UnknownInputException e) {
            assertEquals("Your Deadline has to have a description!", e.getMessage());
        }
    }

    @Test
    public void execute_noBy_exceptionThrown() throws FileCorruptedException {
        try {
            AddDeadlineCommand testCommand = new AddDeadlineCommand();
            Ui ui = new Ui();
            Storage storageStub = new StorageStub();
            TaskList testList = new TaskList();
            testCommand.execute("deadline homework /2022-05-30", ui, storageStub, testList);
            fail();

        } catch (UnknownInputException e) {
            assertEquals("Your Deadline has to have a due date inputted with '/by'", e.getMessage());
        }
    }

    @Test
    public void execute_invalidDate_exceptionThrown() throws FileCorruptedException {
        try {
            AddDeadlineCommand testCommand = new AddDeadlineCommand();
            Ui ui = new Ui();
            Storage storageStub = new StorageStub();
            TaskList testList = new TaskList();
            testCommand.execute("deadline homework /by 5pm", ui, storageStub, testList);
            fail();

        } catch (UnknownInputException e) {
            assertEquals("Your Deadline has to have a due date in the format yyyy-mm-dd", e.getMessage());
        }
    }
}
