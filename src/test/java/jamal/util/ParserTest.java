package jamal.util;

import jamal.command.*;
import jamal.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void listSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertInstanceOf(ListOverdueCommand.class, Parser.parse("list overdue"));
        assertInstanceOf(ListOngoingCommand.class, Parser.parse("list ongoing"));
        assertInstanceOf(ListUpcomingCommand.class, Parser.parse("list upcoming"));
    }

    @Test
    public void listUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("list something");
        });
    }

    @Test
    public void markSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
    }

    @Test
    public void markUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("mark");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("mark 0");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("mark something");
        });
    }

    @Test
    public void unmarkSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
    }

    @Test
    public void unmarkUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("unmark");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("unmark 0");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("unmark something");
        });
    }

    @Test
    public void deleteSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"));
    }

    @Test
    public void deleteUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("delete");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("delete 0");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("delete something");
        });
    }

    @Test
    public void todoSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(ToDoTaskCommand.class, Parser.parse("todo something"));
    }

    @Test
    public void todoUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("todo");
        });
    }

    @Test
    public void deadlineSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(DeadlineTaskCommand.class, Parser.parse("deadline something /by 2020-10-10T00:00:00"));
    }

    @Test
    public void deadlineUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("deadline");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("deadline something");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("deadline something /by 2020-10-10");
        });
    }

    @Test
    public void eventSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(EventTaskCommand.class, Parser.parse("event something /from 2020-10-10T00:00:00 /to 2020-10-10T00:00:01"));
    }

    @Test
    public void eventUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("event");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("event something");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("event something /from 2020-10-10 /to 2020-10-11");
        });
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("event something /from 2020-10-10T00:00:00 /to 2020-10-11");
        });
    }

    @Test
    public void exitSuccessfulCommandTest() throws InvalidCommandException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void exitUnsuccessfulCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("bye something");
        });
    }

    @Test
    public void unknownCommandTest() {
        assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("/unknown");
        });
    }



}

