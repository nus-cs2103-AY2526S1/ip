package lyra.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting dates for tasks in the Lyra application.
 * Provides consistent date handling across the application with support for
 * storage format (ISO) and user-friendly display format.
 */
public final class DateTimeUtil {
	/**
	 * Date formatter for storage format (ISO_LOCAL_DATE).
	 * Format: yyyy-MM-dd
	 */
	public static final DateTimeFormatter STORAGE_DATE = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd
	
	/**
	 * Date formatter for user-friendly display format.
	 * Format: MMM d yyyy (e.g., Oct 15 2019)
	 */
	public static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private DateTimeUtil() {}

	/**
	 * Parses a date string using the ISO date format (yyyy-MM-dd).
	 * Trims whitespace from the input before parsing.
	 *
	 * @param input the date string to parse in yyyy-MM-dd format
	 * @return the parsed LocalDate object
	 * @throws DateTimeParseException if the input string cannot be parsed as a valid date
	 */
	public static LocalDate parseDate(String input) throws DateTimeParseException {
		String trimmed = input.trim();
		// Minimal requirement: accept yyyy-MM-dd
		return LocalDate.parse(trimmed, STORAGE_DATE);
	}

	/**
	 * Formats the given date into a human-friendly string for display purposes.
	 * Uses the format MMM d yyyy (e.g., Oct 15 2019).
	 *
	 * @param date the LocalDate object to format
	 * @return the formatted date string
	 */
	public static String formatForDisplay(LocalDate date) {
		return date.format(DISPLAY_DATE);
	}
}


