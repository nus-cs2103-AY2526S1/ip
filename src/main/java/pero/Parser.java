package pero;

import pero.command.Command;
import pero.command.CommandType;

/** Takes raw user input and parses and returns objects or index*/
public class Parser {

    /**
     * Parses user input and extracts index used for mark/unmark/delete
     *
     * @param input User input line.
     * @return Index of element of TaskList to be changed
     */
    public static int parseIndex(String input){
        //parseInt converts extracted string to integer
        //-1 to change to 0-based indexing
        int parsedIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        assert parsedIndex >= 0;
        return parsedIndex;
    }


    /**
     * Converts current user input line into command type
     *
     * @param input User input line.
     * @return Command type object
     */
    public static Command parseInputCommand(String input) {
        input = input.trim();

        // Tool commands
        if (input.equalsIgnoreCase("bye")) {
            return new Command(CommandType.BYE);
        } else if (input.equalsIgnoreCase("help")) {
            return new Command(CommandType.HELP);
        } else if (input.equalsIgnoreCase("list")) {
            return new Command(CommandType.LIST);

        // Split input and get second part, -1 to match 0 indexing
        } else if (input.matches("mark (\\d+)")) {
            int index = parseIndex(input);
            return new Command(CommandType.MARK, index, null);
        } else if (input.matches("unmark (\\d+)")) {
            int index = parseIndex(input);
            return new Command(CommandType.UNMARK, index, null);
        } else if (input.matches("delete (\\d+)")) {
            int index = parseIndex(input);
            return new Command(CommandType.DELETE, index, null);

        // Task type command
        } else if (input.toLowerCase().startsWith("todo")) {
            return new Command(CommandType.TODO, -1, input);
        } else if (input.toLowerCase().startsWith("deadline")) {
            return new Command(CommandType.DEADLINE, -1, input);
        } else if (input.toLowerCase().startsWith("event")) {
            return new Command(CommandType.EVENT, -1, input);


        } else if ((input.toLowerCase().startsWith("find"))) {
            return new Command(CommandType.FIND, -1, input.substring(4).trim());
            //to retrieve keyword after find

        // If unrecognised command
        } else {
            return new Command(CommandType.INVALID);
        }
    }
}
