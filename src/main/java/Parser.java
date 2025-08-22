import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Checks if the integer given in the command from user is valid
     *
     * @param s String command from user
     * @param arrLen arrayLength of list of tasks
     * @return Boolean on whether the integer is true or not
     * @throws BobbyWasabiException
     */
    public static boolean isValidInteger(String s, int arrLen) throws BobbyWasabiException {
        String[] wordList = s.split(" ");

        // not valid command length
        if (wordList.length != 2) {
            throw new BobbyWasabiException("We only accept two inputs - the command and the integer");
        }


        // not a valid integer
        try {
            int indx = Integer.parseInt(wordList[1]);
            if (indx > arrLen) {
                throw new BobbyWasabiException("Index given in input is out of range, please try an index within the range of your list");
            }

        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("Please input an index following your command");
        }

        return true;
    }

    public static int parseCommandIndex(String userInput, int taskSize) throws BobbyWasabiException {
        if (Parser.isValidInteger(userInput, taskSize)) {
            String[] wordList = userInput.split(" ");
            int indx = Integer.parseInt(wordList[1]);
            return indx;
        } else {
            throw new BobbyWasabiException("Not valid index given as argument!");
        }
    }

    public static String parseTodo(String userInput) throws BobbyWasabiException {
        String[] descriptions = userInput.split("todo ");

        if (descriptions.length <= 1) {
            throw new BobbyWasabiException("Please provide a description for todo");
        }

        String description = descriptions[1];

        if (description.trim().isEmpty()) {
            throw new BobbyWasabiException("Please provide a description for todo");
        }

        return description;

    }

    public static String[] parseDeadline(String userInput) throws BobbyWasabiException {
        String[] bySplit = userInput.split("/by", 2);

        if (bySplit.length < 2) {
            throw new BobbyWasabiException("You did not provide the deadline!");
        }

        String[] descriptions = bySplit[0].split("deadline ");

        if (descriptions.length < 2) {
            throw new BobbyWasabiException("The deadline task description cannot be blank!");
        }

        if (descriptions[1].trim().isEmpty()) {
            throw new BobbyWasabiException("The deadline task description cannot be blank!");
        }

        String deadline = bySplit[1];

        if (deadline.trim().isEmpty()) {
            throw new BobbyWasabiException("The deadline cannot be blank!");
        }

        return new String[] {
                descriptions[1],
                deadline
        };

    }

    public static String[] parseEvent(String userInput) throws BobbyWasabiException {

        String[] fromSplit = userInput.split("/from", 2);

        // there was no /from command
        if (fromSplit.length < 2) {
            throw new BobbyWasabiException("You did not provide the start duration!");
        }

        String[] toSplit = fromSplit[1].split("/to", 2);

        // there was no /to command
        if (toSplit.length < 2) {
            throw new BobbyWasabiException("You did not provide the end duration!");
        }

        // starting description is empty
        if (toSplit[0].trim().isEmpty()) {
            throw new BobbyWasabiException("Your starting duration is blank!");
        } else if (toSplit[1].trim().isEmpty()) {
            throw new BobbyWasabiException("Your ending duration is blank!");
        }

        String[] descriptions = fromSplit[0].split("event ");

        if (descriptions.length < 2) {
            throw new BobbyWasabiException("The event description cannot be blank!");
        }


        if (descriptions[1].trim().isEmpty()) {
            throw new BobbyWasabiException("You did not provide a description of your event!");
        }

        return new String[] {
                descriptions[1],
                toSplit[0],
                toSplit[1]
        };

    }

    /**
     * Parses the given string into a LocalDateTime class
     *
     * @param date String representation of date
     * @return LocalDateTime class
     * @throws DateTimeParseException
     */
    public static LocalDateTime parseDateString(String date) throws BobbyWasabiException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim(), formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new BobbyWasabiException(e.getMessage());
        }
    }


}
