package hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a note with some informational text and a timestamp indicating when it was created.
 */
public class Note {
    private String information;
    private LocalDateTime timeStamp;

    /**
     * Constructs a {@code Note} with the specified information and timestamp.
     *
     * @param information the content of the note
     * @param timeStamp   the time at which the note was created
     */
    public Note(String information, LocalDateTime timeStamp) {
        this.information = information;
        this.timeStamp = timeStamp;
    }

    /**
     * Returns a string representation of the note, including its information and creation timestamp.
     *
     * @return a formatted string with the note’s text followed by its creation time
     */
    @Override
    public String toString() {
        String timeAsString = new DateFormatter().formatTime(timeStamp);
        return String.format("%s\ncreated: %s", this.information, timeAsString);
    }
}
