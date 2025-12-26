package gene;

import org.junit.jupiter.api.Test;

import gene.command.AddCommand;
import gene.command.ExitCommand;
import gene.command.Command;
import gene.command.ListCommand;
import gene.command.MarkCommand;
import gene.command.UnmarkCommand;
import gene.command.PrintCommand;
import gene.tasks.DeadlineTask;
import gene.tasks.EventTask;
import gene.tasks.TodoTask;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void todo_success() {
        Command c = Parser.parse("todo cs2103 homework");
        Command actual = new AddCommand(new TodoTask("cs2103 homework", false));
        assertEquals(c, actual);
    }

    @Test
    public void event_success() {
        Command c = Parser.parse("event cs2103 homework /from 2023-01-01 1900 /to 2025-01-01 2000");
        Command actual = new AddCommand(new EventTask("cs2103 homework", "2023-01-01 1900", "2025-01-01 2000", false));
        assertEquals(c, actual);
    }

    @Test
    public void deadline_success() {
        Command c = Parser.parse("deadline cs2103 homework /by 2023-01-01 1900");
        Command actual = new AddCommand(new DeadlineTask("cs2103 homework", "2023-01-01 1900", false));
        assertEquals(c, actual);
    }

    @Test
    public void exit_command_success() {
        Command c = Parser.parse("Bye");
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void list_command_success() {
        Command c = Parser.parse("List");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void mark_command_success() {
        Command c = Parser.parse("Mark 3");
        Command actual = new MarkCommand(3);
        assertEquals(c, actual);
    }

    @Test
    public void unmark_command_success() {
        Command c = Parser.parse("Unmark 3");
        Command actual = new UnmarkCommand(3);
        assertEquals(c, actual);
    }

    @Test
    public void print_invalid_command_success() {
        Command c = Parser.parse("randomCommand to fail");
        Command actual = new PrintCommand(Ui.SPACING + "I'm sorry, but I don't know what that means :-(");
        assertEquals(c, actual);
    }

    @Test
    public void print_invalid_syntax_success() {
        Command c1 = Parser.parse("event cs2103 homework");
        Command actual1 = new PrintCommand(Ui.SPACING + "Invalid event format. Use: event <description> /from <start> /to <end>");
        Command c2 = Parser.parse("deadline cs2103 homework");
        Command actual2 = new PrintCommand(Ui.SPACING + "Invalid deadline format. Use: deadline <description> /by <date>");
        Command c3 = Parser.parse("todo");
        Command actual3 = new PrintCommand(Ui.SPACING + "The description of a todo cannot be empty.");

        assertEquals(c1, actual1);
        assertEquals(c2, actual2);
        assertEquals(c3, actual3);
    }


}
