package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link FindCommand}. */
class FindCommandTest {

    private TaskList taskList;
    private FindCommand findCommand;

    @BeforeEach
    void setUp() throws Exception {
        this.taskList = new TaskList();
        this.findCommand = new FindCommand();
        this.taskList.add(new ToDo("Buy milk"));
        this.taskList.add(new ToDo("Read book"));
    }

    /**
     * Executes a find with matches and verifies a SUCCESS response and that the original list is
     * not mutated.
     */
    @Test
    void executeWithMatchesReturnsSuccessAndDoesNotMutateList() {
        CommandResponse response = this.findCommand.execute(this.taskList, "milk");
        assertNotNull(response);
        assertEquals(ResponseType.SUCCESS, response.getType());
        assertEquals(2, this.taskList.size());
    }

    /**
     * Executes a find with no matches and verifies a SUCCESS response and that the original list is
     * not mutated.
     */
    @Test
    void executeWithNoMatchesReturnsSuccessAndDoesNotMutateList() {
        CommandResponse response = this.findCommand.execute(this.taskList, "nonexistent");
        assertNotNull(response);
        assertEquals(ResponseType.SUCCESS, response.getType());
        assertEquals(2, this.taskList.size());
    }

    /** Executes a find on an empty list and verifies a SUCCESS response. */
    @Test
    void executeOnEmptyListReturnsSuccess() {
        TaskList empty = new TaskList();
        CommandResponse response = this.findCommand.execute(empty, "anything");
        assertNotNull(response);
        assertEquals(ResponseType.SUCCESS, response.getType());
        assertEquals(0, empty.size());
    }

    /** Executes a find with a null description and expects an error response. */
    @Test
    void executeWithNullDescriptionReturnsErrorResponse() {
        CommandResponse response = this.findCommand.execute(this.taskList, null);
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Search keyword cannot be empty."),
                "Error message should mention search keyword cannot be empty.");
    }
}
