package buddy.parser;

import buddy.command.AddDeadlineCommand;
import buddy.command.AddEventCommand;
import buddy.command.AddTodoCommand;
import buddy.command.ByeCommand;
import buddy.command.Command;
import buddy.command.DeleteCommand;
import buddy.command.FindCommand;
import buddy.command.HelpCommand;
import buddy.command.ListCommand;
import buddy.command.MarkCommand;
import buddy.command.SaveCommand;
import buddy.command.UnmarkCommand;
import buddy.common.Commands;
import buddy.exception.BuddyException;
import buddy.exception.UnknownCommandException;
import buddy.model.TaskList;
import buddy.storage.Storage;
import buddy.ui.Ui;

/*
  Re-use notice:
  I switched this parsing logic to use cmd.substring(...) after observing this approach in other implementations.
  Previously I rebuilt the command using StringBuilder. No code was copied; only the idea.
 */

/**
 * Parses user commands and applies them to the task list, producing user-visible output
 */

public class Parser {

    /**
     * Parses and executes a single command line.
     * @return true if the app should exit, false otherwise.
     */

    public static boolean handle(String input, TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        String trimmed = input.trim();
        assert trimmed != null : "Command cannot be null";
        if (trimmed.isEmpty()) {
            return false;
        }

        // Split into keyword & rest
        String[] parts = trimmed.split("\\s+", 2);
        String keyword = parts[0].toLowerCase();
        String rest = (parts.length > 1) ? parts[1].trim() : "";

        // Aliases
        if (keyword.equals(Commands.TODO_ALIAS)) {
            keyword = Commands.TODO;
        } else if (keyword.equals(Commands.MARK_ALIAS)) {
            keyword = Commands.MARK;
        } else if (keyword.equals(Commands.UNMARK_ALIAS)) {
            keyword = Commands.UNMARK;
        } else if (keyword.equals(Commands.DELETE_ALIAS))   {
            keyword = Commands.DELETE;
        } else if (keyword.equals(Commands.DEADLINE_ALIAS)) {
            keyword = Commands.DEADLINE;
        } else if (keyword.equals(Commands.EVENT_ALIAS)) {
            keyword = Commands.EVENT;
        } else if (keyword.equals(Commands.FIND_ALIAS)) {
            keyword = Commands.FIND;
        } else if (keyword.equals(Commands.SAVE_ALIAS)) {
            keyword = Commands.SAVE;
        } else if (keyword.equals(Commands.LIST_ALIAS)) {
            keyword = Commands.LIST;
        } else if (keyword.equals(Commands.BYE_ALIAS)) {
            keyword = Commands.BYE;
        } else if (keyword.equals(Commands.HELP_ALIAS)) {
            keyword = Commands.HELP;
        }

        Command cmd;
        switch (keyword) {
            case Commands.BYE:
                cmd = new ByeCommand();
                break;
            case Commands.LIST:
                cmd = new ListCommand();
                break;
            case Commands.SAVE:
                cmd = new SaveCommand();
                break;
            case Commands.TODO:
                cmd = new AddTodoCommand(rest);
                break;
            case Commands.DEADLINE:
                cmd = AddDeadlineCommand.from(rest);
                break;
            case Commands.EVENT:
                cmd = AddEventCommand.from(rest);
                break;
            case Commands.MARK:
                cmd = new MarkCommand(rest, tasks.getSize());
                break;
            case Commands.UNMARK:
                cmd = new UnmarkCommand(rest, tasks.getSize());
                break;
            case Commands.DELETE:
                cmd = new DeleteCommand(rest, tasks.getSize());
                break;
            case Commands.FIND:
                cmd = new FindCommand(rest);
                break;
            case Commands.HELP:
                cmd = new HelpCommand();
                break;
            default: throw new UnknownCommandException();
        }

        if (cmd.isExit()) {
            storage.save(tasks.toList());
            ui.showGoodbye();
            return true;
        } else {
            cmd.execute(tasks, ui, storage);
            return false;
        }
    }
}
