package nerpbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the SemanticInterpreter class.
 */
public class SemanticInterpreterTest {

    @Test
    public void testDirectCommandsPassThrough() {
        // Direct commands should pass through unchanged
        assertTrue(SemanticInterpreter.isDirectCommand("todo buy milk"));
        assertTrue(SemanticInterpreter.isDirectCommand("deadline homework /by 2024-01-01"));
        assertTrue(SemanticInterpreter.isDirectCommand("event meeting /from 2pm /to 3pm"));
        assertTrue(SemanticInterpreter.isDirectCommand("list"));
        assertTrue(SemanticInterpreter.isDirectCommand("mark 1"));
        assertTrue(SemanticInterpreter.isDirectCommand("delete 2"));
        assertTrue(SemanticInterpreter.isDirectCommand("find book"));
    }

    @Test
    public void testNaturalLanguageTodo() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("I need to buy groceries");

        assertTrue(result.isNaturalLanguage());
        assertEquals("todo buy groceries", result.getCommand());
        assertTrue(result.needsConfirmation());
    }

    @Test
    public void testNaturalLanguageDeadline() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("finish homework by Friday");

        assertTrue(result.isNaturalLanguage());
        assertTrue(result.getCommand().startsWith("deadline"));
        assertTrue(result.getCommand().contains("/by"));
        assertTrue(result.needsConfirmation());
    }

    @Test
    public void testNaturalLanguageEvent() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("team meeting from 2pm to 4pm");

        assertTrue(result.isNaturalLanguage());
        assertTrue(result.getCommand().startsWith("event"));
        assertTrue(result.getCommand().contains("/from"));
        assertTrue(result.getCommand().contains("/to"));
    }

    @Test
    public void testNaturalLanguageMark() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("task 3 is done");

        assertTrue(result.isNaturalLanguage());
        assertEquals("mark 3", result.getCommand());
    }

    @Test
    public void testNaturalLanguageDelete() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("remove task 2");

        assertTrue(result.isNaturalLanguage());
        assertEquals("delete 2", result.getCommand());
    }

    @Test
    public void testNaturalLanguageList() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("show me all my tasks");

        assertTrue(result.isNaturalLanguage());
        assertEquals("list", result.getCommand());
    }

    @Test
    public void testNaturalLanguageFind() {
        SemanticInterpreter.InterpretationResult result =
                SemanticInterpreter.interpret("search for homework");

        assertTrue(result.isNaturalLanguage());
        assertTrue(result.getCommand().contains("find"));
        assertTrue(result.getCommand().contains("homework"));
    }
}
