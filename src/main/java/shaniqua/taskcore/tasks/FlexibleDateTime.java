package shaniqua.taskcore.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FlexibleDateTime implements Serializable {
    private final long serialVersionUID = 1L;
    private final LocalDateTime dateTime;
    private boolean hasTime; // true: LocalDateTime behaviour; false: LocalDate behaviour;

    private FlexibleDateTime(LocalDateTime dateTime, boolean hasTime) {
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    /**
     * Factory method of FlexibleDateTime that parses input string.
     * @param in string representation of date (and time if present)
     * @return FlexibleDateTime instance
     * @throws FlexibleDateTimeException if the formatter has an error parsing
     */
    public static FlexibleDateTime parse(String in) throws FlexibleDateTimeException {
        LocalDateTime tempDateTime = null;
        boolean hasTimeTemp = false;
        try {
            if (isDateTime(in)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
                tempDateTime = LocalDateTime.parse(in, formatter);
                hasTimeTemp = true;
                return new FlexibleDateTime(tempDateTime, hasTimeTemp);
            } else if (isDate(in)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate temp = LocalDate.parse(in, formatter);
                tempDateTime = temp.atStartOfDay();
                return new FlexibleDateTime(tempDateTime, hasTimeTemp);
            }
        } catch (DateTimeParseException e) {
            throw new FlexibleDateTimeException("Parse Error");
        }
        throw new FlexibleDateTimeException("Invalid Input");
    };

    private static boolean isDate(String date) {
        String[] dateArr = date.split("-");
        if (dateArr.length != 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (!isInteger(dateArr[i])) {
                return false;
            }
        }
        if (dateArr[0].length() != 4) {
            return false;
        }
        int month = Integer.parseInt(dateArr[1]);
        if (month > 12 || month < 1) {
            return false;
        }
        int day = Integer.parseInt(dateArr[2]);
        return day > 0;
    }
    private static boolean isTime(String time) {
        if (!isInteger(time)) {
            return false;
        }
        int timeInt = Integer.parseInt(time);
        return timeInt < 2400 && timeInt > 0;
    }
    private static boolean isDateTime(String time) {
        String[] dateTimeArr = time.split(" ");
        if (dateTimeArr.length < 2) {
            return false;
        }
        return isDate(dateTimeArr[0]) && isTime(dateTimeArr[1]);
    }

    private static boolean isInteger(String param) {
        try {
            Integer.valueOf(param);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets internal LocalDateTime representation
     * @return LocalDateTime representation
     */
    public LocalDateTime get() {
        return dateTime;
    }

    /**
     * Gets status of whether the FlexibleDateTime object has time.
     * @return
     */
    public boolean hasTime() {
        return hasTime;
    }

    /**
     * Compares FlexibleDateTime with object O for equality.
     * @param object object to be compared
     * @return boolean of equality
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof FlexibleDateTime) {
            FlexibleDateTime temp = (FlexibleDateTime) object;
            return this.dateTime.equals(temp.dateTime);
        }
        return false;
    }

    /**
     * Returns string representation of FlexibleDateTime in form "mmm dd yyyy",
     * with time if present
     * @return string representation of FlexibleDateTime
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(" h a");
        return dateTime.toLocalDate().format(formatter)
                + (hasTime ? dateTime.toLocalTime().format(timeFormatter) : "");
    }
}
