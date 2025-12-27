package ip.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ip.commands.DateValidator;
import ip.exceptions.UnknownInputException;

/**
 * Represents a task with a start date and end date
 */
public class Event extends Task {

    private static final String PREFIX_TWO = "from ";
    private static final int PREFIX_TWO_LENGTH = PREFIX_TWO.length();
    private static final String PREFIX_THREE = "to ";
    private static final int PREFIX_THREE_LENGTH = PREFIX_THREE.length();

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for Event task
     *
     * @param description Task description
     * @param startDate   Start date of the event
     * @param endDate     End date of the event
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void snooze(String[] splitInputs) throws UnknownInputException {

        boolean hasNoStartDate = splitInputs[1].length() <= PREFIX_TWO_LENGTH;
        boolean startHasIncorrectFormat = !splitInputs[1].startsWith(PREFIX_TWO);
        boolean hasNoEndDate = splitInputs[2].length() <= PREFIX_THREE_LENGTH;
        boolean endHasIncorrectFormat = !splitInputs[2].startsWith(PREFIX_THREE);

        if (hasNoStartDate || startHasIncorrectFormat) {
            throw new UnknownInputException("Snoozing an event requires a new start date inputted with '/from'");
        }

        if (hasNoEndDate || endHasIncorrectFormat) {
            throw new UnknownInputException("Snoozing an event requires a new end date inputted with '/to'");
        }

        String newStartDate = splitInputs[1].substring(PREFIX_TWO_LENGTH).trim();
        String newEndDate = splitInputs[2].substring(PREFIX_THREE_LENGTH).trim();
        boolean startIsDate = DateValidator.isValidDate(newStartDate);
        boolean endIsDate = DateValidator.isValidDate(newEndDate);

        if (!startIsDate || !endIsDate) {
            throw new UnknownInputException("Your Event has to have start and end dates with format yyyy-mm-dd");
        }

        this.setStartDate(LocalDate.parse(newStartDate));
        this.setEndDate(LocalDate.parse(newEndDate));

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
        return String.format("E / %d / %s / %s / %s", isDoneInt, super.getDescription(), startDate, endDate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);
        return String.format("[E]" + super.toString() + " (from: %s, to: %s)", startDateString, endDateString);
    }
}
