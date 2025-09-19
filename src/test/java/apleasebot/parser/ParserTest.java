package apleasebot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import apleasebot.exceptions.WrongTimeFormatException;
import apleasebot.tasks.Deadline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import apleasebot.commands.DeadlineCommand;
import apleasebot.commands.ListCommand;
import apleasebot.exceptions.UnknownCommandException;
import apleasebot.tasks.TaskList;
import apleasebot.ui.Storage;

public class ParserTest {
    private final Storage storage = new Storage("src/test/java/apleasebot/parser/ParserTest.txt");
    private final TaskList dummyList = new TaskList();

    @Test
    public void list_populatedList_success() {
        assertEquals(ListCommand.class, Parser.parse("list", storage).getClass());
    }

    @Test
    public void deadline_properSyntax_success() {
        assertEquals(
                DeadlineCommand.class,
                Parser.parse("deadline test deadline \\\\by 2025-08-25 00:00 am", storage).getClass()
        );
    }

    @Test
    public void gibberish_unrecognisedText_exception() {
        UnknownCommandException err = Assertions.assertThrows(
                UnknownCommandException.class, () -> {
                    Parser.parse("unrecognised", storage);
                }
        );

        Assertions.assertEquals(
                "APleaseBot error: I am not sure what you mean by: "
                        + "\"unrecognised\"\n"
                        + "Use 'help' to see list of recognised words and arguments needed",
                err.getMessage()
        );
    }

    @Test
    public void deadline_wrongTimeFormat_exception() {
        WrongTimeFormatException err = Assertions.assertThrows(
                WrongTimeFormatException.class, () -> {
                    Parser.parse("deadline testing \\\\by time", storage).execute(dummyList);
                }
        );

        Assertions.assertEquals(
                "APleaseBot error: Oops wrong date format! Try YYYY-MM-DD HH:MM am/pm",
                err.getMessage()
        );
    }
}
