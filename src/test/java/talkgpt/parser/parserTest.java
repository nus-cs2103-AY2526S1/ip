package talkgpt.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import talkgpt.TalkgptException;
import talkgpt.command.AddCommand;
import talkgpt.command.DeleteCommand;
import talkgpt.command.FindCommand;
import talkgpt.command.GoodbyeCommand;
import talkgpt.command.ListCommand;
import talkgpt.command.MarkCommand;
import talkgpt.command.TagCommand;
import talkgpt.command.UnmarkCommand;
import talkgpt.task.ToDo;
import talkgpt.task.Deadline;
import talkgpt.task.Event;

import org.junit.jupiter.api.Test;

public class parserTest {
    Parser parser = new Parser();

    @Test
    public void testParse_throwsTalkgptException() {
        //test error thrown when input is empty
        assertThrows(TalkgptException.class, () -> {
            parser.parse("");
        });

        //test error thrown when input is a single word that is not a valid command
        assertThrows(TalkgptException.class, () -> {
            parser.parse("random");
        });

        //test error thrown when input is a valid command but single word
        assertThrows(TalkgptException.class, () -> {
            parser.parse("mark");
        });
    }

    @Test
    public void testParse_invalidTaskInputs() {
        //test error thrown when todo command has no description
        assertThrows(TalkgptException.class, () -> {
            parser.parse("todo ");
        });

        //test error thrown when deadline command has no description
        assertThrows(TalkgptException.class, () -> {
            parser.parse("deadline ");
        });

        //test error thrown when event command has no description
        assertThrows(TalkgptException.class, () -> {
            parser.parse("event ");
        });

        //test error thrown when deadline command has no /by
        assertThrows(TalkgptException.class, () -> {
            parser.parse("deadline project");
        });

        //test error thrown when event command has no /at
        assertThrows(TalkgptException.class, () -> {
            parser.parse("event meeting");
        });
    }

    @Test
    public void testParse_validInputs() throws TalkgptException {
        //tag command
        TagCommand tagCommand = new TagCommand("school");
        assertEquals(tagCommand, parser.parse("tag school"));

        //list command
        ListCommand listCommand = new ListCommand();
        assertEquals(listCommand, parser.parse("list"));

        //bye command
        GoodbyeCommand goodbyeCommand = new GoodbyeCommand();
        assertEquals(goodbyeCommand, parser.parse("bye"));

        //mark command
        MarkCommand markCommand = new MarkCommand("1");
        assertEquals(markCommand, parser.parse("mark 1"));

        //unmark command
        UnmarkCommand unmarkCommand = new UnmarkCommand("1");
        assertEquals(unmarkCommand, parser.parse("unmark 1"));

        //find command
        FindCommand findCommand = new FindCommand("project");
        assertEquals(findCommand, parser.parse("find project"));

        //add command - todo
        ToDo toDo = new ToDo("read book", "school");
        AddCommand addToDoCommand = new AddCommand(toDo);
        assertEquals(addToDoCommand, parser.parse("todo read book /tag school"));

        //add command - deadline
        Deadline deadline = new Deadline("return book", "3/12/2024 1800", "school");
        AddCommand addDeadlineCommand = new AddCommand(deadline);
        assertEquals(addDeadlineCommand, parser.parse("deadline return book /by 3/12/2024 1800 /tag school"));

        //add command - event
        Event event = new Event("project meeting", "3/12/2024 1400", "3/12/2024 1600", "school");
        AddCommand addEventCommand = new AddCommand(event);
        assertEquals(addEventCommand, parser.parse("event project meeting /from 3/12/2024 1400 /to 3/12/2024 1600 /tag school"));

        //delete command
        DeleteCommand deleteCommand = new DeleteCommand("1");
        assertEquals(deleteCommand, parser.parse("delete 1"));
    }
}
