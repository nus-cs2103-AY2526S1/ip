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
 * Provides static methods to extract task descriptions, deadlines, event dates, client fields,
 * command indices, and keywords from user commands. Also validates integers, contact numbers,
 * and date strings.
 */
public class Parser {

    /**
     * Validates whether the given input contains a valid integer index within the specified range.
     *
     * @param s      The full input string (e.g., "mark,2").
     * @param arrLen The maximum valid index (usually the size of the list).
     * @return       {@code true} if the input contains a valid integer within range.
     * @throws BobbyWasabiException If the input format is invalid, missing, or the index is out of range.
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
     * @throws BobbyWasabiException If the input format is invalid or the index is out of range.
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
     * @throws BobbyWasabiException If the command is malformed, missing, or contains multiple keywords.
     */
    public static String parseFindCommand(String userInput) throws BobbyWasabiException {
        String[] wordList = userInput.split(",");

        if (wordList.length > 2) {
            throw new BobbyWasabiException("Please input a single keyword!");
        }

        if (wordList.length != 2) {
            throw new BobbyWasabiException("Please input the command and the keyword to find!");
        }

        String keyword = wordList[1].trim();
        if (keyword.trim().isEmpty()) {
            throw new BobbyWasabiException("Please input a keyword!");
        }

        return keyword;
    }

    /**
     * Extracts the task description from a "todo" command input.
     *
     * @param userInput The full input string (e.g., "todo,read book").
     * @return          The task description, trimmed of whitespace.
     * @throws BobbyWasabiException If the description is missing or blank.
     */
    public static String parseTodo(String userInput) throws BobbyWasabiException {
        String[] wordList = userInput.split(",");

        if (wordList.length != 2) {
            throw new BobbyWasabiException("Please input the command and the description for todo task!");
        }

        String description = wordList[1].trim();

        if (description.isEmpty()) {
            throw new BobbyWasabiException("Please provide a description for todo");
        }

        return description;
    }

    /**
     * Parses a "deadline" command input into task description and deadline string.
     *
     * @param userInput The full input string (e.g., "deadline,submit report,30/8/2025 1800").
     * @return          A string array of length 2: [task description, deadline string], both trimmed.
     * @throws BobbyWasabiException If the description or deadline is missing, blank, or malformed.
     */
    public static String[] parseDeadline(String userInput) throws BobbyWasabiException {
        String[] wordList = userInput.split(",");

        if (wordList.length != 3) {
            throw new BobbyWasabiException("Please provide correct arguments for deadline task!");
        }

        String description = wordList[1].trim();
        String deadline = wordList[2].trim();

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
        String[] wordList = userInput.split(",");

        if (wordList.length != 4) {
            throw new BobbyWasabiException("Please provide the correct number of arguments for event task!");
        }

        String description = wordList[1].trim();
        String eventStart = wordList[2].trim();
        String eventEnd = wordList[3].trim();

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
     * Parses an "ADDCLIENT" command input into a {@link Client} object.
     *
     * @param userInput The full input string (e.g., "ADDCLIENT John Doe,12345678,30,Engineer,Life Insurance").
     * @return          A {@link Client} object with the parsed fields.
     * @throws BobbyWasabiException If required fields are missing, blank, or invalid (e.g., non-numeric age/contact).
     */
    public static Client parseAddClient(String userInput) throws BobbyWasabiException {
        String[] clientDetails = Parser.splitAddClientInput(userInput);

        String name = clientDetails[0];
        String contactNumber = clientDetails[1];
        String age = clientDetails[2];
        String occupation = clientDetails[3];
        String currentPolicies = clientDetails[4];

        Parser.validateAddClientArgumments(name, contactNumber, age, occupation);

        return new Client(name, contactNumber,
                Parser.getIntegerFromString(age),
                occupation, currentPolicies);
    }

    /**
     * Validates the arguments of an ADDCLIENT command.
     * <p>
     * Checks that contact number and age are numeric and that name/occupation are not empty.
     *
     * @param name          The client's name.
     * @param contactNumber The client's contact number.
     * @param age           The client's age.
     * @param occupation    The client's occupation.
     * @throws BobbyWasabiException If any field is invalid or blank.
     */
    private static void validateAddClientArgumments(String name, String contactNumber,
                                                   String age, String occupation) throws BobbyWasabiException {
        // check if contact is valid
        if (!Parser.isContactAValidNumber(contactNumber)) {
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
    }

    /**
     * Splits and validates the arguments for an ADDCLIENT command.
     * <p>
     * Expected format: "ADDCLIENT name,contact,age,occupation[,currentPolicies]".
     * The current policies field is optional.
     *
     * @param input The full ADDCLIENT command string.
     * @return      An array of 5 strings: [name, contactNumber, age, occupation, currentPolicies].
     *              currentPolicies will be an empty string if not provided.
     * @throws BobbyWasabiException If the number of arguments is invalid.
     */
    private static String[] splitAddClientInput(String input) throws BobbyWasabiException {
        String[] wordList = input.split(",");

        // check if number of arguments given is valid
        if (wordList.length != 5 && wordList.length != 6) {
            throw new BobbyWasabiException("Wrong number of arguments for ADDCLIENT command!");
        }

        String name = wordList[1].trim();
        String contactNumber = wordList[2].trim();
        String age = wordList[3].trim();
        String occupation = wordList[4].trim();
        String currentPolicies = wordList.length == 6
                ? wordList[5].trim()
                : "";

        return new String[] {name, contactNumber, age, occupation, currentPolicies};
    }

    /**
     * Parses an "EDITCLIENT" command input into index, field, and new value.
     *
     * @param userInput  The full input string (e.g., "EDITCLIENT 1,name,Jane Doe").
     * @param clientSize The number of clients in the list (for index validation).
     * @return           A string array of length 3: [client index, field name (uppercased), new field value].
     * @throws BobbyWasabiException If the index, field, or value is invalid, out of range, or empty.
     */
    public static String[] parseEditClient(String userInput, int clientSize) throws BobbyWasabiException {
        String[] args = Parser.splitEditClientArgs(userInput);
        int index = parseAndValidateClientIndex(args[1], clientSize);
        String field = args[2].trim().toUpperCase();
        String newFieldContent = args[3].trim();

        Parser.validateFieldContent(field, newFieldContent);

        return new String[] {
            String.valueOf(index),
            field,
            newFieldContent
        };
    }

    /**
     * Splits and validates the arguments for an EDITCLIENT command.
     * <p>
     * Expected format: "EDITCLIENT index,field,newValue".
     *
     * @param userInput The full EDITCLIENT command string.
     * @return          An array of 4 strings: [command, index, field, newValue].
     * @throws BobbyWasabiException If the number of arguments is not exactly 4.
     */
    private static String[] splitEditClientArgs(String userInput) throws BobbyWasabiException {
        String[] args = userInput.split(",");
        if (args.length != 4) {
            throw new BobbyWasabiException("Wrong number of arguments for EDITCLIENT command!");
        }
        return args;
    }

    /**
     * Parses and validates the client index for an EDITCLIENT command.
     * <p>
     * Ensures the index is numeric, positive, and within the bounds of the client list.
     *
     * @param rawIndex   The index string from the user input.
     * @param clientSize The current number of clients in the list.
     * @return           The validated index as an integer (1-based).
     * @throws BobbyWasabiException If there are no clients, or the index is out of range or invalid.
     */
    private static int parseAndValidateClientIndex(String rawIndex, int clientSize) throws BobbyWasabiException {
        if (clientSize <= 0) {
            throw new BobbyWasabiException("There are no clients in your contact list to edit!");
        }

        int index = getIntegerFromString(rawIndex.trim());
        if (index <= 0) {
            throw new BobbyWasabiException("List index for clients cannot be 0 or lesser!");
        }
        if (index > clientSize) {
            throw new BobbyWasabiException("List index for clients is out of range!");
        }
        return index;
    }

    /**
     * Validates the new field content for an EDITCLIENT command based on the specified field.
     * <p>
     * Supported fields: NAME, CONTACTNUMBER, AGE, OCCUPATION, CURRENTPOLICIES.
     *
     * @param field   The field name (already uppercased).
     * @param content The new field value.
     * @throws BobbyWasabiException If the field is not recognized or the value is invalid.
     */
    private static void validateFieldContent(String field, String content) throws BobbyWasabiException {
        switch (field) {
        case "CONTACTNUMBER":
            if (!isContactAValidNumber(content)) {
                throw new BobbyWasabiException("Please provide a valid contact number!");
            }
            break;
        case "AGE":
            if (!isStringAValidInteger(content)) {
                throw new BobbyWasabiException("Please provide a valid age!");
            }
            break;
        case "NAME":
            if (content.isEmpty()) {
                throw new BobbyWasabiException("Name given is empty!");
            }
            break;
        case "OCCUPATION":
            if (content.isEmpty()) {
                throw new BobbyWasabiException("Occupation given is empty!");
            }
            break;
        case "CURRENTPOLICIES":
            // no validation needed for now
            break;
        default:
            throw new BobbyWasabiException("This is not a valid field to change for client!");
        }
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
     * Checks if a string represents a valid contact number.
     * <p>
     * Valid contact number: numeric string of exactly 8 digits (Singapore format).
     *
     * @param contact The string to check.
     * @return        {@code true} if valid, {@code false} otherwise.
     */
    public static boolean isContactAValidNumber(String contact) {
        if (!isStringAValidInteger(contact)) {
            return false;
        }

        // check if contact number is 8 digits which matches Singaporean contact number format
        if (contact.length() != 8) {
            return false;
        }

        return true;
    }

    /**
     * Converts a string to an integer.
     *
     * @param input The string to convert.
     * @return      The integer value.
     * @throws BobbyWasabiException If the string is not a valid integer.
     */
    public static int getIntegerFromString(String input) throws BobbyWasabiException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BobbyWasabiException("String given is not a valid integer!");
        }
    }

    /**
     * Parses a date string into a {@link LocalDateTime} object.
     * <p>
     * Expected format: "d/M/yyyy HHmm" (e.g., "22/8/2025 1930"). Leading/trailing whitespace is ignored.
     *
     * @param date The date string to parse.
     * @return     The corresponding {@link LocalDateTime} object.
     * @throws BobbyWasabiException If the date format is invalid or parsing fails.
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

    /**
     * Parses start and end date strings for an event into {@link LocalDateTime} objects.
     *
     * @param start The start date string.
     * @param end   The end date string.
     * @return      An array of two {@link LocalDateTime} objects: [startTime, endTime].
     * @throws BobbyWasabiException If parsing fails or if the end time is before or equal to the start time.
     */
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
     * @param userInput The full input string (e.g., "todo,read book").
     * @return          The corresponding {@link BobbyWasabi.Command}, or {@link BobbyWasabi.Command#OTHERS} if unknown.
     */
    public static BobbyWasabi.Command parseCommand(String userInput) {
        String command = userInput.split(",")[0].trim();
        return BobbyWasabi.Command.toCommand(command);
    }


}
