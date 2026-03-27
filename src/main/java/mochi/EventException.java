package mochi;

/**
 * Extends MochiException to handle Event specific exceptions
 */
public class EventException extends MochiException {
    private String message = null;

    public EventException() {
        super();
    }

    /**
     * Returns an exception that contains details regarding event errors.
     *
     * @param s error message for the event issue
     */
    public EventException(String s) {
        this();
        this.message = s;
    }

    @Override
    public String toString() {
        if (this.message == null) {
            return String.format(super.toString() + "\n" + """
                     Invalid event command used.
                     Format: `event <description> /from <date> <time (optional)> /to <date> <time (optional)>`
                              date format: YYYY-MM-DD | time format: HHmm
                     Example: event squash /from 2025-09-17 1800 /to 2025-09-17 2000
                     """);
        }
        return String.format(super.toString() + "\n" + "Invalid event command used." + this.message);
    }
}
