package uxie.commands;

import java.util.List;

import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Command that finds Tasks matching String.
 *
 * @author junyan-k
 */
public class FindCommand extends Command {

    /** String to search for. */
    private String search;

    /**
     * Generates a FindCommand with search String.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Prints list of Tasks matching search String.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Integer> resultIndices = tasks.findContainingString(search);
        if (resultIndices.isEmpty()) {
            ui.uxieAppendln("I can't find any tasks mentioning that.");
        } else {
            ui.uxieAppendln("Here are the matching tasks:\n");

            // verify that indices are within task list
            for (int index: resultIndices) {
                assert 0 < index && index < tasks.getSize()
                        : "FindCommand::execute: index out of bounds";
            }

            try {
                for (int index: resultIndices) {
                    ui.uxieAppendln(String.format("%s. %s\n", index + 1, tasks.getTask(index)));
                }
            } catch (UxieIllegalOpException e) {
                ui.appendException(e); // this should never happen as indices are produced by TaskList
            }
        }
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if provided Object is equal to this FindCommand.
     * Two FindCommands are equal if their search strings are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            return ((FindCommand) o).search.equals(this.search);
        } else {
            return false;
        }
    }

}
