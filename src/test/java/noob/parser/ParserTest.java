package noob.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import noob.command.AddCommand;
import noob.command.DeleteCommand;
import noob.command.ExitCommand;
import noob.command.ListCommand;
import noob.command.MarkCommand;
import noob.exception.NoobException;
import noob.task.DeadlineTask;
import noob.task.EventTask;
import noob.task.Task;
import noob.task.TodoTask;

public class ParserTest {

    @Test
    public void exitCommandTest() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (NoobException e) {
            fail("\"bye\" command failed to return ExitCommand");
        }
    }

    @Test
    public void listCommandTest() {
        try {
            assertEquals(new ListCommand(), Parser.parse("list"));
        } catch (NoobException e) {
            fail("\"list\" command failed to return ListCommand");
        }
    }

    @Test
    public void markCommandTest() {
        try {
            assertEquals(new MarkCommand(5, true), Parser.parse("mark 5"));
            assertEquals(new MarkCommand(10, false), Parser.parse("unmark 10"));
        } catch (NoobException e) {
            fail("\"mark\" command failed to return MarkCommand");
        }
    }

    @Test
    public void addTodoCommandTest() {
        try {
            String desc = "test task";
            Task task = new TodoTask(desc);
            assertEquals(new AddCommand(task), Parser.parse("todo " + desc));
        } catch (NoobException e) {
            fail("\"todo\" command failed to return correct AddCommand");
        }
    }

    @Test
    public void addDeadlineCommandTest() {
        try {
            String desc = "test task";
            String by = "2025-06-06";
            Task task = new DeadlineTask(desc, by);
            assertEquals(new AddCommand(task), Parser.parse("deadline " + desc + " /by " + by));
        } catch (NoobException e) {
            fail("\"deadline\" command failed to return correct AddCommand");
        }
    }

    @Test
    public void addEventCommandTest() {
        try {
            String desc = "test task";
            String from = "2025-06-04";
            String to = "2025-06-06";
            Task task = new EventTask(desc, from, to);
            assertEquals(new AddCommand(task), Parser.parse("event " + desc + " /from " + from + " /to " + to));
        } catch (NoobException e) {
            fail("\"event\" command failed to return correct AddCommand");
        }
    }

    @Test
    public void deleteCommandTest() {
        try {
            assertEquals(new DeleteCommand(5), Parser.parse("delete 5"));
        } catch (NoobException e) {
            fail("\"delete\" command failed to return DeleteCommand");
        }
    }

    @Test
    public void invalidCommandTest() {
        NoobException e = assertThrows(NoobException.class, () -> Parser.parse("I'm invalid"));
        assertEquals("Please input a valid command", e.getMessage());
    }
}
