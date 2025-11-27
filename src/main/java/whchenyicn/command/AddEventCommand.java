package whchenyicn.command;

import java.io.IOException;

import whchenyicn.exceptions.WhchenyicnException;
import whchenyicn.task.Event;
import whchenyicn.task.TaskList;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * A command to add an event task to tasklist.
 */
public class AddEventCommand extends Command {
    private String s;
    private static final String FROM_TOKEN = "/from";
    private static final String TO_TOKEN   = "/to";

    /**
     * Creates a command to add event task.
     *
     * @param s User input text after "event".
     */
    public AddEventCommand(String s) {
        this.s = s;
    }

    @Override
    public String execute(TaskList tlist, Ui ui, Storage storage) throws WhchenyicnException {
        checkFull(tlist);
        assertUi (tlist, ui, storage);

        if (s == null || s.isEmpty()) {
            throw new WhchenyicnException("Cannot be empty, event <desc> /from <start> /to <end>");
        }

        int f = s.indexOf(FROM_TOKEN);
        int t = s.indexOf(TO_TOKEN);
        if (f <= 0 || t <= f) {
            throw new WhchenyicnException("event <desc> /from <start> /to <end>");
        }
        String desc = s.substring(0, f).trim();

        if (desc.isEmpty()) {
            throw new WhchenyicnException("description cannot be empty");
        }

        String from = s.substring(f + 5, t).trim().replaceFirst("^:", "").trim();

        if (from.isEmpty()) {
            throw new WhchenyicnException("from date cannot be empty");
        }

        String to = s.substring(t + 3).trim().replaceFirst("^:", "").trim();

        if (to.isEmpty()) {
            throw new WhchenyicnException("to date cannot be empty");
        }

        tlist.add(new Event(desc, from, to));

        try {
            storage.save(tlist);
        } catch (IOException e) {
            ui.printError("Failed to save: " + e.getMessage());
        }

        return ui.printEventTask(tlist);
    }
}
