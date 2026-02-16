package ubersuper.utils;

import ubersuper.clients.Client;
import ubersuper.exceptions.UberExceptions;
import ubersuper.tasks.Deadline;
import ubersuper.tasks.Event;
import ubersuper.tasks.Task;
import ubersuper.tasks.Todo;
import ubersuper.utils.command.CommandType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Parsing user input is done via {@link #fromInput(String)}, which:
 * <ul>
 *   <li>is case-insensitive (e.g., {@code "Bye"}, {@code "BYE"} → {@link #BYE}),</li>
 *   <li>matches the <em>first whitespace-delimited token</em> only,</li>
 *   <li>requires an exact token match (no prefix matching; e.g., {@code "listall"} → {@link #UNKNOWN}),</li>
 *   <li>is null/blank-safe (null or blank input → {@link #UNKNOWN}).</li>
 * </ul>
 * Utility for parsing user-supplied date/time strings into {@link LocalDateTime}.
 * <p>
 * The parser is intentionally permissive and accepts several common formats.
 * If only a date is given (no time component), the returned {@link LocalDateTime}
 * is set to {@code 00:00} (start of day).
 *
 * <h2>Accepted input formats</h2>
 * <ul>
 *   <li><b>ISO date-time</b>: {@code yyyy-MM-dd'T'HH:mm}
 *   <li><b>ISO date-time (space)</b>: {@code yyyy-MM-dd HH:mm}
 *   <li><b>ISO date (date only)</b>: {@code yyyy-MM-dd} → time defaults to {@code 00:00}
 *   <li><b>Slash with compact time</b>: {@code d/M/uuuu HHmm}
 *   <li><b>Slash (date only)</b>: {@code d/M/uuuu} → time defaults to {@code 00:00}
 *   <li><b>Dash with compact time</b>: {@code d-M-uuuu HHmm}
 *   <li><b>Dash (date only)</b>: {@code d-M-uuuu} → time defaults to {@code 00:00}
 * </ul>
 *
 * <p>If the input does not match any of the above, an {@link UberExceptions}
 * is thrown with a helpful message.</p>
 */
public class Parser {

    /**
     * Parses the user's input into a {@link CommandType}.
     * <p><strong>Rules:</strong></p>
     * <ul>
     *   <li>Extracts the first whitespace-delimited token and lower-cases it.</li>
     *   <li>Performs exact token match against known commands (no prefix/substring match).</li>
     *   <li>Returns CommandType.UNKNOWN if the token does not match any command,
     *   or if input is null/blank.</li>
     * </ul>
     *
     * @param input full user input line
     * @return matching {@link CommandType} or CommandType.UNKNOWN if none
     */
    public static CommandType fromInput(String input) {

        if (input == null || input.isBlank()) {
            return CommandType.UNKNOWN;
        }
        String[] parts = input.strip().split("\\s+", 2);
        String head = parts[0].toLowerCase();

        for (CommandType c : CommandType.values()) {
            if (head.equals(c.getKeyword())) {
                return c;
            }
        }
        return CommandType.UNKNOWN;
    }


    /**
     * Parses a date/time string into a {@link LocalDateTime}.
     * <p>
     * The method tries multiple formats (listed in the class Javadoc) in a sensible order.
     * If only a date is supplied, the time component is set to midnight.
     *
     * @param raw user input containing a date/time
     * @return a {@link LocalDateTime} representing the parsed moment
     * @throws UberExceptions if the input cannot be parsed by any supported format
     */
    public static LocalDateTime parseDateTime(String raw) throws UberExceptions {
        String s = raw.trim();

        // 1) ISO date-time: 2019-12-02T18:00 (or "2019-12-02 18:00")
        try {
            if (s.contains("T")) {
                return LocalDateTime.parse(s);
            }
            if (s.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(s.replace(' ', 'T'));
            }
        } catch (DateTimeParseException ignore) {
            //ignore
        }

        // 2) ISO date only: 2019-12-02  (treat as 00:00)
        try {
            return LocalDate.parse(s).atStartOfDay();
        } catch (DateTimeParseException ignore) {
            //ignore
        }

        // 3) dd/MM/yyyy HHmm   e.g. 2/12/2019 1800
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
            return LocalDateTime.parse(s, f);
        } catch (DateTimeParseException ignore) {
            //ignore
        }

        // 4) dd/MM/yyyy        (00:00)
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d/M/uuuu");
            return LocalDate.parse(s, f).atStartOfDay();
        } catch (DateTimeParseException ignore) {
            //ignore
        }

        // 5) d-M-uuuu HHmm
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
            return LocalDateTime.parse(s, f);
        } catch (DateTimeParseException ignore) {
            //ignore
        }

        // 6) d-M-uuuu (00:00)
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d-M-uuuu");
            return LocalDate.parse(s, f).atStartOfDay();
        } catch (DateTimeParseException ignore) {
            //ignore
        }

        throw new UberExceptions("I couldn't understand the date/time: \""
                + raw
                + "\".\n" + "Try formats like: 2019-12-02, 2019-12-02 18:00, 2/12/2019 1800.");
    }

    /**
     * Parses the date portion of the user's input.
     * Supports both ISO format (yyyy-MM-dd) and alternative format (dd/MM/yyyy).
     *
     * @param input Full user input string.
     * @return Parsed {@link LocalDate} representing the date specified by the user.
     * @throws UberExceptions if the input format is invalid or cannot be parsed.
     */
    public static LocalDate parseDateInput(String input) throws UberExceptions {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2) {
            throw new UberExceptions("Use: onDate <yyyy-mm-dd | dd/MM/yyyy>");
        }

        String raw = parts[1].trim();
        try {
            return LocalDate.parse(raw);
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu");
                return LocalDate.parse(raw, formatter);
            } catch (DateTimeParseException ex) {
                throw new UberExceptions("Use: onDate <yyyy-mm-dd | dd/MM/yyyy>");
            }
        }
    }

    /**
     * Parses a list of raw task lines from the data file into {@link Task} objects.
     * Returns null entries for lines that cannot be parsed.
     *
     * @param lines The list of raw lines from the data file.
     * @return A list of {@link Task} objects (null if parsing failed for that line).
     */
    public static List<Task> parseTaskLines(List<String> lines) {
        return lines.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(Parser::parseTaskLine)
                .toList();
    }

    /**
     * Parses a single line of text into a {@link Task}.
     * Line format: type | done | description | [date...] | [end...]
     *
     * @param line The input line to parse.
     * @return A valid {@link Task}, or {@code null} if parsing fails.
     */
    private static Task parseTaskLine(String line) {
        try {
            String[] parts = Arrays.stream(line.split("\\|"))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (parts.length < 3) {
                return null;
            }

            String type = parts[0];
            boolean isDone = "1".equals(parts[1]);
            String description = parts[2];

            return switch (type) {
            case "T":
                Todo t = new Todo(description);
                if (isDone) {
                    t.mark();
                }
                yield t;
            case "D":
                if (parts.length >= 4) {
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    Deadline d = new Deadline(description, by);
                    if (isDone) {
                        d.mark();
                    }
                    yield d;
                } else {
                    yield null;
                }
            case "E":
                LocalDateTime start = LocalDateTime.parse(parts[3]);
                LocalDateTime end = LocalDateTime.parse(parts[4]);
                Event e = new Event(description, start, end);
                if (isDone) {
                    e.mark();
                }
                yield e;
            default:
                yield null;
            };
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parses all non-empty lines from the file into {@link Client} objects.
     * Invalid lines are represented as {@code null}.
     *
     * @param lines list of raw input lines.
     * @return list of {@link Client} objects (may contain {@code null} entries for skipped lines).
     */
    public static List<Client> parseClientLines(List<String> lines) {
        return lines.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(Parser::parseClientLine)
                .toList();
    }

    /**
     * Parses a single line from the file into a {@link Client}.
     * Expected format: {@code name | phone | email}.
     *
     * @param line a line from the client file.
     * @return a {@link Client} if parsed successfully, otherwise {@code null}.
     */
    private static Client parseClientLine(String line) {
        try {
            String[] parts = Arrays.stream(line.split("\\|"))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (parts.length < 3) {
                return null;
            }

            String name = parts[0];
            String phone = parts[1];
            String email = parts[2];

            return new Client(name, phone, email);

        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Parses a string into a string of client details
     * <p>
     *
     * @param raw user input containing a date/time
     * @return a string representing the client's details
     * @throws UberExceptions if the input cannot be parsed by any supported format
     */
    public static Client parseAddClient(String raw) throws UberExceptions {
        String[] parts = raw.split("/");
        if (parts.length < 2) {
            throw new UberExceptions("Use format: addclient <name> /phone <phone number> /email <email address>");
        } else if (parts.length < 3) {
            throw new UberExceptions("Use format: addclient <name> /phone <phone number> /email <email address>");
        }

        String name = parts[0].replaceFirst("addclient", "");
        String phonePart = parts[1].trim(); // "phone ..."
        String emailPart = parts[2].trim(); // "email ..."
        if (name.isEmpty()) {
            throw new UberExceptions("Please give your client a name");
        }
        //ensure correct formatting
        if (!phonePart.toLowerCase().startsWith("phone") || !emailPart.toLowerCase().startsWith("email")) {
            throw new UberExceptions("Use format: addclient <name> /phone <phone number> /email <email address>");
        }
        String phone = phonePart.replaceFirst("phone", "").trim();
        String email = emailPart.replaceFirst("email", "").trim();

        return new Client(name, phone, email);
    }
}
