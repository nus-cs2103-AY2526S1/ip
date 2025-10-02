package com.neokortex.task;

import com.neokortex.time.Time;

/**
 * Represents an event with a specific start and end time.
 * <p>
 * An {@code DeadlineTask} extends the base {@code Task} class with additional
 * time components, representing when the event start and ends. Both times are
 * represented by {@link Time} objects.
 * </p>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * E|isMarked|description|startTimeSerialization|endTimeSerialization
 * </pre>
 * Where {@code startTimeSerialization} and {@code endTimeSerialization} are
 * the results of {@link Time#serialize()}.
 *
 * @see Task
 * @see Time
 */
public class EventTask extends Task {
    private final Time startTime;
    private final Time endTime;

    /**
     * Constructs a new {@code EventTask} with the specified description,
     * start time, and end time. The task is initially unmarked (not completed).
     *
     * @param description the event description.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     */
    public EventTask(String description, Time startTime, Time endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new EventTask with the specified description,
     * start time, end time, and completion status.
     *
     * @param description the event description
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     * @param isMarked the initial completion status
     *                 (true for completed, false for incomplete)
     */
    public EventTask(String description, Time startTime,
                     Time endTime, boolean isMarked) {
        super(description, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    /**
     * Returns a formatted string representation of this {@code EventTask}.
     * <p>
     * Format: {@code [E][markStatus] description, from startTime to endTime}
     * </p>
     *
     * @return a formatted string representation including the event details and time range
     */
    @Override
    public String toString() {
        return String.format(
                "%s, from %s to %s",
                super.toString(),
                startTime.toString(),
                endTime.toString()
        );
    }

    /**
     * Serializes this {@code EventTask} to a string representation for storage.
     * <p>
     * Format: {@code E|isMarked|description|startTimeSerialization|endTimeSerialization}
     * </p>
     * <p>
     * Where {@code startTimeSerialization} and {@code endTimeSerialization} are
     * the results of {@link Time#serialize()}.
     * </p>
     *
     * @return a string representation of this {@code EventTask} for storage
     * @see Time#serialize()
     * @see Task#baseSerialization()
     */
    @Override
    public String serialize() {
        return String.format(
                "%s|%s|%s",
                super.baseSerialization(),
                startTime.serialize(),
                endTime.serialize()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof EventTask otherTask) {
            return this.description.equals(otherTask.description)
                    && this.isMarked() == otherTask.isMarked()
                    && this.startTime.equals(otherTask.startTime)
                    && this.endTime.equals(otherTask.endTime);
        }

        return false;
    }
}
