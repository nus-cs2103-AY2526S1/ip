package horus;

import horus.tasks.TaskList;
import horus.tasks.InvalidInputException;


/**
 * A class that is able to parse input strings and execute relevant commands on this.taskList.
 */
public class Parser {
    TaskList taskList;

    /**
     * Initializes parser with taskList.
     *
     * @param taskList The TaskList on which future commands are executed.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses inputStr for commands and executes relevant commands on this.taskList.
     *
     * @param inputStr String representing user input
     * @return String to be printed by Ui
     */
    public String parse(String inputStr) {
        String[] inputArray = inputStr.split("\\s+",2);
        //The first element of inputArray represents the command to be executed
        //      while the second element represents details of how to execute said command but may or may not exist

        String command = inputArray[0];

        try {
            switch (command) {
            case "bye":
                return "Until we next meet. The Emperor protects!\n";
            case "save":
                return "\n";
            case "list":
                return taskList.showList();
            case "mark":
                return taskList.markTask(inputArray[1]);
            case "unmark":
                return taskList.unmarkTask(inputArray[1]);
            case "delete":
                return taskList.delete(inputArray[1]);
            case "todo":
                return taskList.addTask(inputArray[1], TaskList.taskTypes.TODO);
            case "deadline":
                return taskList.addTask(inputArray[1], TaskList.taskTypes.DEADLINE);
            case "event":
                return taskList.addTask(inputArray[1], TaskList.taskTypes.EVENT);
            case "find":
                return taskList.find(inputArray[1]);
            default:
                return "Error: '" + inputStr + "' is not a sanctioned command.\n";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            switch (command) {
                case "mark":
                case "unmark":
                case "delete":
                    return "Error: You must designate the sacred number of the task to " + command + ".\n";
                case "todo":
                case "deadline":
                case "event":
                    return "Error: A warrior’s oath cannot be sworn upon silence. The task description must be spoken.\n";
                default:
                    throw new RuntimeException(e); //Unknown source of exception
            }
        } catch (NumberFormatException e) {
//            return "Error: " + inputArray[1] + " is not an integer. Please input task number to "
//                            + command +"\n";
            return "Error: " + inputArray[1] + " is no true number. Present a valid task number to "
                    + command + ", lest duty falter.\n";
        } catch (InvalidInputException e) {
            return e.getMessage() +"\n";
        }
    }
}
