package yuan.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import yuan.storage.Storage;
import yuan.tasklist.TaskList;
import yuan.ui.UI;

/**
 * AI-assisted
 */
class ParserTest {

    private final Storage storage = new Storage("./data/test.txt");
    private final TaskList taskList = new TaskList();
    private final UI ui = new UI();

    @Test
    void parseAndExecute_todoCommand_addsTask() {
        String response = Parser.parseAndExecute("todo read book", taskList, storage, ui);
        assertTrue(response.contains("read book"), "Response should mention the added task");
        assertEquals(1, taskList.getSize(), "TaskList should contain one task");
    }

    @Test
    void parseAndExecute_invalidCommand_throwsException() {
        String response = Parser.parseAndExecute("nonsense command", taskList, storage, ui);
        assertTrue(response.contains("Try again"), "Should show error message for invalid command");
    }

    @Test
    void parseAndExecute_deleteCommand_removesTask() {
        Parser.parseAndExecute("todo write essay", taskList, storage, ui);
        assertEquals(1, taskList.getSize());

        String response = Parser.parseAndExecute("delete 1", taskList, storage, ui);

        assertTrue(response.contains("write essay"), "Response should mention the removed task");
        assertEquals(0, taskList.getSize(), "TaskList should be empty after deletion");
    }
}
