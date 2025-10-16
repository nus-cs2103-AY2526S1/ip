package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void testTask_validTask_success() {
        AddCommand todoCommand = new AddCommand("todo", "Read a book");
        assertEquals("T", todoCommand.taskType());

        AddCommand deadlineCommand = new AddCommand("deadline", "Submit report /by 2023-10-01");
        assertEquals("D", deadlineCommand.taskType());

        AddCommand eventCommand = new AddCommand("event", "Team meeting /from 2023-10-05 /to 2023-10-05");
        assertEquals("E", eventCommand.taskType());
    }
}
