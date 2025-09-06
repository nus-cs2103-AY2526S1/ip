package chash.parser;

import chash.command.AddCommand;
import chash.command.Command;
import chash.command.CommandTypeEnum;
import chash.command.DeleteCommand;
import chash.command.ExitCommand;
import chash.command.FindCommand;
import chash.command.HelpCommand;
import chash.command.ListCommand;
import chash.command.MarkCommand;
import chash.exception.ChashException;

/** CHASH user data parser */
public class CommandParser {
    /**
     * todo
     * 
     * @param fullCommand desc
     * @return desc
     * @throws ChashException desc
     */
    public static Command parse(String fullCommand) throws ChashException {
        String[] tmp = fullCommand.split(" ", 2);

        CommandTypeEnum cmd;
        try {
            cmd = CommandTypeEnum.valueOf(tmp[0].toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ChashException("Invalid command: " + tmp[0]);
        }
        String args = (tmp.length > 1) ? tmp[1] : "";

        //Switch expression supported from Java 14>
        //Cannot use qualified name for switch with enum
        return switch (cmd) {
        case BYE -> new ExitCommand();
        case LIST -> new ListCommand();
        case MARK -> new MarkCommand(args, true);
        case UNMARK -> new MarkCommand(args, false);
        case TODO -> new AddCommand(cmd, args);
        case DEADLINE -> new AddCommand(cmd, args);
        case EVENT -> new AddCommand(cmd, args);
        case DELETE -> new DeleteCommand(args);
        case FIND -> new FindCommand(args);
        case HELP -> new HelpCommand();
        default -> throw new ChashException("Unsupported command");
        };
    }
}
