package tension.helper;

import java.util.ArrayList;
import java.util.List;

import tension.TensionException;

/**
 * Provides methods to parse information given dueDate
 * the user
 *
 */
public class Parser {

    /**
     * Returns parser object to parse commands
     */
    public Parser() {}

    /**
     * Returns relevant information based on command given
     *
     * @param input a string that starts with list, mark, unmark,
     *                delete, todo, deadline, event
     * @return tension.helper.Command
     */
    public Command parse(String input) throws TensionException {
        int index = -1;
        boolean isMarked = false;
        String[] parts = input.split("\\s+");
        String firstString = parts[0];
        Command command;
        if (startsWithValidCommandWord(firstString)) {
            if (firstString.equals("mark") || parts[0].equals("unmark")) {
                isMarked = firstString.equals("mark");
                index = Integer.parseInt(parts[1]) - 1;
            } else if (firstString.equals("delete") || parts[0].equals("tag")) {
                index = Integer.parseInt(parts[1]) - 1;
            }
            command = new Command(index, firstString, isMarked, input);
        } else {
            throw new TensionException("Invalid command: start with \"bye\", \"find\", \"list\", \"mark\", \"unmark\""
                                        + "\"delete\", \"todo\", \"deadline\", \"event\", \"tag\"");
        }
        return command;
    }

    /**
     * parses string to an arraylistString
     */
    public ArrayList<String> parseStringToArrayList(String listStringWithBrackets) {
        assert listStringWithBrackets.charAt(0) == '[';
        int length = listStringWithBrackets.length();
        assert listStringWithBrackets.charAt(length - 1) == ']';
        String cleanString = listStringWithBrackets.substring(1, length - 1);
        String[] stringArray = cleanString.split(", ");
        return new ArrayList<>(List.of(stringArray));
    }

    private static boolean startsWithValidCommandWord(String s) {
        String[] valid = new String[]{"bye", "find", "list", "mark", "unmark",
                                      "delete", "todo", "deadline", "event", "tag"};
        for (String v : valid) {
            if (s.equals(v)) {
                return true;
            }
        }
        return false;
    }


}
