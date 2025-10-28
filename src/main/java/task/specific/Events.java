package task.specific;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import exceptions.EventTimelineInvalid;
import exceptions.InvalidDateInput;
import exceptions.InvalidElementInList;
import task.Task;

/**
 * This class represents an event. In other words, there is a
 * starting and an ending time. Events will then be added to the
 * TaskList in the future.
 */
public class Events extends Task {
    private final LocalDate startingTime;
    private final LocalDate endingTime;

    /**
     * Creates an event with the constuctor.
     *
     * @param description Description of the event.
     * @param sT Starting time of the event specified by the user.
     * @param eT Dnding time of the event specified by the user.
     * @throws InvalidElementInList It is acting on a nonexistent string.
     * @throws InvalidDateInput The user's format for the date is incorrect.
     * @throws EventTimelineInvalid The user's starting time is after the ending time.
     */
    public Events(String description, String sT, String eT) throws
            InvalidElementInList, InvalidDateInput, EventTimelineInvalid {
        super(description);
        try {
            this.startingTime = LocalDate.parse(sT);
            this.endingTime = LocalDate.parse(eT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInput();
        }
        if (startingTime.isAfter(endingTime)) {
            throw new EventTimelineInvalid();
        }
    }

    /**
     * Creates an event with the constuctor givne the parameters
     *
     * @param description Description of the event.
     * @param sT Starting time of the event specified by the user.
     * @param eT Dnding time of the event specified by the user.
     * @param finishType Whether the event has passed.
     * @param tags All the tags that the event is associated with.
     * @throws InvalidElementInList It is acting on a nonexistent string.
     * @throws InvalidDateInput The user's format for the date is incorrect.
     * @throws EventTimelineInvalid The user's starting time is after the ending time.
     */
    public Events(String description, String sT, String eT, boolean finishType,
                  ArrayList<String> tags) throws
            InvalidElementInList, InvalidDateInput, EventTimelineInvalid {
        super(description, finishType, tags);
        try {
            assert sT != null : "the starting time is null";
            assert eT != null : "the ending time is null";
            this.startingTime = LocalDate.parse(sT);
            this.endingTime = LocalDate.parse(eT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInput();
        }
        if (startingTime.isAfter(endingTime)) {
            throw new EventTimelineInvalid();
        }
    }

    /**
     * Creates an event with the constuctor givne the parameters
     *
     * @param description Description of the event.
     * @param sT Starting time of the event specified by the user.
     * @param eT Dnding time of the event specified by the user.
     * @param finishResult Whether the event has passed.
     * @throws InvalidElementInList It is acting on a nonexistent string.
     * @throws InvalidDateInput The user's format for the date is incorrect.
     * @throws EventTimelineInvalid The user's starting time is after the ending time.
     */
    public Events(String description, String sT, String eT, boolean finishResult)
            throws InvalidElementInList, InvalidDateInput, EventTimelineInvalid {
        super(description);
        try {
            assert sT != null : "the starting time is null";
            assert eT != null : "the ending time is null";
            this.startingTime = LocalDate.parse(sT);
            this.endingTime = LocalDate.parse(eT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateInput();
        }
        if (startingTime.isAfter(endingTime)) {
            throw new EventTimelineInvalid();
        }
    }

    /**
     * Returns the ending time given its toString().
     */
    public String getEndingTime() {
        assert this.endingTime != null : "the ending time is null";
        return this.endingTime.toString();
    }

    /**
     * Returns the starting time given its toString().
     */
    public String getStaringTime() {
        assert this.startingTime != null : "the starting time is null";
        return this.startingTime.toString();
    }

    /**
     * Returns the deadline object represented as a string.
     */
    public String toString() {
        String stringStarting = this.startingTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String stringEnding = this.endingTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[Events]" + super.toString()
                + " (from: " + stringStarting + " to: " + stringEnding + ") "
                + super.taggedToPrint();
    }

    /**
     * Returns the deadline object represented as a string and stored inside of the document.
     */
    @Override
    public String store() {
        return "[Events]\"\"" + super.store() + "\\from"
                + startingTime.toString() + "\"\"" + "\\to" + endingTime.toString()
                + "\"\"" + super.taggedStrings();
    }
}

