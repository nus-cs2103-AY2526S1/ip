package ip.commands;

/**
 * Class to parse inputs and call matching command
 */
public class Parser {

    /**
     * Returns Command matching user input
     * @param input User input
     * @return Command matching user input
     */
    public Command getCommand(String input) {
        return CommandType.findCommand(input).getCommand();
    }
}
