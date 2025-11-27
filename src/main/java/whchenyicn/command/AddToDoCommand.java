package whchenyicn.command;

import java.io.IOException;

import whchenyicn.exceptions.WhchenyicnException;
import whchenyicn.task.TaskList;
import whchenyicn.task.ToDo;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * A command to add ToDo task to tasklist.
 */
public class AddToDoCommand extends Command {
    private String s;

    /**
     * Creates a command to add event task.
     *
     * @param s User input text after "todo".
     */
    public AddToDoCommand(String s) {
        this.s = s;
    }

    @Override
    public String execute(TaskList tlist, Ui ui, Storage storage) throws WhchenyicnException {

        if (s == null || s.trim().isEmpty()) {
            throw new WhchenyicnException("The description of a todo cannot be empty.");
        }

        checkFull(tlist);
        assertUi (tlist, ui, storage);

        tlist.add(new ToDo(s));

        try {
            storage.save(tlist);
        } catch (IOException e) {
            ui.printError("Failed to save: " + e.getMessage());
        }

        return ui.printTodoTask(tlist);
    }

}