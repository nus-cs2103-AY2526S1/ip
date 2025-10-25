package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link ListCommand}. */
class ListCommandTest {

    private TaskList taskList;
    private ListCommand listCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.listCommand = new ListCommand();
    }

    /** Executes a list command on an empty task list, ensuring a SUCCESS response. */
    @Test
    void executeOnEmptyListReturnsSuccess() {
        CommandResponse response = this.listCommand.execute(this.taskList, "");
        assertNotNull(response);
        assertEquals(ResponseType.SUCCESS, response.getType());
    }

    /**
     * Executes a list command on a populated task list, ensuring that the response is SUCCESS and
     * includes the tasks.
     */
    @Test
    void executeOnPopulatedListReturnsSuccessAndIncludesTasks() throws Exception {
        this.taskList.add(new ToDo("Buy milk"));
        this.taskList.add(new ToDo("Read book"));
        CommandResponse response = this.listCommand.execute(this.taskList, "");
        assertNotNull(response);
        assertEquals(ResponseType.SUCCESS, response.getType());
        String msg = response.getMessage();
        assertNotNull(msg);
        assert msg.contains("Buy milk") || msg.contains("Read book");
    }

    /**
     * Executes a list command with a null task list, expecting an assertion error. Requires running
     * tests with JVM assertions enabled (-ea).
     */
    @Test
    void executeWithNullListThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> this.listCommand.execute(null, ""));
    }
}
