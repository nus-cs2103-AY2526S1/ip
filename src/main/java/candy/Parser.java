package candy;

import exceptions.EditTaskErrorException;
import exceptions.NoEndException;
import exceptions.NoStartException;
import exceptions.NoTaskException;
import exceptions.InvalidInputException;
import exceptions.InvalidTimeInputException;
import tasks.TaskList;

/**
 * Represents a parser to parse user command
 */
public class Parser {

    private static boolean isConversationOver = false;

    /**
     * Sets the isConversationOver boolean
     *
     * @param newIsOver boolean to set
     */
    public static void setIsConversationOver(boolean newIsOver) {
        isConversationOver = newIsOver;
    }

    /**
     * Returns isConversationOver boolean
     */
    public static boolean getIsConversationOver() {
        return isConversationOver;
    }
    /**
     * Returns String of the reply by candy chatbot
     *
     * @param text String description of what user types
     * @param taskList List of task of current candy
     *
     * @throws EditTaskErrorException when task does not exist
     * @throws InvalidInputException when input did not start with the key words
     * @throws InvalidTimeInputException when time is not keyed in the right format
     * @throws NoTaskException when there is missing tasks
     * @throws NoStartException when there is missing start time
     * @throws NoEndException when there is missing end time
     * *
     */
    public static String parse(String text, TaskList taskList) {
        Command commandWord;
        try {
            commandWord = Command.parseCommand(text);
        } catch (InvalidInputException e) {
            return Ui.printError(e);
        }

        switch (commandWord) {
        case BYE:
            isConversationOver = true;
            return Ui.printBye();
        case LIST:
            return taskList.printList();
        case MARK:
            return taskList.doMark(text, true);
        case UNMARK:
            return taskList.doMark(text, false);
        case DELETE:
            return taskList.delete(text);
        case FIND:
            return taskList.findTask(text);
        case TODO:
            return taskList.addTask(text, "todo");
        case DEADLINE:
            return taskList.addTask(text, "deadline");
        case EVENT:
            return taskList.addTask(text, "event");
        case EDIT:
            return taskList.updateTask(text);
        }
        return null;
    }
}
