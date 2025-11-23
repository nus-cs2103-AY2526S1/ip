package jinwoo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

class AddCommandTest {
    /**
     * Invalid date task instantiation all throw DukeException from parseToDate
     */
    @Test
    void execute_wrongDateThrowMessage() {
        AddCommand addCommand = new AddCommand(new String[]{"deadline", "return book", "wrongDate"});
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        DukeException e = assertThrows(DukeException.class, () -> addCommand.execute(tasks, ui, storage));
        assertEquals("Warning ??? : Please enter date in the format yyyy-MM-dd.", e.getMessage());
    }
}
