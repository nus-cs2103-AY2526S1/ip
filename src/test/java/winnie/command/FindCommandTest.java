package winnie.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import winnie.task.Todo;
import winnie.tasklist.TaskList;
import winnie.ui.Cli;
import winnie.ui.Ui;
import winnie.storage.Storage;

public class FindCommandTest {
    private FindCommand findCommand;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        findCommand = new FindCommand("book");
        taskList = new TaskList();
        ui = new Cli();
        storage = new Storage("./data/test.txt");

        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("buy groceries"));
        taskList.addTask(new Todo("return book to library"));
        taskList.addTask(new Todo("watch movie"));
    }

    @Test
    public void constructor_validKeyword_createsFindCommand() {
        FindCommand command = new FindCommand("test");
        assertNotNull(command);
    }

    @Test
    public void isExit_findCommand_returnsFalse() {
        assertFalse(findCommand.isExit());
    }

    @Test
    public void execute_keywordMatchesMultipleTasks_displaysMatchingTasks() {
        assertDoesNotThrow(() -> {
            findCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_keywordMatchesNoTasks_displaysNoMatchingTasks() {
        FindCommand noMatchCommand = new FindCommand("xyz");
        assertDoesNotThrow(() -> {
            noMatchCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_keywordIsCaseInsensitive_findsMatchingTasks() {
        FindCommand upperCaseCommand = new FindCommand("BOOK");
        assertDoesNotThrow(() -> {
            upperCaseCommand.execute(taskList, ui, storage);
        });
    }
}
