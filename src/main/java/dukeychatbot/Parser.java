package dukeychatbot;

import java.util.ArrayList;

import dukeychatbot.dukeyexceptions.EmptyDescriptionException;
import dukeychatbot.dukeyexceptions.InvalidCommandException;
import dukeychatbot.dukeyexceptions.MissingDeadlineException;
import dukeychatbot.dukeyexceptions.MissingTimeframeException;
import dukeychatbot.tasktypes.Task;

/**
 * Constructs the Parser class which deals with making sense of the user command.
 *
 * @author dongjun
 */
public class Parser {
    private TaskList taskArray;
    private Ui ui;
    private boolean isActive;
    private Storage storage;

    /**
     * Constructs the Parser object.
     */
    public Parser(TaskList taskArray, Ui ui, Storage storage) {
        this.taskArray = taskArray;
        this.ui = ui;
        this.isActive = true;
        this.storage = storage;
    }

    /**
     * Parses the user input and carries out the corresponding commands.
     *
     * @param fullCommand Command input from user
     */
    public String parse(String fullCommand) {
        String command = fullCommand.trim();
        if (command.equalsIgnoreCase("bye") || command.equalsIgnoreCase("b")) {
            return this.byeCommand();
        } else if (command.equalsIgnoreCase("list") || command.equalsIgnoreCase("li")) {
            return this.ui.printList("", taskArray.getTasks());
        } else if (command.split(" ").length == 2) {
            try {
                String commandWord = command.split(" ")[0];
                if (commandWord.equalsIgnoreCase("mark")
                        || commandWord.equalsIgnoreCase("m")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    return this.markCommand(taskNumber);
                } else if (commandWord.equalsIgnoreCase("unmark")
                        || commandWord.equalsIgnoreCase("unm")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    return this.unmarkCommand(taskNumber);
                } else if (commandWord.equalsIgnoreCase("delete")
                        || commandWord.equalsIgnoreCase("del")) {
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    return this.delete(taskNumber);
                } else if (commandWord.equalsIgnoreCase("find")
                        || commandWord.equalsIgnoreCase("f")) {
                    String keyword = command.split(" ")[1];
                    return this.find(keyword);
                }
            } catch (NumberFormatException e) {
                return this.ui.numberFormatError();
            }
        }
        try {
            return this.taskArray.addNewTask(command, false, false);
        } catch (InvalidCommandException | EmptyDescriptionException | MissingDeadlineException
                 | MissingTimeframeException e) {
            return this.ui.formattedErrorResponse(e.getMessage());
        }
    }

    /**
     * Finds the task descriptions with the keyword in it.
     *
     * @param keyword Keyword to find
     */
    public String find(String keyword) {
        ArrayList<Task> matchingArray = this.taskArray.find(keyword);
        if (matchingArray.isEmpty()) {
            return this.ui.noMatchingTasks();
        } else {
            return this.ui.printList("\nHere are the matching tasks in your list: ", matchingArray);
        }
    }

    /**
     * Deletes the task corresponding to the task number from the task list.
     *
     * @param taskNumber task number
     */
    public String delete(int taskNumber) {
        assert taskNumber >= 0 : "task number should be 0 or more";

        if (taskNumber <= taskArray.getTasks().size()) {
            return this.taskArray.removeTask(taskNumber);
        } else {
            return this.ui.invalidTaskIndex();
        }
    }

    /**
     * Marks the task corresponding to the task number as not done.
     *
     * @param taskNumber task number
     */
    public String unmarkCommand(int taskNumber) {
        assert taskNumber >= 0 : "task number should be 0 or more";

        if (taskNumber <= taskArray.getTasks().size()) {
            return this.taskArray.unmarkDone(taskNumber);
        } else {
            return this.ui.invalidTaskIndex();
        }
    }

    /**
     * Marks the task corresponding to the task number as done.
     *
     * @param taskNumber task number
     */
    public String markCommand(int taskNumber) {
        assert taskNumber >= 0 : "task number should be 0 or more";

        if (taskNumber <= this.taskArray.getTasks().size()) {
            return this.taskArray.markDone(taskNumber);
        } else {
            return this.ui.invalidTaskIndex();
        }
    }

    /**
     * Returns the goodbye text on the UI and store the task list to the hard drive.
     */
    public String byeCommand() {
        this.isActive = false;
        this.storage.save(taskArray.getTasks());
        return this.ui.bye();
    }

    /**
     * Returns the Active status of the chatbot.
     *
     * @return Active status
     */
    public boolean getActiveStatus() {
        return this.isActive;
    }
}
