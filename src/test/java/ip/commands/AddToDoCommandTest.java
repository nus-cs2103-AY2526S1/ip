package ip.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.storage.StorageStub;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.tasks.ToDo;
import ip.ui.Ui;

public class AddToDoCommandTest {
    @Test
    public void execute_success() throws UnknownInputException, FileCorruptedException {
        AddToDoCommand testCommand = new AddToDoCommand();
        Ui ui = new Ui();
        Storage storageStub = new StorageStub();

        TaskList testListOne = new TaskList();
        testCommand.execute("todo homework", ui, storageStub, testListOne);
        Task testOne = testListOne.get(0);
        assertEquals(new ToDo("homework").toString(),
                testOne.toString());

        TaskList testListTwo = new TaskList();
        testCommand.execute("todo eat chicken 2 times", ui, storageStub, testListTwo);
        Task testTwo = testListTwo.get(0);
        assertEquals(new ToDo("eat chicken 2 times").toString(),
                testTwo.toString());
    }

    @Test
    public void execute_noDescription_exceptionThrown() throws FileCorruptedException {
        AddToDoCommand testCommand = new AddToDoCommand();
        Ui ui = new Ui();
        Storage storageStub = new StorageStub();
        TaskList testList = new TaskList();

        try {
            testCommand.execute("todo", ui, storageStub, testList);
            fail();
        } catch (UnknownInputException e) {
            assertEquals("Your ToDo has to have a description!", e.getMessage());
        }

        try {
            testCommand.execute("todo ", ui, storageStub, testList);
            fail();
        } catch (UnknownInputException e) {
            assertEquals("Your ToDo has to have a description!", e.getMessage());
        }
    }

}
