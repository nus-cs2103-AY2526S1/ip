package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link DeleteCommand}. */
class DeleteCommandTest {
    private TaskList taskList;
    private DeleteCommand deleteCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.deleteCommand = new DeleteCommand();
    }

    /**
     * Executes a valid delete command, ensuring that the task is removed and a SUCCESS response is
     * returned.
     */
    @Test
    void executeValidDeleteRemovesTaskAndReturnsSuccess() throws Exception {
        this.taskList.add(new ToDo("Buy milk"));
        CommandResponse response = this.deleteCommand.execute(this.taskList, "1");

        assertEquals(0, this.taskList.size());
        assertEquals(ResponseType.SUCCESS, response.getType());
        assertTrue(response.getMessage().contains("Buy milk"));
    }

    /**
     * Executes a delete command on an empty list, expecting an error response due to an
     * out-of-bounds index.
     */
    @Test
    void executeOnEmptyListReturnsErrorResponse() {
        CommandResponse response = this.deleteCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /** Executes a delete command with an out-of-bounds index, expecting an error response. */
    @Test
    void executeOutOfBoundsIndexReturnsErrorResponse() throws Exception {
        this.taskList.add(new ToDo("Read book"));
        CommandResponse response = this.deleteCommand.execute(this.taskList, "2");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /**
     * Executes a delete command with a non-numeric description, expecting the command to return an
     * ERROR response rather than throwing an exception.
     */
    @Test
    void executeNonNumericDescriptionReturnsErrorResponse() {
        // Changed from assertThrows to checking for ERROR response
        CommandResponse response = this.deleteCommand.execute(this.taskList, "abc");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("not a valid index"),
                "Error message should mention invalid index.");
    }

    /**
     * Additional test: Executes a delete command with null description, expecting an error
     * response.
     */
    @Test
    void executeNullDescriptionReturnsErrorResponse() {
        CommandResponse response = this.deleteCommand.execute(this.taskList, null);
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("specify an index"),
                "Error message should mention specifying an index.");
    }

    /**
     * Additional test: Executes a delete command with empty description, expecting an error
     * response.
     */
    @Test
    void executeEmptyDescriptionReturnsErrorResponse() {
        CommandResponse response = this.deleteCommand.execute(this.taskList, "");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("specify an index"),
                "Error message should mention specifying an index.");
    }

    /**
     * Additional test: Executes multiple delete commands to ensure proper indexing after deletions.
     */
    @Test
    void executeMultipleDeletesHandlesIndexingCorrectly() throws Exception {
        this.taskList.add(new ToDo("Task 1"));
        this.taskList.add(new ToDo("Task 2"));
        this.taskList.add(new ToDo("Task 3"));

        // Delete the first task
        CommandResponse response1 = this.deleteCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.SUCCESS, response1.getType());
        assertEquals(2, this.taskList.size());

        // Delete the new first task (originally Task 2)
        CommandResponse response2 = this.deleteCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.SUCCESS, response2.getType());
        assertEquals(1, this.taskList.size());

        // Verify the remaining task is Task 3
        assertTrue(this.taskList.get(0).isMatching("Task 3"));
    }

    /**
     * Additional test: Executes delete command with negative index after conversion, expecting an
     * error response.
     */
    @Test
    void executeNegativeIndexReturnsErrorResponse() throws Exception {
        this.taskList.add(new ToDo("Test task"));
        CommandResponse response = this.deleteCommand.execute(this.taskList, "0"); // 0-1 = -1
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }
}
