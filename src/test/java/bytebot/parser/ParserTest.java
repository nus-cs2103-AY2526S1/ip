package bytebot.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bytebot.ByteException;
import bytebot.command.Command;
import bytebot.command.DeleteCommand;
import bytebot.command.ExitCommand;
import bytebot.command.FindCommand;
import bytebot.command.ListCommand;
import bytebot.command.MarkCommand;
import bytebot.command.SortCommand;
import bytebot.command.TodoCommand;
import bytebot.task.Deadline;
import bytebot.task.Event;
import bytebot.task.Task;
import bytebot.task.Todo;

public class ParserTest {

    @Test
    public void parse_simpleCommands_ok() throws Exception {
        Command c1 = Parser.parse("bye");
        assertTrue(c1 instanceof ExitCommand);
        Command c2 = Parser.parse("list");
        assertTrue(c2 instanceof ListCommand);
    }

    @Test
    public void parse_todoCommand_ok() throws Exception {
        Command c = Parser.parse("todo study exam");
        assertTrue(c instanceof TodoCommand);
    }

    @Test
    public void parse_markAndDelete_zeroBasedHandled() throws Exception {
        Command c1 = Parser.parse("mark 3");
        assertTrue(c1 instanceof MarkCommand);
        Command c2 = Parser.parse("delete 3");
        assertTrue(c2 instanceof DeleteCommand);
    }

    @Test
    public void parse_sortVariants_okAndErrors() throws Exception {
        Command c1 = Parser.parse("sort deadlines");
        assertTrue(c1 instanceof SortCommand);
        Command c2 = Parser.parse("sort all");
        assertTrue(c2 instanceof SortCommand);

        ByteException ex = assertThrows(ByteException.class, () -> Parser.parse("sort"));
        assertTrue(ex.getMessage().toLowerCase().contains("specify"));
    }

    @Test
    public void parse_find_requiresKeyword() throws Exception {
        assertTrue(Parser.parse("find book") instanceof FindCommand);
        ByteException ex = assertThrows(ByteException.class, (
                    ) -> ((FindCommand) Parser.parse("find "))
                        .execute(new bytebot.ui.Ui(), new bytebot.storage.Storage()));
        assertTrue(ex.getMessage().toLowerCase().contains("invalid"));
    }

    @Test
    public void parseTaskFromString_todoDeadlineEvent_ok() throws Exception {
        Task t1 = Parser.parseTaskFromString("1 | [T] read book");
        assertTrue(t1 instanceof Todo);

        Task t2 = Parser.parseTaskFromString("0 | [D] submit report (by: Jan 01 2025, 8:00 AM)");
        assertTrue(t2 instanceof Deadline);

        Task t3 = Parser.parseTaskFromString(
                "1 | [E] carnival (from: Sep 15 2025, 10:30 AM to: Sep 15 2025, 5:00 PM)");
        assertTrue(t3 instanceof Event);
    }

    @Test
    public void parseTaskFromString_invalidType_throws() {
        assertThrows(ByteException.class, () -> Parser.parseTaskFromString("1 | [X] something"));
    }

}


