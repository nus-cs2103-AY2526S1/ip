package exceptions;

/**
 * Throws error if the event start date is after the end date.
 */

public class EventTimelineInvalid extends Exception {
    protected String message;

    /**
     * Creates an EventTimelineInvalid() object
     */
    public EventTimelineInvalid() {
        super("Your starting date is after your ending date");
    }
}

