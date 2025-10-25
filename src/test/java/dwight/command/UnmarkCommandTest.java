package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.Task;
import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link UnmarkCommand}. */
class UnmarkCommandTest {
    private TaskList taskList;
    private UnmarkCommand unmarkCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.unmarkCommand = new UnmarkCommand();
    }

    /**
     * Executes a valid unmark command, ensuring the task is unmarked and a SUCCESS response is
     * returned.
     */
    @Test
    void executeValidUnmarkUnmarksTaskAndReturnsSuccess() throws Exception {
        this.taskList.add(new ToDo("Buy milk").mark());
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "1");
        Task task = this.taskList.get(0);
        assertFalse(task.isMarked());
        assertEquals(ResponseType.SUCCESS, response.getType());
    }

    /** Executes an unmark command on an empty list, expecting an error response. */
    @Test
    void executeOnEmptyListReturnsErrorResponse() {
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /** Executes an unmark command with an out-of-bounds index, expecting an error response. */
    @Test
    void executeOutOfBoundsIndexReturnsErrorResponse() throws Exception {
        this.taskList.add(new ToDo("Read book"));
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "2");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /**
     * Executes an unmark command with a non-numeric description, expecting the command to return an
     * ERROR response rather than throwing an exception.
     */
    @Test
    void executeNonNumericDescriptionReturnsErrorResponse() {
        // Changed from assertThrows to checking for ERROR response
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "abc");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("not a valid index"),
                "Error message should mention invalid index.");
    }

    /**
     * Additional test: Executes an unmark command with null description, expecting an error
     * response.
     */
    @Test
    void executeNullDescriptionReturnsErrorResponse() {
        CommandResponse response = this.unmarkCommand.execute(this.taskList, null);
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("specify an index"),
                "Error message should mention specifying an index.");
    }

    /**
     * Additional test: Executes an unmark command with empty description, expecting an error
     * response.
     */
    @Test
    void executeEmptyDescriptionReturnsErrorResponse() {
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("specify an index"),
                "Error message should mention specifying an index.");
    }

    /**
     * Additional test: Executes an unmark command on an already unmarked task to ensure it works
     * correctly.
     */
    @Test
    void executeUnmarkOnAlreadyUnmarkedTaskReturnsSuccess() throws Exception {
        // Add an unmarked task
        this.taskList.add(new ToDo("Write code"));
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "1");

        Task task = this.taskList.get(0);
        assertFalse(task.isMarked()); // Should still be unmarked
        assertEquals(ResponseType.SUCCESS, response.getType());
    }
}
