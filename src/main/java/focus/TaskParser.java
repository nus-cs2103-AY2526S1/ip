package focus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;



/**
 * Parses a single storage line into a concrete Task.
 */
public class TaskParser {


    /**
     * Parses a storage line into a Task.
     *
     * @param unparsedLine One line read from the save file.
     * @return Parsed task instance.
     * @throws FocusException If the line is corrupted or the type is unknown.
     */
    public static Task parse(String unparsedLine) throws FocusException {

        DateTimeFormatter inputFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // User input and storage format for events and deadline

        String[] parts = unparsedLine.split(" \\| ");

        if (parts.length < 3) {
            throw new FocusException("Corrupted line: %s" + unparsedLine);
        }

        char firstChar = parts[0].charAt(0);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        // compute the minimum columns without a tag and detect an optional tag column
        final int minWithoutTag = (firstChar == 'T') ? 3 : 4;
        final boolean hasTagColumn = parts.length == (minWithoutTag + 1);
        final String tagColumn = hasTagColumn ? parts[parts.length - 1].trim() : null;

        Task toRet;

        switch (firstChar) {

        case 'T':

            toRet = new ToDo(description);
            break;

        case 'D':

            if (parts.length < 4) {
                throw new FocusException("Deadline missing 'by': " + unparsedLine);
            }

            String deadlineBy = parts[3].trim();
            LocalDateTime formattedDeadlineBy;

            try {
                formattedDeadlineBy = LocalDateTime.parse(deadlineBy, inputFormat);
            } catch (DateTimeParseException ex) {
                throw new FocusException("     Invalid date-time. Use yyyy-MM-dd HHmm (e.g., 2025-10-01 0930).");
            }

            toRet = new Deadline(description, formattedDeadlineBy);

            break;

        case 'E':

            if (parts.length < 4) {
                throw new FocusException("Event missing start/end: " + unparsedLine);
            }

            String[] times = parts[3].split(" - ", 2); // split into start and end

            if (times.length < 2) {
                throw new FocusException("Event time not in 'start - end' format: " + unparsedLine);
            }

            String eventStart = times[0].trim();
            String eventEnd = times[1].trim();
            LocalDateTime formattedEventStart;
            LocalDateTime formattedEventEnd;

            try {
                formattedEventStart = LocalDateTime.parse(eventStart, inputFormat);
                formattedEventEnd = LocalDateTime.parse(eventEnd, inputFormat);
            } catch (DateTimeParseException ex) {
                throw new FocusException("     Invalid date-time. Use yyyy-MM-dd HHmm (e.g., 2025-10-01 0930).");
            }

            toRet = new Event(description, formattedEventStart, formattedEventEnd);

            break;

        default:
            throw new FocusException("Unknown task type. Could not parse!");

        }

        if (isDone) {
            toRet.markAsDone();
        }

        if (hasTagColumn) {
            // Require leading '#'
            if (tagColumn.isEmpty() || !tagColumn.startsWith("#")) {
                throw new FocusException("     Corrupted tag column: " + tagColumn);
            }
            String body = tagColumn.substring(1).trim();
            if (body.isEmpty()) {
                throw new FocusException("     Tag cannot be empty (e.g., #work).");
            }
            if (!body.matches("[A-Za-z0-9_-]{1,20}")) {
                throw new FocusException("     Invalid tag: " + tagColumn
                        + " (allowed: letters, digits, '_' or '-', 1–20 chars)");
            }
            toRet.setTag(new Tag(-1, body));
        } else if (parts.length > minWithoutTag + 1) {
            // More columns than expected (+1 for tag) => malformed line
            throw new FocusException("     Corrupted line (too many columns): " + unparsedLine);
        }

        return toRet;

    }

}
