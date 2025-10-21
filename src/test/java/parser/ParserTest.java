package parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import command.Add;
import command.Delete;
import command.Exit;
import command.Find;
import command.Greet;
import command.Invalid;
import command.ListOut;
import command.Mark;
import command.NotesAdd;
import command.NotesDelete;
import command.NotesList;
import exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
	@Test
	void stringToDate_validIso_returnsLocalDate() throws DukeException {
		LocalDate expected = LocalDate.of(2025, 9, 30);
		LocalDate actual = Parser.stringToDate("2025-09-30");
		assertEquals(expected, actual);
	}

	@Test
	void stringToDate_invalidFormat_throwsDukeException() {
		assertThrows(DukeException.class, () -> Parser.stringToDate("09/30/2025"));
	}

	@Test
	void stringToDate_invalidDate_throwsDukeException() {
		assertThrows(DukeException.class, () -> Parser.stringToDate("2025-13-01"));
	}

	@Test
	void parse_greetCommand_returnsGreet() {
		assertTrue(Parser.parse("greet") instanceof Greet);
		assertTrue(Parser.parse("hi") instanceof Greet);
	}

	@Test
	void parse_exitCommand_returnsExit() {
		assertTrue(Parser.parse("bye") instanceof Exit);
		assertTrue(Parser.parse("exit") instanceof Exit);
	}

	@Test
	void parse_listCommand_returnsListOut() {
		assertTrue(Parser.parse("list") instanceof ListOut);
	}

	@Test
	void parse_findCommand_returnsFind() {
		assertTrue(Parser.parse("find book") instanceof Find);
	}

	@Test
	void parse_todoCommand_returnsAdd() {
		assertTrue(Parser.parse("todo read book") instanceof Add);
	}

	@Test
	void parse_deadlineCommand_returnsAdd() {
		assertTrue(Parser.parse("deadline submit /by 2025-09-30") instanceof Add);
	}

	@Test
	void parse_eventCommand_returnsAdd() {
		assertTrue(Parser.parse("event party /from 2025-10-01 /to 2025-10-02") instanceof Add);
	}

	@Test
	void parse_deleteCommand_returnsDelete() {
		assertTrue(Parser.parse("delete 2") instanceof Delete);
	}

	@Test
	void parse_markCommand_returnsMark() {
		assertTrue(Parser.parse("mark 3") instanceof Mark);
	}

	@Test
	void parse_unmarkCommand_returnsMark() {
		assertTrue(Parser.parse("unmark 1") instanceof Mark);
	}

	@Test
	void parse_notesAddCommand_returnsNotesAdd() {
		assertTrue(Parser.parse("note add remember milk") instanceof NotesAdd);
	}

	@Test
	void parse_notesDeleteCommand_returnsNotesDelete() {
		assertTrue(Parser.parse("note delete 1") instanceof NotesDelete);
	}

	@Test
	void parse_notesListCommand_returnsNotesList() {
		assertTrue(Parser.parse("note list") instanceof NotesList);
	}

	@Test
	void parse_unknownCommand_returnsInvalid() {
		assertTrue(Parser.parse("random text") instanceof Invalid);
	}

	@Test
	void parse_invalidDeadlineFormat_returnsInvalid() {
		assertTrue(Parser.parse("deadline missing by keyword") instanceof Invalid);
	}

	@Test
	void parse_invalidEventFormat_returnsInvalid() {
		assertTrue(Parser.parse("event missing dates") instanceof Invalid);
	}
}
