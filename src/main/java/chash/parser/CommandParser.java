package chash.parser;

import chash.command.AddCommand;
import chash.command.Command;
import chash.command.CommandTypeEnum;
import chash.command.DeleteCommand;
import chash.command.ExitCommand;
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
        return switch (cmd) {
        case CommandTypeEnum.BYE -> new ExitCommand();
        case CommandTypeEnum.LIST -> new ListCommand();
        case CommandTypeEnum.MARK -> new MarkCommand(args, true);
        case CommandTypeEnum.UNMARK -> new MarkCommand(args, false);
        case CommandTypeEnum.TODO -> new AddCommand(cmd, args);
        case CommandTypeEnum.DEADLINE -> new AddCommand(cmd, args);
        case CommandTypeEnum.EVENT -> new AddCommand(cmd, args);
        case CommandTypeEnum.DELETE -> new DeleteCommand(args);
        default -> throw new ChashException("Unsupported command");
        };
    }
}
