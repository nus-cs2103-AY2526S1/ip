package benn.parser;

import benn.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static void assertType(Command c, Class<?> clazz) {
        assertNotNull(c, "Parser returned null");
        assertTrue(clazz.isInstance(c),
                "Expected " + clazz.getSimpleName() + " but got " + c.getClass().getSimpleName());
    }

    @Test
    void parse_blank_returnsNull() {
        String[] blanks = {"", "   ", "\n  \n"};
        for (String in : blanks) {
            Command c = Parser.parse(in);
            assertType(c, InvalidCommand.class);
        }
    }

    @Test
    void parse_bye_success() {
        assertType(Parser.parse("bye"), ExitCommand.class);
        assertType(Parser.parse("  BYE  "), ExitCommand.class);
    }

    @Test
    void parse_list_success() {
        assertType(Parser.parse("list"), ListCommand.class);
        assertType(Parser.parse("  LIST  "), ListCommand.class);
    }

    @Test
    void parse_list_withArgs_invalid() {
        assertType(Parser.parse("list 3"), InvalidCommand.class);
    }

    @Test
    void parse_todo_success() {
        assertType(Parser.parse("todo read book"), AddTodoCommand.class);
    }

    @Test
    void parse_todo_missingDesc_invalid() {
        assertType(Parser.parse("todo "), InvalidCommand.class);
    }

    @Test
    void parse_deadline_validDatetime_success() {
        assertType(Parser.parse("deadline project /by 12/12/2069 14:50"), AddDeadlineCommand.class);
    }

    @Test
    void parse_deadline_missingBy_invalid() {
        assertType(Parser.parse("deadline project"), InvalidCommand.class);
    }

    @Test
    void parse_deadline_wrongFormat_invalid() {
        assertType(Parser.parse("deadline project /by 12-12-2069 2pm"), InvalidCommand.class);
    }

    @Test
    void deadline_accepts_midnight_and_lastMinute() {
        assertType(Parser.parse("deadline x /by 01/01/2025 00:00"), AddDeadlineCommand.class);
        assertType(Parser.parse("deadline y /by 31/12/2025 23:59"), AddDeadlineCommand.class);
    }

    @Test
    void deadline_accepts_weird_spacing() {
        assertType(Parser.parse("  deadline   task   /by   09/07/2025   08:05   "), AddDeadlineCommand.class);
        assertType(Parser.parse("\tdeadline\tfoo\t/by\t02/02/2025\t09:09"), AddDeadlineCommand.class);
    }

    @Test
    void deadline_rejects_outOfRange_time() {
        assertType(Parser.parse("deadline bad /by 01/01/2025 24:00"), InvalidCommand.class); // hour 24 invalid
        assertType(Parser.parse("deadline bad /by 01/01/2025 23:60"), InvalidCommand.class); // minute 60 invalid
        assertType(Parser.parse("deadline bad /by 01/01/2025 7:05"),  InvalidCommand.class); // missing leading 0 hour
        assertType(Parser.parse("deadline bad /by 01/01/2025 07:5"),  InvalidCommand.class); // missing leading 0 minute
    }

    @Test
    void deadline_rejects_wrong_separators_and_order() {
        assertType(Parser.parse("deadline bad /by 2025/01/01 07:05"), InvalidCommand.class); // yyyy/dd/MM not allowed
        assertType(Parser.parse("deadline bad /by 01-01-2025 07:05"), InvalidCommand.class); // '-' separators
        assertType(Parser.parse("deadline bad /by 1/01/2025 07:05"),   InvalidCommand.class); // no leading zero in day
        assertType(Parser.parse("deadline bad /by 01/1/2025 07:05"),   InvalidCommand.class); // no leading zero in month
        assertType(Parser.parse("deadline bad /by 01/01/25 07:05"),    InvalidCommand.class); // year not 4 digits
    }

    @Test
    void keywords_are_caseInsensitive_dates_are_not() {
        assertType(Parser.parse("DeAdLiNe thing /By 09/09/2025 09:09"), AddDeadlineCommand.class);
        assertType(Parser.parse("deadline thing /by 09/09/2025 9:09"), InvalidCommand.class);
    }

    @Test
    void deadline_accepts_trailing_leading_spaces_around_date() {
        assertType(Parser.parse("deadline task /by   07/07/2025   07:07  "), AddDeadlineCommand.class);
    }

    @Test
    void parse_event_valid_success() {
        assertType(Parser.parse(
                "event meeting /from 12/08/2002 19:23 /to 12/08/2002 12:50"
        ), AddEventCommand.class);
    }

    @Test
    void parse_event_missingParts_invalid() {
        assertType(Parser.parse("event meeting"), InvalidCommand.class);
        assertType(Parser.parse("event meeting /from 12/08/2002 12:23"), InvalidCommand.class);
        assertType(Parser.parse("event meeting /to 12/08/2002 12:50"), InvalidCommand.class);
    }

    @Test
    void event_accepts_valid_range_and_spacing() {
        assertType(Parser.parse("event meet /from 01/03/2025 09:00 /to 01/03/2025 10:00"), AddEventCommand.class);
        assertType(Parser.parse("  event  foo  /from  05/05/2025 05:05   /to   05/05/2025 06:06  "), AddEventCommand.class);
    }

    @Test
    void event_rejects_when_from_or_to_malformed() {
        assertType(Parser.parse("event bad /from 1/03/2025 09:00 /to 01/03/2025 10:00"), InvalidCommand.class);
        assertType(Parser.parse("event bad /from 01/03/2025 09:00 /to 1/03/2025 10:00"), InvalidCommand.class);
        assertType(Parser.parse("event bad /from 01/03/2025 25:00 /to 01/03/2025 10:00"), InvalidCommand.class);
    }

    @Test
    void parse_mark_unmark_delete_valid_success() {
        assertType(Parser.parse("mark 1"), MarkCommand.class);
        assertType(Parser.parse("unmark 2"), UnmarkCommand.class);
        assertType(Parser.parse("delete 3"), DeleteCommand.class);
    }

    @Test
    void parse_mark_unmark_delete_invalid() {
        assertType(Parser.parse("mark"), InvalidCommand.class);
        assertType(Parser.parse("unmark two"), InvalidCommand.class);
        assertType(Parser.parse("delete 3.14"), InvalidCommand.class);
    }

    @Test
    void parse_find_valid_success() {
        assertType(Parser.parse("find book"), FindCommand.class);
        assertType(Parser.parse("  FIND   Book  "), FindCommand.class);
        assertType(Parser.parse("find read book"), FindCommand.class);
        assertType(Parser.parse("find 2025-08"), FindCommand.class);
    }

    @Test
    void parse_find_missing_keyword_invalid() {
        assertType(Parser.parse("find"), InvalidCommand.class);
        assertType(Parser.parse("find    "), InvalidCommand.class);
        assertType(Parser.parse("  find\t"), InvalidCommand.class);
    }

    @Test
    void parse_unknown_invalid() {
        assertType(Parser.parse("abracadabra"), InvalidCommand.class);
    }
}

