package angus.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import angus.command.ByeCommand;
import angus.command.ListCommand;
import angus.command.MarkCommand;
import angus.exception.AngusException;
import angus.storage.Storage;
import angus.task.TaskList;


public class ParserTest {
    private TaskList tasks;
    private Parser parser;
    @BeforeEach
    public void setUp() {
        Ui ui = new Ui();
        tasks = new TaskList(ui);
        Storage storage = new Storage("");
        parser = new Parser(ui, tasks, storage);
    }
    @Test
    public void parse_bye_success() throws AngusException {
        String fullCommand = "bye";
        assertInstanceOf(ByeCommand.class, parser.parse(fullCommand));
    }
    @Test
    public void parse_list_success() throws AngusException {
        String fullCommand = "list";
        assertInstanceOf(ListCommand.class, parser.parse(fullCommand));
    }
    @Test
    public void parse_mark_success() throws AngusException {
        String fullCommand = "mark 1";
        tasks.addTodo("");
        tasks.markTask(0);
        assertInstanceOf(MarkCommand.class, parser.parse(fullCommand));
    }
    @Test
    public void parse_notEnoughArgumentsForMark_exceptionThrown() {
        String expected = "Wrong usage of mark!"
                + Ui.LINE_BREAK
                + "Usage: mark [task number]";
        String fullCommand = "mark";
        try {
            parser.parse(fullCommand);
            fail();
        } catch (AngusException e) {
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    public void parse_markingNonIntegerTask_exceptionThrown() {
        String expected = "Wrong usage of mark!"
                + Ui.LINE_BREAK
                + "Usage: mark [task number]";
        String fullCommand = "mark hello";
        try {
            parser.parse(fullCommand);
            fail();
        } catch (AngusException e) {
            assertEquals(expected, e.getMessage());
        }
    }
}
