package command;

import java.util.ArrayList;
import java.util.List;

import ui.Lmbd;

/**
 * Represents a command to display helpful information about available commands.
 * It can list all commands with their descriptions or provide details for a
 * specific command.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", 0, "Gives a helpful description of a command");
    }

    @Override
    String run(Lmbd lmbd, String[] args) {
        List<String> lines = new ArrayList<>();
        if (args.length == 0) {
            lines.add("Here are a list of commands:");
            for (Command cmd : lmbd.getCommands()) {
                lines.add(String.format("%s - %s", cmd.getCmd(), cmd.getHelpText()));
            }
            return String.join("\n", lines);
        } else if (lmbd.isCommand(args[0])) {
            return lmbd.getCommand(args[0]).getHelpText();
        } else {
            return String.format("Command %s not found", args[0]);
        }
    }
}
