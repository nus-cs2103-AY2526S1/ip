package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.TaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link NewCommand}. */
class NewCommandTest {

    private TaskList taskList;
    private NewCommand newCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.newCommand = new NewCommand();
    }

    /**
     * Executes a valid todo command, ensuring that the task is added, the response type is SUCCESS,
     * and the message references the task.
     */
    @Test
    void executeValidTodoAddsTaskAndReturnsSuccess() {
        CommandResponse response = this.newCommand.execute(this.taskList, "todo Buy milk");

        assertEquals(1, this.taskList.size());
        assertEquals(ResponseType.SUCCESS, response.getType());
        assertTrue(response.getMessage().contains("Buy milk"));
    }

    /** Executes a command with an unknown type, ensuring the response type is ERROR. */
    @Test
    void executeUnknownTypeReturnsError() {
        CommandResponse response = this.newCommand.execute(this.taskList, "xyz Do something");
        assertEquals(ResponseType.ERROR, response.getType());
    }

    /** Executes a command with an incomplete description, ensuring the response type is ERROR. */
    @Test
    void executeIncompleteDescriptionReturnsError() {
        CommandResponse response = this.newCommand.execute(this.taskList, "deadline ");
        assertEquals(ResponseType.ERROR, response.getType());
    }

    /**
     * Executes the same command twice, ensuring that the second execution with a duplicate task
     * returns a response of type ERROR.
     */
    @Test
    void executeDuplicateTaskReturnsError() {
        this.newCommand.execute(this.taskList, "todo Buy milk");
        CommandResponse response = this.newCommand.execute(this.taskList, "todo Buy milk");
        assertEquals(ResponseType.ERROR, response.getType());
    }

    /** Executes a new with a null description and expects an error response. */
    @Test
    void executeWithNullDescriptionReturnsErrorResponse() {
        CommandResponse response = this.newCommand.execute(this.taskList, null);
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Task description cannot be empty."),
                "Error message should mention task description cannot be empty.");
    }
}
