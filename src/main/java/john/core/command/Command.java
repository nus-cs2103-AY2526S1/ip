package john.core.command;

import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

import john.core.exception.ParseException;

public interface Command {
    CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException;
    default boolean isExit() {
        return false;
    }
}