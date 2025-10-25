package dwight.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link TaskParserFactory}.
 *
 * <p>Ensures that the factory correctly maps task type keywords to their respective {@link
 * TaskParser} implementations and throws the expected exception when an unknown type is provided.
 */
class TaskParserFactoryTest {

    /** Both short and long forms of "todo" return a {@link ToDoParser}. */
    @Test
    void validTypeTodoReturnsToDoParser() throws Exception {
        assertTrue(
                TaskParserFactory.createFileParser("T") instanceof ToDoParser,
                "'T' should map to ToDoParser");
        assertTrue(
                TaskParserFactory.createFileParser("todo") instanceof ToDoParser,
                "'todo' should map to ToDoParser");
    }

    /** Both short and long forms of "deadline" return a {@link DeadlineParser}. */
    @Test
    void validTypeDeadlineReturnsDeadlineParser() throws Exception {
        assertTrue(
                TaskParserFactory.createFileParser("D") instanceof DeadlineParser,
                "'D' should map to DeadlineParser");
        assertTrue(
                TaskParserFactory.createFileParser("deadline") instanceof DeadlineParser,
                "'deadline' should map to DeadlineParser");
    }

    /** Both short and long forms of "event" return an {@link EventParser}. */
    @Test
    void validTypeEventReturnsEventParser() throws Exception {
        assertTrue(
                TaskParserFactory.createFileParser("E") instanceof EventParser,
                "'E' should map to EventParser");
        assertTrue(
                TaskParserFactory.createFileParser("event") instanceof EventParser,
                "'event' should map to EventParser");
    }

    /** Unknown type throws {@link UnknownTaskTypeException}. */
    @Test
    void invalidTypeThrowsUnknownTaskTypeException() {
        assertThrows(
                UnknownTaskTypeException.class,
                () -> TaskParserFactory.createFileParser("X"),
                "Unknown type should throw UnknownTaskTypeException");
    }

    /** Case sensitivity: unsupported uppercase should throw {@link UnknownTaskTypeException}. */
    @Test
    void caseSensitivityUppercaseFailsIfNotMapped() {
        assertThrows(
                UnknownTaskTypeException.class,
                () -> TaskParserFactory.createFileParser("TODO"),
                "Uppercase 'TODO' is not mapped and should throw");
    }

    /** Null input should throw {@link UnknownTaskTypeException}. */
    @Test
    void nullTypeThrowsUnknownTaskTypeException() {
        assertThrows(
                UnknownTaskTypeException.class,
                () -> TaskParserFactory.createFileParser(null),
                "null type should throw UnknownTaskTypeException");
    }
}
