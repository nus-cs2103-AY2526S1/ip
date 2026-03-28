package tweety.commands;

import tweety.exceptions.EmtpyDescriptionException;
import tweety.exceptions.TweetyException;
import tweety.exceptions.InvalidInputFormatException;

/**
 * Parses user input strings into Command objects for the Tweety application.
 * Handles various command types including mark, unmark, delete, list, bye, find, todo, deadline, and event commands.
 */
public class Parser {

    private static final int TODO_COMMAND_LENGTH = 4;
    private static final int EVENT_COMMAND_LENGTH = 5;
    private static final int DEADLINE_COMMAND_LENGTH = 8;
    private static final int FIND_COMMAND_LENGTH = 4;
    private static final int EDIT_COMMAND_LENGTH = 4;

    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String FIND_COMMAND = "find";
    private static final String EDIT_COMMAND = "edit";

    /**
     * Parses the user input string and returns the corresponding Command object.
     * Identifies command type and delegates to appropriate parsing methods.
     *
     * @param userInput The raw user input string to parse.
     * @return A Command object representing the parsed user input.
     * @throws TweetyException If the command format is invalid.
     */
    public Command parseCommand(String userInput) throws TweetyException {
        validateInput(userInput);
        String command = extractCommand(userInput);
        return createCommand(command, userInput);
    }

    /**
     * Helper to validates the user input to ensure it is neither null nor empty after trimming.
     * Asserts that the input is valid before processing it further.
     *
     * @param userInput The raw user input string to validate.
     * @throws AssertionError If the userInput is null or empty after trimming.
     */
    private void validateInput(String userInput) {
        assert userInput != null : "userInput should not be null";
        assert !userInput.trim().isEmpty() : "userInput should not be empty after trimming";
    }

    /**
     * Helper to extract the command type from the user input string.
     * The command is the first word in the input before any spaces.
     *
     * @param userInput The raw user input string.
     * @return A string representing the command extracted from the user input.
     */
    private String extractCommand(String userInput) {
        return userInput.trim().split(" ")[0];
    }

    /**
     * Helper to create a corresponding Command object based on the extracted command string.
     * Delegates to specific parsing methods depending on the command type.
     *
     * @param command The command type extracted from the user input.
     * @param userInput The raw user input string.
     * @return A Command object corresponding to the command type.
     * @throws TweetyException If the command is invalid or cannot be parsed.
     */
    private Command createCommand(String command, String userInput) throws TweetyException {
        switch (command) {
            case MARK_COMMAND:
                return parseMarkCommand(userInput);
            case UNMARK_COMMAND:
                return parseUnmarkCommand(userInput);
            case DELETE_COMMAND:
                return parseDeleteCommand(userInput);
            case LIST_COMMAND:
                return new ListCommand();
            case BYE_COMMAND:
                return new ExitCommand();
            case TODO_COMMAND:
                return parseTodoCommand(userInput);
            case DEADLINE_COMMAND:
                return parseDeadlineCommand(userInput);
            case EVENT_COMMAND:
                return parseEventCommand(userInput);
            case FIND_COMMAND:
                return parseFindCommand(userInput);
            case EDIT_COMMAND:
                return parseEditCommand(userInput);
            default:
                throw new TweetyException("Oopsie, hon! Invalid command. Please try again.");
        }
    }

    /**
     * Parses a mark command and extracts the task number.
     * Expected format: "mark [task_number]"
     *
     * @param userInput The user input string containing the mark command.
     * @return A Command object with MARK type and task number set.
     * @throws TweetyException If the task number is invalid or missing.
     */
    private Command parseMarkCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        int taskNumber = getTaskNumber(userInput);
        return new MarkCommand(taskNumber);
    }

    /**
     * Parses an edit command and extracts the task number, field, and new value.
     * Expected format: "edit [task_number] [field] [new_value]"
     *
     * @param userInput The user input string containing the edit command.
     * @return An EditCommand object with the task number, field and new value set.
     * @throws TweetyException If the task number is invalid, format is incorrect or required parameters are missing.
     */
    private Command parseEditCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        assert userInput.startsWith("edit") : "userInput should start with 'edit'";

        String descriptions = getDescription(userInput, EDIT_COMMAND_LENGTH);
        String[] parts = descriptions.split("\\s+", 3);

        validateEditFormat(parts);

        int taskNumber = parseTaskNumberFromParts(parts[0]);
        String field = parts[1].trim();
        String newValue = parts[2].trim();

        return new EditCommand(taskNumber, field, newValue);
    }

    /**
     * Helper to validate the format of the edit command by ensuring it has at least 3 parts:
     * task number, field, and new value.
     *
     * @param parts An array of strings containing the parts of the edit command.
     * @throws TweetyException If the format is incorrect (fewer than 3 parts).
     */
    private void validateEditFormat(String[] parts) throws TweetyException {
        if (parts.length < 3) {
            throw new InvalidInputFormatException("edit <task number> <field> <newValue>\n" +
                    "Valid fields are : description, deadline, from, to. ", EDIT_COMMAND);
        }
    }

    /**
     * Helper to parse the task number from the given string and converts it to an integer.
     *
     * @param taskNumberStr The string representing the task number.
     * @return The parsed task number as an integer.
     * @throws TweetyException If the task number is not a valid integer.
     */
    private int parseTaskNumberFromParts(String taskNumberStr) throws TweetyException {
        try {
            return Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException("Task number must be a valid integer", EDIT_COMMAND);
        }
    }

    /**
     * Parses an unmark command and extracts the task number.
     * Expected format: "unmark [task_number]"
     *
     * @param userInput The user input string containing the unmark command.
     * @return A Command object with UNMARK type and task number set.
     * @throws TweetyException If the task number is invalid or missing.
     */
    private Command parseUnmarkCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        int taskNumber = getTaskNumber(userInput);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Parses a delete command and extracts the task number.
     * Expected format: "delete [task_number]"
     *
     * @param userInput The user input string containing the delete command.
     * @return A Command object with DELETE type and task number set.
     * @throws TweetyException If the task number is invalid or missing.
     */
    private Command parseDeleteCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        int taskNumber = getTaskNumber(userInput);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Helper method to extract the descriptions of task
     *
     * @param userInput The user input string
     * @return A string that is trimmed to only include descriptions of the task
     *
     */
    private String getDescription(String userInput, int commandLength) {
        return userInput.substring(commandLength).trim();
    }

    /**
     * Parses a todo command and extracts the task description.
     * Expected format: "todo [description]"
     *
     * @param userInput The user input string containing the todo command.
     * @return A Command object with TODO type and description set.
     * @throws TweetyException If the description is empty or missing.
     */
    private Command parseTodoCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";

        String description = getDescription(userInput, TODO_COMMAND_LENGTH);
        if (description.isEmpty()) {
            throw new EmtpyDescriptionException("todo borrow book", TODO_COMMAND);
        }

        return new TodoCommand(description);
    }

    /**
     * Helper to validate that the description of the deadline command is not empty.
     * Throws an exception if the description is empty.
     *
     * @param description The description part of the user input for the deadline command.
     * @param example A sample of valid input for the deadline command.
     * @param command The name of the command being validated.
     * @throws TweetyException If the description is empty.
     */
    private void validateDescriptionNotEmpty(String description, String example, String command) throws TweetyException {
        if (description.isEmpty()) {
            throw new InvalidInputFormatException(example, command);
        }
    }

    /**
     * Parses a deadline command and extracts description and due date.
     * Expected format: "deadline [description] /by [date]"
     *
     * @param userInput The user input string containing the deadline command.
     * @return A Command object with DEADLINE type, description, and due date set.
     * @throws TweetyException If the format is invalid, description is empty, or /by is missing.
     */
    private Command parseDeadlineCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        assert userInput.startsWith("deadline") : "userInput should start with 'deadline'";

        validateDeadlineLength(userInput);
        String descriptions = getDescription(userInput, DEADLINE_COMMAND_LENGTH);
        validateDeadlineFormat(userInput);

        String[] parts = descriptions.split("/by", 2);
        String description = parts[0].trim();
        validateDescriptionNotEmpty(description, "deadline borrow book /by yyyy-mm-dd", DEADLINE_COMMAND);

        String by = parts[1].trim();
        return new DeadlineCommand(description, by);
    }

    /**
     * Helper to validate the length of the deadline input to ensure that the description is not too short.
     * Throws an exception if the description is too short (8 characters or fewer).
     *
     * @param userInput The raw user input string containing the deadline command.
     * @throws TweetyException If the input description is too short.
     */
    private void validateDeadlineLength(String userInput) throws TweetyException {
        if (userInput.length() <= 8) {
            throw new EmtpyDescriptionException("deadline borrow book /by yyyy-mm-dd", DEADLINE_COMMAND);
        }
    }

    /**
     * Helper to validate the format of the deadline input to ensure that it contains the "/by" keyword.
     * Throws an exception if the input does not contain "/by" to indicate the deadline date.
     *
     * @param userInput The raw user input string containing the deadline command.
     * @throws TweetyException If the input does not contain "/by" in the expected format.
     */
    private void validateDeadlineFormat(String userInput) throws TweetyException {
        if (!userInput.contains("/by")) {
            throw new InvalidInputFormatException("deadline borrow book /by yyyy-mm-dd", DEADLINE_COMMAND);
        }
    }


    /**
     * Parses an event command and extracts description, start time, and end time.
     * Expected format: "event [description] /from [start_time] /to [end_time]"
     *
     * @param userInput The user input string containing the event command.
     * @return A Command object with EVENT type, description, start time, and end time set.
     * @throws TweetyException If the format is invalid, description is empty, or /from or /to is missing.
     */
    private Command parseEventCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        assert userInput.startsWith("event") : "userInput should start with 'event'";

        validateEventLength(userInput);
        String descriptions = getDescription(userInput, EVENT_COMMAND_LENGTH);
        validateEventFormat(userInput);

        String[] parts = descriptions.split("/from|/to");
        String description = parts[0].trim();
        validateDescriptionNotEmpty(description, "event project meeting /from Mon 2pm /to 4pm", EVENT_COMMAND);

        String from = parts[1].trim();
        String to = parts[2].trim();

        return new EventCommand(description, from, to);
    }

    /**
     * Helper to validate the length of the event input to ensure that the description is not too short.
     * Throws an exception if the description is too short (5 characters or fewer).
     *
     * @param userInput The raw user input string containing the event command.
     * @throws TweetyException If the input description is too short.
     */
    private void validateEventLength(String userInput) throws TweetyException {
        if (userInput.length() <= 5) {
            throw new EmtpyDescriptionException("event project meeting /from Mon 2pm /to 4pm", EVENT_COMMAND);
        }
    }

    /**
     * Helper to validate the format of the event input to ensure that it contains the "/from" and "/to" keywords.
     * Throws an exception if the input does not contain both "/from" and "/to" to indicate the event's time range.
     *
     * @param userInput The raw user input string containing the event command.
     * @throws TweetyException If the input does not contain both "/from" and "/to" in the expected format.
     */
    private void validateEventFormat(String userInput) throws TweetyException {
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new InvalidInputFormatException("event project meeting /from Mon 2pm /to 4pm", EVENT_COMMAND);
        }
    }

    /**
     * Parses a find command and extracts the search keyword.
     * Expected format: "find [keyword]"
     *
     * @param userInput The user input string containing the find command.
     * @return A FindCommand object with the search keyword set.
     * @throws TweetyException If the keyword is empty or missing.
     */
    private Command parseFindCommand(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";
        String keyword = getDescription(userInput, FIND_COMMAND_LENGTH);

        if (keyword.isEmpty()) {
            throw new InvalidInputFormatException("find read", FIND_COMMAND);
        }

        return new FindCommand(keyword);
    }

    /**
     * Extracts and parses the task number from user input.
     * Assumes the task number follows the command keyword separated by a space.
     *
     * @param userInput The user input string containing a command with task number.
     * @return The parsed task number as an integer.
     * @throws TweetyException If the task number is not a valid integer or is missing.
     */
    private int getTaskNumber(String userInput) throws TweetyException {
        assert userInput != null : "userInput should not be null";

        try {
            String taskNumber = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumber);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TweetyException("Invalid command format. Please specify a valid task number.");
        }
    }
}
