package mambo.parser;

import mambo.MamboException;
import mambo.command.ByeCommand;
import mambo.command.DeadlineCommand;
import mambo.command.DeleteCommand;
import mambo.command.EventCommand;
import mambo.command.ListCommand;
import mambo.command.MarkCommand;
import mambo.command.ToDoCommand;
import mambo.command.UnmarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parseCommandTestBye() {
        try {
            assertEquals(new ByeCommand(""), Parser.parseCommand("bye"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestMark() {
        try {
            assertEquals(new MarkCommand("1"), Parser.parseCommand("mark     1 "));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestUnmark() {
        try {
            assertEquals(new UnmarkCommand("2"), Parser.parseCommand("unmark 2"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestList() {
        try {
            assertEquals(new ListCommand(""), Parser.parseCommand("list"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestEvent() {
        try {
            assertEquals(new EventCommand("dinner /from 4 /to 6"),
                    Parser.parseCommand("event dinner /from 4 /to 6"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestToDo() {
        try {
            assertEquals(new ToDoCommand("die"), Parser.parseCommand("todo die"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestDeadline() {
        try {
            assertEquals(new DeadlineCommand("work /by monday"),
                    Parser.parseCommand("deadline work /by monday"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestDelete() {
        try {
            assertEquals(new DeleteCommand("10"), Parser.parseCommand("delete   10"));
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseCommandTestWrong() {
        try {
            Parser.parseCommand("hi mambo");
        } catch (MamboException e) {
            assertEquals("ummm not sure what that's supposed to mean. try one of the commands listed!",
                    e.getMessage());
        }
    }



    @Test
    public void parseFileTestToDo() {
        try {
            assertEquals("(T) [ ] die",
                    Parser.parseLineInFile("T / false / die").toString());
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseFileTestDeadline() {
        try {
            assertEquals("(D) [X] work (by: monday)",
                    Parser.parseLineInFile("D / true / work / monday").toString());
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseFileTestEvent() {
        try {
            assertEquals("(E) [ ] dinner (from: 4pm -> to: 6pm)",
                    Parser.parseLineInFile("E / false / dinner / 4pm / 6pm").toString());
        } catch (MamboException e) {
            assertEquals("no exception thrown", "exception thrown");
        }
    }

    @Test
    public void parseFileTestCorrupted() {
        try {
            Parser.parseLineInFile("E / false / dinner / 4pm ");
        } catch (MamboException e) {
            assertEquals("file is corrupted", e.getMessage());
        }
    }
}
