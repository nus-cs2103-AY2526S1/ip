package scribbles.command;

import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to display help guide.
 */
public class HelpCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        StringBuilder commands = new StringBuilder("Here is a list of commands you can use:\n");
        String[] commandList = Command.getCommandList();
        for (String command : commandList) {
            if (command.equals("help")) {
                continue;
            }
            commands.append("    %s\n".formatted(command));
        }
        return commands.toString();
    }
}
