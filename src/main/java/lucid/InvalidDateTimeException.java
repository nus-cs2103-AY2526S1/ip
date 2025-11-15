package lucid;

/**
 * Exception resulting from detection of invalid date time string
 */
public class InvalidDateTimeException extends LucidException {
    public InvalidDateTimeException() {
        super("Your date is invalid. Enter a real date! (Up to 12 months and not exceeding the days for that month)");
    }
}
