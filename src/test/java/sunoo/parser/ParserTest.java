package sunoo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import sunoo.command.AddCommand;
import sunoo.command.IncorrectCommand;
import sunoo.command.MarkCommand;
import sunoo.exception.SunooException;
import sunoo.task.Deadline;
import sunoo.task.ToDo;

public class ParserTest {

    @Test
    public void parse_byeInputHasFollowingCharacters_incorrectCommandReturned() {
        assertEquals(new IncorrectCommand("Sorry! Ddeonu doesn't know what you mean ToT"),
                Parser.parse("bye123"));
    }

    @Test
    public void parse_markInputNotFollowedByNumber_exceptionThrown() {
        try {
            assertEquals(new MarkCommand(1), Parser.parse("mark Abs8%^23"));
            fail();
        } catch (SunooException e) {
            assertEquals("ENGENE, I need a number to mark!", e.getMessage());
        }
    }

    @Test
    public void parse_todoInputHasNoDescription_exceptionThrown() {
        try {
            assertEquals(new AddCommand(new ToDo(false, "")), Parser.parse("todo   "));
            fail();
        } catch (SunooException e) {
            assertEquals("Sorry ENGENE, you don't have a todo description!", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineInputIsEmptyAfterBy_exceptionThrown() {
        try {
            assertEquals(new AddCommand(new Deadline(false, "homework", null)),
                    Parser.parse("deadline homework /by "));
        } catch (SunooException e) {
            assertEquals("""
                        ENGENE, there seems to be a problem!
                        1. Remember to include the " /by " keyword between your task description and deadline!
                        2. Your description and deadline cannot be empty!""",
                    e.getMessage());
        }
    }
}
