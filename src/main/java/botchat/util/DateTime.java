package botchat.util;

import botchat.app.BotChatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * To format the date into standard formats.
 */
public class DateTime {
    public static final DateTimeFormatter INPUT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter INPUT_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final DateTimeFormatter OUTPUT_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Validate that a string is in yyyy-MM-dd format.
     *
     * @param raw user input (e.g. "2025-09-30")
     * @throws BotChatException if null/blank/invalid format
     */
    public static void validateDateString(String raw) throws BotChatException {
        Objects.requireNonNull(raw, "date cannot be null");
        String s = raw.trim();
        if (s.isEmpty()) {
            throw new BotChatException("OH NO! date is missing");
        }

        try {
            LocalDate.parse(s, INPUT_DATE);
        } catch (DateTimeParseException e) {
            throw new BotChatException(
                    "OH NO! invalid date format. Use yyyy-MM-dd (e.g. 2025-09-30)."
            );
        }
    }

    /**
     * Validate that a string is in yyyy-MM-dd HHmm format.
     *
     * @param raw user input (e.g. "2025-09-30 1830")
     * @throws BotChatException if null/blank/invalid
     */
    public static void validateDateTimeString(String raw) throws BotChatException {
        Objects.requireNonNull(raw, "date/time cannot be null");
        String s = raw.trim();
        if (s.isEmpty()) {
            throw new BotChatException("OH NO! date/time is missing");
        }

        try {
            LocalDateTime.parse(s, INPUT_DATETIME); // validate only
        } catch (DateTimeParseException e) {
            throw new BotChatException(
                    "OH NO! invalid date/time format. Use yyyy-MM-dd HHmm (e.g. 2025-09-30 1830)."
            );
        }
    }

}
