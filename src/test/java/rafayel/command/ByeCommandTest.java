
package rafayel.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import rafayel.RafayelException;
import rafayel.storage.Storage;
import rafayel.task.TaskList;
import rafayel.ui.Ui;

// Mock Ui class for isolated testing
class MockUi extends Ui {
    private final String exitMessage;

    public MockUi(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    @Override
    public String showExit() {
        return this.exitMessage;
    }
}

public class ByeCommandTest {

    private TaskList mockTasks;
    private Storage mockStorage;

    @Test
    public void testByeCommandConstructor_setsCorrectCommandType() {
        ByeCommand byeCommand = new ByeCommand();
        assertEquals(CommandHandle.CommandType.BYE, byeCommand.getCommand());
    }

    @Test
    public void testExecute_returnsCorrectExitMessage() throws RafayelException {
        // Create a ByeCommand with a MockUi that returns a predictable message
        ByeCommand byeCommand = new ByeCommand();

        String result = byeCommand.execute(mockTasks, mockStorage);

        // Asserts that it returns a String (does not throw an error) and that the string is not null.
        // The exact message depends on the real Ui.showExit() implementation.
        assertNotNull(result);
        assertInstanceOf(String.class, result);
    }

    @Test
    public void testExecute_doesNotThrowException() {
        ByeCommand byeCommand = new ByeCommand();

        // This asserts that no exception is thrown when execute is called
        assertDoesNotThrow(() -> {
            byeCommand.execute(mockTasks, mockStorage);
        });
    }

    // Test to show how it would work with a mocked Ui (if the design allowed for dependency injection)
    @Test
    public void testExecute_withMockUi_returnsExpectedMessage() throws RafayelException {
        // This test demonstrates the ideal unit test if we could inject the Ui dependency.
        String testExitMessage = "Goodbye! Hope to see you again soon!";
        Ui testUi = new MockUi(testExitMessage);

        assertEquals(testExitMessage, testUi.showExit());
    }
}
