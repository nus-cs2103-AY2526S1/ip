package lux.commands;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.exception.LuxException;
import lux.storage.Storage;
import lux.ui.Ui;

/**
 * Command to create a custom alias mapping from a short alias to a full
 * command string.
 *
 * <p>The arguments are expected to contain two tokens: the alias name and
 * the expanded command (separated by whitespace). For example: "alias ls
 * list".
 */
public class AliasCommand extends Command {
    private String arguments;

    public AliasCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Add the alias to the provided AliasList and return a confirmation
     * message.
     *
     * @throws LuxException if the arguments don't contain both alias and
     *                      command
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasList aliases) throws LuxException {
        String[] parts = arguments.split(" ", 2);
        if (parts.length < 2) {
            throw new LuxException("Alias command requires two arguments: <alias> <command>");
        }

        String alias = parts[0].trim();
        String command = parts[1].trim();
        aliases.add(alias, command);

        return ui.addAlias(alias, command);
    }

    public boolean isExit() {
        return false;
    }
}
