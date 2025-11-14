package duke.userinterface;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasklist;
import duke.task.Todo;

/**
 * Parser class to interpret user inputs into commands.
 */
public class Parser {
    private boolean hasReadBack = false;
    private Tasklist lst = new Tasklist();
    private UI voice = new UI();

    /**
     * Constructs a Parser.
     *
     * @param hasReadBack True if inputs should be echoed back to the user
     */
    public Parser(boolean hasReadBack) {
        this.hasReadBack = hasReadBack;
    }

    /**
     * Parses a user input string and executes the corresponding command.
     *
     * @param input the user input
     * @return true if Duke should continue running, false if it should exit
     */
    public String parse(String input) {
        try {
            assert input != null : "User input should not be null";

            String[] inputArr = input.split(" ", 2);
            String command = inputArr[0].toLowerCase();

            String description = (inputArr.length > 1) ? inputArr[1].trim() : "";

            assert !command.isEmpty() : "Command word should not be empty";

            switch (command) {
            case "priority":
                if (description.isEmpty()) {
                    if (hasReadBack) {
                        voice.priorityError();
                    }
                }
                String[] priorityArr = description.split(" ", 2);
                int priorityIndex = Integer.parseInt(priorityArr[0]);
                String priority = priorityArr[1];
                lst.setPriorityList(priorityIndex, priority);
                if (hasReadBack) {
                    return voice.setPriorityEB(priorityIndex, priority);
                }
                break;

            case "mark":
                if (description.isEmpty()) {
                    if (hasReadBack) {
                        voice.markError();
                    }
                }
                lst.tickbox(Integer.parseInt(description));
                if (hasReadBack) {
                    return voice.tickboxEB(Integer.parseInt(description));
                }
                break;

            case "unmark":
                if (description.isEmpty()) {
                    if (hasReadBack) {
                        voice.unmarkError();
                    }
                }
                lst.untickbox(Integer.parseInt(description));
                if (hasReadBack) {
                    return voice.untickboxEB(Integer.parseInt(description));
                }
                break;

            case "delete":
                if (description.isEmpty()) {
                    if (hasReadBack) {
                        voice.deleteError(); // throws BotException
                    }
                }
                int index = Integer.parseInt(description);
                Task removedTask = Tasklist.peekList(index - 1);
                lst.removeFromList(index);
                if (hasReadBack) {
                    return voice.removeFromListEB(removedTask);
                }
                break;

            case "todo":
                if (description.isEmpty()) {
                    if (hasReadBack) {
                        voice.todoError();
                    }
                }
                Task t1 = new Todo(description);
                lst.addToList(t1);
                if (hasReadBack) {
                    return voice.addToListEB(t1);
                }
                break;

            case "deadline":
                if (description.isEmpty() || !description.contains("/by")) {
                    if (hasReadBack) {
                        voice.deadlineError();
                    }
                }
                Task t2 = new Deadline(description);
                lst.addToList(t2);
                if (hasReadBack) {
                    return voice.addToListEB(t2);
                }
                break;

            case "event":
                if (description.isEmpty() || !description.contains("/from") || !description.contains("/to")) {
                    if (hasReadBack) {
                        voice.eventError();
                    }
                }
                Task t3 = new Event(description);
                lst.addToList(t3);
                if (hasReadBack) {
                    return voice.addToListEB(t3);
                }
                break;

            case "list":
                if (hasReadBack) {
                    return lst.displayList();
                }
                break;

            case "find":
                if (hasReadBack) {
                    return lst.find(description);
                }
                break;

            case "bye":
                if (hasReadBack) {
                    return voice.bye();
                }
                break;

            default:
                if (hasReadBack) {
                    voice.unknownError();
                }
                break;
            }
        } catch (BotException e) {
            return ErrorMessage.printError(e.getMessage());
        }
        return "";
    }

    /**
     * Enables readback so that commands are echoed back to the user.
     */
    public void enableReadback() {
        this.hasReadBack = true;
    }
}
