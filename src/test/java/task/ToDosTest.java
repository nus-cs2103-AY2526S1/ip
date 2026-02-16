package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.CommandsEnum;
import ineffaexceptions.IneffaException;

/**
 * Test for the task command: todo
 */
public class ToDosTest {
    /**
     * Test that ToDo task created successfully
     *
     * @throws IneffaException If error encountered during parsing of task
     */
    @Test
    public void parseTask_createTodoTask_success() throws IneffaException {
        try {
            Task todo = Task.parseTask(
                    CommandsEnum.TODO, false, "borrow book"
            );

            assertEquals("[T][ ] borrow book", todo.toString());
        } catch (IneffaException e) {
            throw new IneffaException("exception thrown in parseTask_createDeadlineTask_success: " + e.getMessage());
        }
    }
}
