package mochi;

/**
 * Extends MochiException to handle Deadline specific exceptions
 */
public class DeadlineException extends MochiException {
    public DeadlineException() {
        super();
    }

    @Override
    public String toString() {
        return String.format(super.toString() + "\n" + """
             Invalid deadline command used.
             Format: `deadline <description> /by <date> <time (optional)>`
                      date format: YYYY-MM-DD | time format: HHmm
             Example: deadline watch lecture videos /by 2019-10-15 1800
             """);
    }
}
