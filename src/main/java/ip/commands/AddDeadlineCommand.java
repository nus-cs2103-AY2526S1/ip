package ip.commands;

import java.time.LocalDate;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Deadline;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to add deadline task to task list when given one as input
 */
public class AddDeadlineCommand implements Command {
    private static final String PREFIX = "deadline ";
    private static final int PREFIX_LENGTH = PREFIX.length();
    private static final String PREFIX_TWO = "by ";
    private static final int PREFIX_TWO_LENGTH = PREFIX_TWO.length();

    /**
     * Adds Deadline task into TaskList, appends task into data file and calls UI for response
     *
     * @throws UnknownInputException if input is missing description, '/by' or valid dueDate
     *                               Used Deepseek to split up my original execute method into shorter methods
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException {

        validateInputLength(input);
        validatePrefixPresence(input);

        String[] splitInputs = input.substring(PREFIX_LENGTH).split("/");
        String taskDescription = validateAndGetDescription(splitInputs);
        String dueDate = validateAndGetDueDate(splitInputs);

        validateDateFormat(dueDate);

        LocalDate date = LocalDate.parse(dueDate);
        Deadline addTask = new Deadline(taskDescription, date);

        storage.writeToStorage(addTask);
        tasks.addTask(addTask);
        assert tasks.contains(addTask) : "Task not added";

        return ui.showTaskInput(addTask);
    }

    /**
     * Checks if input has a description
     *
     * @param input User input
     * @throws UnknownInputException if input is just 'deadline'
     */
    private void validateInputLength(String input) throws UnknownInputException {
        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }
    }

    /**
     * Checks if input has '/by'
     *
     * @param input User input
     * @throws UnknownInputException if input does not contain '/by'
     */
    private void validatePrefixPresence(String input) throws UnknownInputException {
        if (!input.contains("/" + PREFIX_TWO)) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }
    }

    /**
     * Checks if input contains task description
     *
     * @param splitInputs User input split by "/"
     * @return task description if it exists
     * @throws UnknownInputException if there is no task description
     */
    private String validateAndGetDescription(String[] splitInputs) throws UnknownInputException {
        String taskDescription = splitInputs[0].trim();
        if (taskDescription.isEmpty()) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }
        return taskDescription;
    }

    /**
     * Checks if user has input a due date
     *
     * @param splitInputs User input split by "/"
     * @return Due date if valid
     * @throws UnknownInputException if no due date or second half of input does not start with '/by'
     */
    private String validateAndGetDueDate(String[] splitInputs) throws UnknownInputException {
        boolean hasNoDueDate = splitInputs[1].length() <= PREFIX_TWO_LENGTH;
        boolean hasIncorrectFormat = !splitInputs[1].startsWith(PREFIX_TWO);

        if (hasNoDueDate || hasIncorrectFormat) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }

        return splitInputs[1].substring(PREFIX_TWO_LENGTH).trim();
    }

    /**
     * Checks if due date is valid
     *
     * @param dueDate Due date
     * @throws UnknownInputException if due date is not in valid format
     */
    private void validateDateFormat(String dueDate) throws UnknownInputException {
        boolean isDate = DateValidator.isValidDate(dueDate);
        if (!isDate) {
            throw new UnknownInputException("Your Deadline has to have a due date in the format yyyy-mm-dd");
        }
    }
}
