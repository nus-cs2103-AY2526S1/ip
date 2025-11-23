package stewie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import stewie.exceptions.CommandException;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.ui.Ui;

/**
 * Contains tests for {@link ByeCommand}.
 */
class ByeCommandTest {

    @Test
    void execute_always_returnsByeMessage() throws CommandException {
        ByeCommand byeCommand = new ByeCommand();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("dummy.txt");

        String result = byeCommand.execute(taskList, storage);

        assertEquals(Ui.showBye(), result);
    }

    @Test
    void isExit_always_returnsTrue() {
        ByeCommand byeCommand = new ByeCommand();

        assertTrue(byeCommand.isExit());
    }

    @Test
    void getCommandType_always_returnsBye() {
        ByeCommand byeCommand = new ByeCommand();

        assertEquals(CommandType.BYE, byeCommand.getCommandType());
    }
}
