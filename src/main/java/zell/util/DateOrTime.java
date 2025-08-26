package zell.util;

import zell.exception.ZellException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOrTime {
    private LocalDate date;
    private LocalDateTime dateTime;

    public DateOrTime(String dateOrTimeString) throws ZellException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.dateTime = LocalDateTime.parse(dateOrTimeString, formatter);
        } catch (DateTimeParseException e) {
            try {
                this.date = LocalDate.parse(dateOrTimeString);
            } catch (DateTimeParseException de) {
                throw new ZellException("Date or DateTime should be in the respective formats " +
                        "yyyy-MM-dd or yyyy-MM-dd HH:mm.\nFor example: 2019-12-01 or 2019-12-01 18:30");
            }
        }
    }

    public String originalFormat() {
        String original = "";

        if (this.date != null) {
            original = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (this.dateTime != null) {
            original = this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        return original;
    }

    @Override
    public String toString() {
        String formatted = "";

        if (this.date != null) {
            formatted = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (this.dateTime != null) {
            formatted = this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        }

        return formatted;
    }
}
