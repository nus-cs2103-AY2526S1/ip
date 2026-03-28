package sanbot.parser;

import sanbot.tasklist.TaskList;

/**
 * Utility class for processing user commands.
 */
public class Parser {

    /**
     * Processes user input and delegates to appropriate task operations.
     * Returns a string that is the result of the command that was executed after input was parsed
     *
     * @param input the user input string to be parsed and executed
     * @return string to indicate result of command that was executed
     */
    public static String handleInput(String input) {
        assert input != null : "Input cannot be null!";
        assert !input.trim().isEmpty() : "Input cannot be just whitespaces!";

        String[] processedParts = input.split(" ");

        assert processedParts.length > 0 : "split should produce at least 1 element";

        switch (processedParts[0]) {
            case "bye":
                return TaskList.exitApp(input);
            case "list":
                return TaskList.printListItems();
            case "mark":
                return TaskList.markDone(input);
            case "unmark":
                return TaskList.markNotDone(input);
            case "delete":
                return TaskList.deleteTask(input);
            case "todo":
                return TaskList.todoHandler(input);
            case "deadline":
                return TaskList.deadlineHandler(input);
            case "event":
                return TaskList.eventHandler(input);
            case "find":
                return TaskList.findTasks(input);
            default:
                return "Oops, I am not sure what is the task type!";
        }
    }
}