package Skye.classes;
public class Task {
    protected String taskName;
    protected boolean isComplete;
    protected static final String DATE_TIME_FORMAT = "MMM dd yyyy";

    public Task(String name) {
        taskName = name;
        isComplete = false;
    }

    public void setTaskComplete() {
        isComplete = true;
    }

    public void setTaskIncomplete() {
        isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    public String getTaskType() {
        return " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }

    /**
     * Gets the string for writing into data file
     * @return String of the correct format for data file write
     */
    public String getTaskData() {
        return String.format("%s|%s|%s", getTaskType(), (isComplete ? "Y" : "N"), taskName);
    }

    /**
     * Check if the task name contains the given string
     * @param name given string to check against
     * @return boolean
     */
    public boolean containName(String name){
        return taskName.contains(name);
    }
}
