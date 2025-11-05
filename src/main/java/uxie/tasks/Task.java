package uxie.tasks;

import java.time.LocalDateTime;
import java.util.List;

import uxie.exceptions.UxieIllegalOpException;

/**
 * Tasks are tasks to be completed.
 *
 * @author junyan-k
 */
public abstract class Task {

    /** Whether Task is completed. */
    private boolean isCompleted;

    /** Description of Task. */
    private String desc;

    /** Tag of Task. */
    private String tag;

    /**
     * Generates Task with description.
     * Task is incomplete by default.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isCompleted = false;
        this.tag = "";
    }

    /**
     * Generates Task with description and specified completion status.
     */
    public Task(boolean isCompleted, String desc) {
        this.desc = desc;
        this.isCompleted = isCompleted;
        this.tag = "";
    }

    /**
     * Returns whether Task is completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Marks Task as complete.
     *
     * @throws UxieIllegalOpException if Task is already complete.
     */
    public void markCompleted() throws UxieIllegalOpException {
        if (!this.isCompleted) {
            this.isCompleted = true;
        } else {
            throw new UxieIllegalOpException("This task is already complete.");
        }
    }

    /**
     * Marks Task as incomplete.
     *
     * @throws UxieIllegalOpException if Task is already incomplete.
     */
    public void markIncomplete() throws UxieIllegalOpException {
        if (this.isCompleted) {
            this.isCompleted = false;
        } else {
            throw new UxieIllegalOpException("This task is already incomplete.");
        }
    }

    /**
     * Tags this Task with given String.
     * If Task previously had a tag, it is overwritten.
     *
     * @param tag new tag for Task.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns description of Task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Returns task type symbol.
     */
    public abstract String getSymbol();

    /**
     * Returns tag of Task.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Returns all time arguments in order.
     */
    public abstract List<LocalDateTime> getTimeArguments();

    /**
     * Returns this Task as String.
     * Format: "[<'X' if complete, ' ' if not>] {@link #desc}"
     */
    @Override
    public String toString() {
        String tagString = tag.isBlank() ? "" : String.format(" (tag: %s)", this.tag);
        return String.format("[%s] %s%s",
                this.isCompleted ? "X" : " ", this.desc, tagString);
    }

}
