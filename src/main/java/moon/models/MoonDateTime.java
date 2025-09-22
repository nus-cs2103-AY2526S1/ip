package moon.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a date with an optional time, used for tasks in the Moon chatbot.
 * <p>
 * Provides factory methods to create either:
 * <ul>
 *   <li>a date-only value, or</li>
 *   <li>a date with an associated time.</li>
 * </ul>
 * <p>
 * The string representation is formatted for display to the user.
 */
public class MoonDateTime {
    private final LocalDate date;
    private final Optional<LocalTime> time;

    private MoonDateTime(LocalDate date, Optional<LocalTime> time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Creates a MoonDateTime with both a date and a time.
     *
     * @param d The date
     * @param t The time
     * @return A MoonDateTime representing the given date and time
     */
    public static MoonDateTime of(LocalDate d, LocalTime t) {
        return new MoonDateTime(d, Optional.of(t));
    }

    /**
     * Creates a MoonDateTime with only a date (no time component).
     *
     * @param d The date
     * @return A MoonDateTime representing the given date only
     */
    public static MoonDateTime ofDate(LocalDate d) {
        return new MoonDateTime(d, Optional.empty());
    }

    /**
     * Returns the formatted date component.
     *
     * @return A string in the format {@code "MMM d yyyy"} (e.g. "Sep 6 2025")
     */
    private String dateToString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the formatted time component if present.
     *
     * @return A string in the format {@code " HH:mm"} (e.g. " 14:30"),
     *         or an empty string if no time is set
     */
    private String timeToString() {
        return this.time
                .map(t -> " " + t.format(DateTimeFormatter.ofPattern("HH:mm")))
                .orElse("");
    }

    public LocalDate date() {
        return this.date;
    }

    public Optional<LocalTime> time() {
        return this.time;
    }

    /**
     * Returns the string representation of this MoonDateTime for display.
     * <p>
     * Format:
     * <ul>
     *   <li>{@code "MMM d yyyy HH:mm"} if time is present.</li>
     *   <li>{@code "MMM d yyyy"} if no time is present.</li>
     * </ul>
     *
     * @return Formatted string of the date (and optional time)
     */
    @Override
    public String toString() {
        return this.dateToString() + this.timeToString();
    }
}
