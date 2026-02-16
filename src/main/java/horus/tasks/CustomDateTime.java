package horus.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An object representing a time which may or may not contain a date and a time of day.
 */
public class CustomDateTime {
    LocalDateTime dateTime;
    String remainingStr;

    /**
     * Creates a CustomDateTime object based on dateTimeStr.
     *
     * @param dateTimeStr String which may or may not start with a date and time.
     */
    public CustomDateTime(String dateTimeStr) {
        LocalDate date = null;
        LocalTime time = null;
        remainingStr = "";
        dateTimeStr = dateTimeStr.strip();
        String[] dateTimeArray = dateTimeStr.split(" ", 3);

        for (int i = 0; i < dateTimeArray.length; i++ ) {
            try {
                date = LocalDate.parse(dateTimeArray[i], DateTimeFormatter.ofPattern("d/M/yyyy") );
                continue;
            } catch (DateTimeException e) {
                //Will save contents to remainingStr instead of initializing a date
            }
            try {
                time = LocalTime.parse(dateTimeArray[i], DateTimeFormatter.ofPattern("HHmm") );
                continue;
            } catch (DateTimeException e) {
                //Will save contents to remainingStr instead of initializing a time
            }

            remainingStr += dateTimeArray[i];
            remainingStr += " ";
        }

        if (date == null && time == null) {
            dateTime = null;
        } else {
            date = date == null? LocalDate.now(): date;
            time = time == null? LocalTime.of(23,59): time;
            dateTime = LocalDateTime.of(date, time);
        }

        remainingStr = remainingStr.strip();
    }

    /**
     * Checks if CustomDateTime contains a valid dateTime.
     *
     * @return true if CustomDateTime contains a valid dateTime and false otherwise.
     */
    public boolean checkDateTime() {
        return !(dateTime == null);
    }

    /**
     * Postpone the date represented by CustomDateTime.
     *
     * @param days Number of days to postpone the date by.
     */
    public void plusDays(int days) {
        dateTime = dateTime.plusDays(days);
    }

    /**
     * Advance the date represented by CustomDateTime.
     *
     * @param days Number of days to advance the date by.
     */
    public void minusDays(int days) {
        dateTime = dateTime.minusDays(days);
    }

    /**
     * Returns a string similar to the input dateTimeStr
     *         with dates and times converted to "MMM d yyyy" and "H:mma" formats.
     */
    @Override
    public String toString() {
        if (dateTime == null) {
            return remainingStr;
        }
        String dateStr = dateTime.toLocalDate().format( DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        String timeStr = dateTime.toLocalTime().format( DateTimeFormatter.ofPattern("h:mma"))  + " ";
        return dateStr + timeStr + remainingStr;
    }

    @Override
    public boolean equals(Object inputObj){
        if (!(inputObj instanceof CustomDateTime dateTimeInput)){
            return false;
        }

        boolean isSameDateTime = (this.dateTime == dateTimeInput.dateTime) || this.dateTime.equals(dateTimeInput.dateTime);
        boolean isSameRemainingStr = (this.remainingStr == dateTimeInput.remainingStr) || this.remainingStr.equals(dateTimeInput.remainingStr);

        return isSameDateTime && isSameRemainingStr;
    }

    /**
     * Returns a string similar to the input dateTimeStr
     *
     * @return A string from which a similar CustomDateTime object may be initialized from
     */
    public String getData() {
        String outputDateTimeStr = "";
        if (dateTime != null) {
            String dateStr = dateTime.toLocalDate().format( DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ";
            String timeStr = dateTime.toLocalTime().format( DateTimeFormatter.ofPattern("HHmm"))  + " ";
            outputDateTimeStr += dateStr + timeStr;
        }
        outputDateTimeStr += remainingStr;

        assert new CustomDateTime(outputDateTimeStr).equals(this);
        return outputDateTimeStr;
    }
}
