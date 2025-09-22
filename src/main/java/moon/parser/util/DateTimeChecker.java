package moon.parser.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import moon.models.MoonDateTime;
import moon.parser.exceptions.DateTimeException;

/**
 * Utility class providing validation and comparison helpers for {@link moon.models.MoonDateTime}.
 * <p>
 * This class centralises common time-related checks such as:
 * <ul>
 *   <li>Validating that a deadline is not set before the start of today (00:00)</li>
 *   <li>Ensuring that an event's start date/time is not after its end date/time</li>
 * </ul>
 * All methods are static, making the class a convenient companion to {@link moon.parser.util.DateTimeParser}.
 */
public class DateTimeChecker {
    /**
     * Checks if the given due date/time is not before the start of today (00:00).
     *
     * @param due the deadline moment.
     * @throws DateTimeException if due is today or in the future.
     */
    public static void isNotBeforeToday(MoonDateTime due) throws DateTimeException {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        LocalDateTime candidate = due.time()
                .map(t -> LocalDateTime.of(due.date(), t))
                .orElse(due.date().atStartOfDay());
        if (candidate.isBefore(startOfToday)) {
            throw new DateTimeException("Meow! Your deadline due date cannot be before today!");
        }
    }

    /**
     * Checks if the start of an event is before (or equal to) the end.
     * If either side has no time, only dates are compared.
     *
     * @param from start date/time.
     * @param to   end date/time.
     * @throws DateTimeException if from is before to (or same day if times are missing).
     */
    public static void isValidEventRange(MoonDateTime from, MoonDateTime to) throws DateTimeException {
        boolean valid;

        if (from.time().isEmpty() || to.time().isEmpty()) {
            // Using !isAfter() instead of isBefore() allows for event in the same date
            valid = !from.date().isAfter(to.date());
        } else {
            LocalDateTime start = LocalDateTime.of(from.date(), from.time().get());
            LocalDateTime end = LocalDateTime.of(to.date(), to.time().get());
            valid = start.isBefore(end);
        }

        if (!valid) {
            throw new DateTimeException("Meow! Your event end time cannot be before start!");
        }
    }
}
