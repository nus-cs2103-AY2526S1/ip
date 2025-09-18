package marcus.task;

public class Task {
    String description;
    boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Switches complete status from false to true.
     *
     * @return Success of switching status to complete.
     */
    public String markComplete() {
        if (this.isCompleted) {
            return "This chapter has already been completed before";
        } else {
            this.isCompleted = true;
            return "A brand new chapter complete!\n" + this;
        }

    }

    /**
     * Switches complete status from true to false.
     *
     * @return Success of switching status to incomplete.
     */
    public String unmarkComplete() {
        if (!this.isCompleted) {
            return "You haven't started on this chapter yet";
        } else {
            this.isCompleted = false;
            return "You forgot about this chapter...\n" + this;
        }

    }

    /**
     * Returns an icon based on status of completion of task.
     *
     * @return An icon used to represent whether a task has been completed.
     */
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getSaveFileString() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     * The format of the string representation is for the user interface.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
