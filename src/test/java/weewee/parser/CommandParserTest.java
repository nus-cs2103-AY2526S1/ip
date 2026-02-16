package weewee.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import weewee.exception.WeeweeException;
import weewee.task.Task;
import weewee.task.TaskList;
import weewee.ui.Ui;


public class CommandParserTest {
    @Test
    public void parseAndExecute_invalidCommand_throwsException() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        Ui ui = new Ui();
        String input = "blehhh";
        Exception exception = assertThrows(WeeweeException.class,
                () -> CommandParser.parseAndExecute(input, list, ui)
        );

        assertEquals("Sorry, I don't understand what that means </3\n", exception.getMessage());
    }
}
