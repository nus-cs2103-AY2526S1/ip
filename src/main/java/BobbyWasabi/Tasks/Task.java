package BobbyWasabi.Tasks;

public class Task {

    private String description;
    private Boolean isMarked;

    public Task(String description, Boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void setIsMarked(Boolean bool) {
        this.isMarked = bool;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getIsMarked() {
        return this.isMarked;
    }

    /**
     * Returns the Mark/Unmarked Checkbox depending on the Boolean value of isMarked for the BobbyWasabi.BobbyWasabi.Tasks.Task
     *
     * @return Mark/Unmarked Checkbox.
     */
    public String checked() {
        if (this.isMarked) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns the String representation of task
     * Has the Marked/UnMarked Checkbox and then the task's description
     *
     * @return String representation of task.
     */
    public String toString() {
        return this.checked() + " " + this.description;
    }

    public String getData() {
        return "";
    }
}
