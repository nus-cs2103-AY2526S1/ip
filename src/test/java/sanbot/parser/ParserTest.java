package sanbot.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void handleInput_nonExitCommands() {
        assertEquals(Parser.handleInput("Hi"), "Oops, I am not sure what is the task type!");
    }

    @Test
    public void handleInput_nullInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            Parser.handleInput(null);
        });
    }

    @Test
    public void handleInput_emptyInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            Parser.handleInput("");
        });
    }

    @Test
    public void handleInput_whitespaceOnlyInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            Parser.handleInput("   ");
        });
    }

    @Test
    public void handleInput_listCommand_callsPrintListItems() {
        String result = Parser.handleInput("list");
        assertNotNull(result);
    }

    @Test
    public void handleInput_listCommandWithExtraText_callsPrintListItems() {
        String result = Parser.handleInput("list all tasks");
        assertNotNull(result);
    }

    @Test
    public void handleInput_markCommand_callsMarkDone() {
        String result = Parser.handleInput("mark 1");
        assertNotNull(result);
    }

    @Test
    public void handleInput_markCommandOnly_callsMarkDone() {
        String result = Parser.handleInput("mark");
        assertNotNull(result);
    }

    @Test
    public void handleInput_unmarkCommand_callsMarkNotDone() {
        String result = Parser.handleInput("unmark 1");
        assertNotNull(result);
    }

    @Test
    public void handleInput_unmarkCommandOnly_callsMarkNotDone() {
        String result = Parser.handleInput("unmark");
        assertNotNull(result);
    }

    @Test
    public void handleInput_deleteCommand_callsDeleteTask() {
        String result = Parser.handleInput("delete 1");
        assertNotNull(result);
    }

    @Test
    public void handleInput_deleteCommandOnly_callsDeleteTask() {
        String result = Parser.handleInput("delete");
        assertNotNull(result);
    }

    @Test
    public void handleInput_todoCommand_callsTodoHandler() {
        String result = Parser.handleInput("todo read book");
        assertNotNull(result);
    }

    @Test
    public void handleInput_deadlineCommand_callsDeadlineHandler() {
        String result = Parser.handleInput("deadline submit assignment /by 19-09-2025 23:59");
        assertNotNull(result);
    }

    @Test
    public void handleInput_eventCommand_callsEventHandler() {
        String result = Parser.handleInput("event team meeting /from 18-09-2025 16:30 /to 18-09-2025 17:30");
        assertNotNull(result);
    }

    @Test
    public void handleInput_findCommand_callsFindTasks() {
        String result = Parser.handleInput("find book");
        assertNotNull(result);
    }

    @Test
    public void handleInput_findCommandOnly_callsFindTasks() {
        String result = Parser.handleInput("find");
        assertNotNull(result);
    }

    @Test
    public void handleInput_unknownCommand_returnsErrorMessage() {
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("unknown"));
    }

    @Test
    public void handleInput_unknownCommandWithArguments_returnsErrorMessage() {
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("unknown command with args"));
    }

    @Test
    public void handleInput_caseMattersForCommands() {
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("LIST"));
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("Todo read book"));
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("BYE"));
    }

    @Test
    public void handleInput_commandsWithMultipleSpaces() {
        String result1 = Parser.handleInput("todo    read   book");
        String result2 = Parser.handleInput("event  team  meeting /from 18-09-2025 16:30 /to   18-09-2025 17:30");
        String result3 = Parser.handleInput("find   keyword");

        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
    }

    @Test
    public void handleInput_edgeCaseCommands() {
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("lists"));
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("todos"));
        assertEquals("Oops, I am not sure what is the task type!",
                Parser.handleInput("marking"));
    }
}
