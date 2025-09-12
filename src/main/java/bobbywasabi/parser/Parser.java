package bobbywasabi.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobbywasabi.BobbyWasabi;
import bobbywasabi.client.Client;
import bobbywasabi.exceptions.BobbyWasabiException;


/**
 * Utility class for parsing user input into structured commands, task data, and client information.
 * <p>
 * Provides methods to extract descriptions, dates, indices, keywords, and client fields from user commands.
 */
public class Parser {

    /**
     * Checks whether a user input contains a valid integer index within the specified range.
     *
     * @param s       The full input string (e.g., "mark,2").
     * @param arrLen  The maximum valid index (usually the size of the list).
     * @return        {@code true} if the input contains a valid integer within range.
     * @throws BobbyWasabiException if the input format is invalid, missing, or the index is out of range.
     */
    public static boolean isValidInteger(String s, int arrLen) throws BobbyWasabiException {
        String[] wordList = s.split(",");

        if (wordList.length != 2) {
            throw new BobbyWasabiException("We only accept two inputs - the command and the integer");
        }

        try {
            int indx = Integer.parseInt(wordList[1].trim());
            if (indx > arrLen) {
                throw new BobbyWasabiException("Index given in input is out of range, "
                        + "please try an index within the range of your list");
            } else if (indx <= 0) {
                throw new BobbyWasabiException("Index given in input is out of range, "
                        + "please try an index within the range of your list");
            }

        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("Please input an index following your command");
        }

        return true;
    }

    /**
     * Parses and returns the integer index from a command input.
     *
     * @param userInput The full input string containing a command and an index (e.g., "delete,3").
     * @param taskSize  The maximum valid index (usually the size of the list).
     * @return          The parsed index as an integer.
     * @throws BobbyWasabiException if the input format is invalid or the index is out of range.
     */
    public static int parseCommandIndex(String userInput, int taskSize) throws BobbyWasabiException {
        if (!Parser.isValidInteger(userInput, taskSize)) {
            throw new BobbyWasabiException("Not valid index given as argument!");
        }

        String[] wordList = userInput.split(",");
        int indx = Integer.parseInt(wordList[1].trim());
        return indx;
    }

    /**
     * Extracts the keyword from a "find" command input.
     *
     * @param userInput The full input string (e.g., "find,book").
     * @return          The keyword to search for, trimmed of whitespace.
     * @throws BobbyWasabiException if the command is malformed, missing, or contains multiple keywords.
     */
    public static String parseFindCommand(String userInput) throws BobbyWasabiException {
        String[] wordList = userInput.split(",");

        if (wordList.length > 2) {
            throw new BobbyWasabiException("Please input a single keyword!");
        }

        if (wordList.length != 2) {
            throw new BobbyWasabiException("Please input the command and the keyword to find!");
        }

        String keyword = wordList[1];
        if (keyword.trim().isEmpty()) {
            throw new BobbyWasabiException("Please input a keyword!");
        }

        return keyword;
    }

    /**
     * Extracts the task description from a "todo" command.
     *
     * @param userInput The full input string (e.g., "todo read book").
     * @return          The task description, trimmed of whitespace.
     * @throws BobbyWasabiException if the description is missing or blank.
     */
    public static String parseTodo(String userInput) throws BobbyWasabiException {
        String description = userInput.substring("TODO".length() + 1).trim();

        if (description.isEmpty()) {
            throw new BobbyWasabiException("Please provide a description for todo");
        }

        return description;
    }

    /**
     * Parses a "deadline" command into description and deadline string.
     *
     * @param userInput The full input string (e.g., "deadline submit report,30/8/2025 1800").
     * @return          A string array of length 2: [task description, deadline string], both trimmed.
     * @throws BobbyWasabiException if the description or deadline is missing, blank, or malformed.
     */
    public static String[] parseDeadline(String userInput) throws BobbyWasabiException {
        String arguments = userInput.substring("DEADLINE".length() + 1).trim();
        String[] details = arguments.split(",");

        if (details.length != 2) {
            throw new BobbyWasabiException("Please provide correct arguments for deadline task!");
        }

        String description = details[0].trim();
        String deadline = details[1].trim();

        if (description.isEmpty()) {
            throw new BobbyWasabiException("The deadline task description cannot be blank!");
        }

        if (deadline.isEmpty()) {
            throw new BobbyWasabiException("The deadline cannot be blank!");
        }

        return new String[] {description, deadline};

    }

    /**
     * Parses an "event" command into description, start time, and end time.
     *
     * @param userInput The full input string (e.g., "event,project meeting,29/8/2025 1200,29/8/2025 1400").
     * @return          A string array of length 3: [description, start time, end time], all trimmed.
     * @throws BobbyWasabiException if description, start time, or end time is missing, blank, or malformed.
     */
    public static String[] parseEvent(String userInput) throws BobbyWasabiException {
        String arguments = userInput.substring("EVENT".length() + 1).trim();
        String[] details = arguments.split(",");

        if (details.length != 3) {
            throw new BobbyWasabiException("Please provide the correct number of arguments for event task!");
        }

        String description = details[0].trim();
        String eventStart = details[1].trim();
        String eventEnd = details[2].trim();

        if (description.isEmpty()) {
            throw new BobbyWasabiException("Your event description is blank!");
        } else if (eventStart.isEmpty()) {
            throw new BobbyWasabiException("Your starting duration is blank!");
        } else if (eventEnd.isEmpty()) {
            throw new BobbyWasabiException("Your ending duration is blank!");
        }

        return new String[] {
                description,
                eventStart,
                eventEnd
        };

    }

    /**
     * Parses an "ADDCLIENT" command into a {@link Client} object.
     *
     * @param userInput The full input string (e.g., "ADDCLIENT John Doe,12345678,30,Engineer,Life Insurance").
     * @return          A {@link Client} object with the parsed fields.
     * @throws BobbyWasabiException if required fields are missing, blank, or invalid (e.g., non-numeric age/contact).
     */
    public static Client parseAddClient(String userInput) throws BobbyWasabiException {
        String arguments = userInput.substring("ADDCLIENT".length() + 1).trim();
        String[] details = arguments.split(",");

        // check if number of arguments given is valid
        if (details.length != 4 && details.length != 5) {
            throw new BobbyWasabiException("Wrong number of arguments for ADDCLIENT command!");
        }

        String name = details[0].trim();
        String contactNumber = details[1].trim();
        String age = details[2].trim();
        String occupation = details[3].trim();
        String currentPolicies = details.length == 5
                ? details[4].trim()
                : "";

        // check if contact is valid
        if (!Parser.isStringAValidInteger(contactNumber)) {
            throw new BobbyWasabiException("Please provide a valid contact number for client!");
        }

        // check if age is valid
        if (!Parser.isStringAValidInteger(age)) {
            throw new BobbyWasabiException("Please provide a valid age for client!");
        }

        // check if name is not empty
        if (name.trim().isEmpty()) {
            throw new BobbyWasabiException("Please provide an actual name!");
        }

        // check if occupation is not empty
        if (occupation.trim().isEmpty()) {
            throw new BobbyWasabiException("Please provide an actual occupation!");
        }

        return new Client(name, contactNumber,
                Parser.getIntegerFromString(age),
                occupation, currentPolicies);
    }

    /**
     * Parses an "EDITCLIENT" command to extract the client index, field to edit, and new value.
     *
     * @param userInput  The full input string (e.g., "EDITCLIENT 1,name,Jane Doe").
     * @param clientSize The number of clients in the list (for index validation).
     * @return           A string array of length 3: [client index, field name (uppercased), new field value].
     * @throws BobbyWasabiException if the index, field, or value is invalid, out of range, or empty.
     */
    public static String[] parseEditClient(String userInput, int clientSize) throws BobbyWasabiException {
        String arguments = userInput.substring("EDITCLIENT".length() + 1).trim();
        String[] details = arguments.split(",");

        // check if number of arguments given is valid
        if (details.length != 3) {
            throw new BobbyWasabiException("Wrong number of arguments for EDITCLIENT command!");
        }

        int index = Parser.getIntegerFromString(details[0]) - 1;
        String field = details[1].trim().toUpperCase();
        String newFieldContent = details[2].trim();

        // check if index has a valid range
        if (index >= clientSize) {
            throw new BobbyWasabiException("List index for clients is out of range!");
        } else if (index < 0) {
            throw new BobbyWasabiException("List index for clients is out of range!");
        }

        // check if given argument is valid in accordance with field
        switch (field) {
        case ("CONTACTNUMBER"):
            if (!isStringAValidInteger(newFieldContent)) {
                throw new BobbyWasabiException("Please provide a valid contact number!");
            }
            break;
        case ("AGE"):
            if (!isStringAValidInteger(newFieldContent)) {
                throw new BobbyWasabiException("Please provide a valid age!");
            }
            break;
        case ("NAME"):
            if (newFieldContent.trim().isEmpty()) {
                throw new BobbyWasabiException("Name given is empty!");
            }
            break;
        case ("OCCUPATION"):
            if (newFieldContent.trim().isEmpty()) {
                throw new BobbyWasabiException("Occupation given is empty!");
            }
        case ("CURRENTPOLICIES"):
            break;
        default:
            throw new BobbyWasabiException("This is not a valid field to change for client!");
        }

        return new String[] {
                details[0],
                field,
                newFieldContent
        };

    }

    /**
     * Checks if a string represents a valid integer.
     *
     * @param input The string to check.
     * @return      {@code true} if the string can be parsed as an integer, {@code false} otherwise.
     */
    public static boolean isStringAValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Converts a string to an integer.
     *
     * @param input The string to convert.
     * @return      The integer value if parseable, or 0 if invalid.
     */
    public static int getIntegerFromString(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Parses a date string into a {@link LocalDateTime} object.
     * <p>
     * Expected format: "d/M/yyyy HHmm" (e.g., "22/8/2025 1930"). Leading/trailing whitespace is ignored.
     *
     * @param date The date string to parse.
     * @return     The corresponding {@link LocalDateTime} object.
     * @throws BobbyWasabiException if the date format is invalid or parsing fails.
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

    public static LocalDateTime[] parseEventDateString(String start, String end) throws BobbyWasabiException {

        LocalDateTime startTime = Parser.parseDateString(start);
        LocalDateTime endTime = Parser.parseDateString(end);

        // check if end time is before or the same as start time
        if (endTime.isBefore(startTime)) {
            throw new BobbyWasabiException("End time cannot be earlier than start time!");
        } else if (endTime.isEqual(startTime)) {
            throw new BobbyWasabiException("End time cannot be the same as start time!");
        }

        return new LocalDateTime[] {
                startTime,
                endTime
        };
    }

    /**
     * Parses the first word of user input as a {@link BobbyWasabi.Command} enum.
     * <p>
     * Input is trimmed of whitespace and converted to uppercase before mapping to a command.
     *
     * @param userInput The full input string (e.g., "todo read book").
     * @return          The corresponding {@link BobbyWasabi.Command}. Returns {@link BobbyWasabi.Command#OTHERS}
     *                  if the input does not match any known command.
     */
    public static BobbyWasabi.Command parseCommand(String userInput) {
        return BobbyWasabi.Command.toCommand(userInput.split(",")[0]);
    }


}
