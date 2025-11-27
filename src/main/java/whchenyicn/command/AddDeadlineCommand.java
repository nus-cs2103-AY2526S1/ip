package whchenyicn.command;

import java.io.IOException;

import whchenyicn.exceptions.WhchenyicnException;
import whchenyicn.task.Deadline;
import whchenyicn.task.TaskList;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * A command to add a deadline task to tasklist.
 */
public class AddDeadlineCommand extends Command {

    private String s;
    protected static final String BY_TOKEN = "/by";

    /**
     * Creates a Command to Add Deadline Task.
     *
     * @param s User input text after "deadline".
     */
    public AddDeadlineCommand(String s) {
        this.s = s;
    }

    @Override
    public String execute(TaskList tlist, Ui ui, Storage storage) throws WhchenyicnException {
        checkFull(tlist);
        assertUi (tlist, ui, storage);

        if (s == null || s.isEmpty()) {
            throw new WhchenyicnException("whchenyicn.task cannot be empty, use deadline <desc> /by <when>");
        }

        int b = s.indexOf(BY_TOKEN);

        if (b < 0) {
            throw new WhchenyicnException("Missing '/by', use deadline <desc> /by <when>");
        }

        String desc = s.substring(0, b).trim();
        String rest = s.substring(b + BY_TOKEN.length()).trim();
        String by = rest.replaceFirst("^:", "").trim();

        assert !desc.isEmpty() : "Deadline description should not be empty here";
        assert !by.isEmpty() : "Deadline 'by' date should not be empty here";

        if (desc.isEmpty()) {
            throw new WhchenyicnException("Description cannot be empty 'deadline <desc> /by <when>' ");
        }

        if (by.isEmpty()) {
            throw new WhchenyicnException("The deadline cannot be empty, 'deadline <desc> /by <when>'");
        }

        try {
            tlist.add(new Deadline(desc, by));
        } catch (IllegalArgumentException ex) {
            throw new WhchenyicnException("Invalid date. Use yyyy-MM-dd");
        }

        try {
            storage.save(tlist);
        } catch (IOException e) {
            ui.printError("Failed to save: " + e.getMessage());
        }

        return ui.printDeadlineTask(tlist);
    }
}
