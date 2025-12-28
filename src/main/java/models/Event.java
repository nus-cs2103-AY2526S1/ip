package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent Event
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs a Event with name and isDone = false
     */
    public Event(String name, LocalDate from, LocalDate to, String tag) {
        setName(name);
        setIsDone(false);
        setTag(tag);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a Event with name and isDone
     */
    public Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        setName(name);
        setIsDone(isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a Event with name and isDone
     */
    public Event(String name, boolean isDone, LocalDate from, LocalDate to, String tag) {
        setName(name);
        setIsDone(isDone);
        setTag(tag);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a Event with name and isDone
     */
    @JsonCreator
    public Event(@JsonProperty("name") String name,
            @JsonProperty("isDone") boolean isDone,
            @JsonProperty("from") String from,
            @JsonProperty("to") String to,
            @JsonProperty("tag") String tag) {
        setName(name);
        setIsDone(isDone);
        setTag(tag);
        this.from = LocalDate.parse(from, DATE_FORMATTER);
        this.to = LocalDate.parse(to, DATE_FORMATTER);
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    /**
     * Custom getter for JSON serialization that returns due date as String for
     * deserialization
     */
    @JsonGetter("from")
    public String getFromAsString() {
        return from != null ? from.format(DATE_FORMATTER) : null;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    /**
     * Custom getter for JSON serialization that returns due date as String
     */
    @JsonGetter("to")
    public String getDueAsString() {
        return to != null ? to.format(DATE_FORMATTER) : null;
    }

    @Override
    public String toString() {
        if (super.getTag().equals(null) || super.getTag().isEmpty()) {
            return String.format("[E]%s (from: %s to: %s)",
                    super.toString(), from.format(DATE_FORMATTER), to.format(DATE_FORMATTER));
        }
        return String.format("[E]%s (from: %s to: %s) #tags: %s",
                super.toString(), from.format(DATE_FORMATTER), to.format(DATE_FORMATTER), super.getTag());
    }
}
