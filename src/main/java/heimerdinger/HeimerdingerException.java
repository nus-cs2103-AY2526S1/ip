package heimerdinger;

/**
 * Custom exception type for the Heimerdinger application.
 *
 * <p>This exception is thrown to signal user-facing or domain-specific errors
 * during command parsing, execution, or persistence. It is intended to wrap
 * validation failures, invalid inputs, or storage-related issues in a way that
 * can be cleanly surfaced to the user.</p>
 *
 * <p>Examples of when this exception may be thrown:</p>
 * <ul>
 *   <li>User omits a required command argument (e.g., missing deadline date).</li>
 *   <li>User provides an invalid index (e.g., out of bounds in {@code mark} or {@code delete}).</li>
 *   <li>Parsing of a date/time string fails.</li>
 *   <li>File I/O errors when reading or saving tasks in {@link heimerdinger.Storage}.</li>
 * </ul>
 *
 * @see heimerdinger.command.Command
 * @see heimerdinger.Storage
 */
public class HeimerdingerException extends Exception {
    public HeimerdingerException(String message) {
        super(message);
    }
}
