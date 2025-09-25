package heimerdinger.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.TaskList;

public class AddDeadlineCommandTest {
    @Test
    public void addDeadlineCommand_noDescription_exceptionThrown() {
        try {
            Command c = new AddDeadlineCommand("");
            Ui ui = new Ui();
            Storage storage = new Storage("");
            TaskList tasks = new TaskList();
            c.execute(tasks, ui, storage);
        } catch (HeimerdingerException e) {
            assertEquals("The deadline requires a description!",
                    e.getMessage());
        }
    }

    @Test
    public void addDeadlineCommand_noByStatement_exceptionThrown() {
        try {
            Command c = new AddDeadlineCommand("todo keep clothes");
            Ui ui = new Ui();
            Storage storage = new Storage("");
            TaskList tasks = new TaskList();
            c.execute(tasks, ui, storage);
        } catch (HeimerdingerException e) {
            assertEquals("The deadline requires a due date!",
                    e.getMessage());
        }
    }

    @Test
    public void addDeadlineCommand_wrongByStatement_exceptionThrown() {
        try {
            Command c = new AddDeadlineCommand("todo keep clothes /by ");
            Ui ui = new Ui();
            Storage storage = new Storage("");
            TaskList tasks = new TaskList();
            c.execute(tasks, ui, storage);
        } catch (HeimerdingerException e) {
            assertEquals("Would you like some assistance in learning how to write dates? "
                            + "(It's in the 'yyyy-mm-dd'/'yyyy-mm-dd hh:mm' format!)",
                    e.getMessage());
        }
    }

    @Test
    public void addDeadlineCommand_wrongDateFormat_exceptionThrown() {
        try {
            Command c = new AddDeadlineCommand("todo keep clothes /by 10/10/2003");
            Ui ui = new Ui();
            Storage storage = new Storage("");
            TaskList tasks = new TaskList();
            c.execute(tasks, ui, storage);
        } catch (HeimerdingerException e) {
            assertEquals("Would you like some assistance in learning how to write dates? "
                            + "(It's in the 'yyyy-mm-dd'/'yyyy-mm-dd hh:mm' format!)",
                    e.getMessage());
        }
    }
}
