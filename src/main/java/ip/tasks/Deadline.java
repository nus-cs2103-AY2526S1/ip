package ip.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ip.commands.DateValidator;
import ip.exceptions.UnknownInputException;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {

    private static final String PREFIX_TWO = "by ";
    private static final int PREFIX_TWO_LENGTH = PREFIX_TWO.length();

    private LocalDate dueDate;

    /**
     * Constructor for a Deadline task
     *
     * @param description Description of task
     * @param dueDate     Due date of task
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void snooze(String[] splitInputs) throws UnknownInputException {

        boolean hasNoDueDate = splitInputs[1].length() <= PREFIX_TWO_LENGTH;
        boolean hasIncorrectFormat = !splitInputs[1].startsWith(PREFIX_TWO);

        if (hasNoDueDate || hasIncorrectFormat) {
            throw new UnknownInputException("Snoozing a deadline requires a due date inputted with '/by");
        }

        String newDeadline = splitInputs[1].substring(PREFIX_TWO_LENGTH).trim();
        boolean isDate = DateValidator.isValidDate(newDeadline);

        if (!isDate) {
            throw new UnknownInputException("Your Deadline has to have a due date in the format yyyy-mm-dd");
        }

        LocalDate date = LocalDate.parse(newDeadline);

        this.setDueDate(date);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toDataString() {
        int isDoneInt;
        if (super.getIsDone()) {
            isDoneInt = 1;
        } else {
            isDoneInt = 0;
        }
        return String.format("D / %d / %s / %s", isDoneInt, super.getDescription(), dueDate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
        String dateString = dueDate.format(formatter);

        return String.format("[D]" + super.toString() + " (by: %s)", dateString);
    }

}
