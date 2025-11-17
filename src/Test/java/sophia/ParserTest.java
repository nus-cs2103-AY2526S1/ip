package sophia;

import org.junit.jupiter.api.Test;

//AI generated tests for parser functions
public class ParserTest {
    /**
     * Test if parser validates todo task commands correctly
     */
    @Test
    void testTodo() {
        org.junit.jupiter.api.Assertions.assertTrue(Parser.validateTodoInput("todo read book"));
        org.junit.jupiter.api.Assertions.assertFalse(Parser.validateTodoInput("todo"));
    }

    /**
     * Test if parser validates deadline task commands correctly
     */
    @Test
    void testDeadline() {
        org.junit.jupiter.api.Assertions.assertTrue(
                Parser.validateDeadlineInput("deadline submit /by 2025-12-09"));
        org.junit.jupiter.api.Assertions.assertTrue(
                Parser.validateDeadlineInput("deadline submit report /by 2025-12-09 23:59"));
        org.junit.jupiter.api.Assertions.assertFalse(
                Parser.validateDeadlineInput("deadline submit report by 2025-12-09"));
    }

    /**
     * Test if parser validates event task commands correctly
     */
    @Test
    void testEvent() {
        org.junit.jupiter.api.Assertions.assertTrue(
                Parser.validateEventInput("event conf /from 2025-12-10 /to 2025-12-12"));
        org.junit.jupiter.api.Assertions.assertTrue(
                Parser.validateEventInput("event conf /from 2025-12-10 09:00 /to 2025-12-12 17:00"));
        org.junit.jupiter.api.Assertions.assertFalse(
                Parser.validateEventInput("event conf from 2025-12-10 to 2025-12-12"));
    }

    @Test
    void testFind() {
        org.junit.jupiter.api.Assertions.assertTrue(
                Parser.validateFindInput("find book"));
        org.junit.jupiter.api.Assertions.assertTrue(
                Parser.validateFindInput("find read book now"));
        org.junit.jupiter.api.Assertions.assertFalse(
                Parser.validateFindInput("find"));
    }

    @Test
    void testMarkUnmarkDelete() {
        org.junit.jupiter.api.Assertions.assertTrue(Parser.validateMarkInput("mark 1"));
        org.junit.jupiter.api.Assertions.assertTrue(Parser.validateUnmarkInput("unmark 2"));
        org.junit.jupiter.api.Assertions.assertTrue(Parser.validateDeleteInput("delete 3"));
        org.junit.jupiter.api.Assertions.assertFalse(Parser.validateDeleteInput("delete"));
    }
}
