package junny.tasks;

import junny.TaskTypes;

/**
 * Represents a task with a description, completion status and task type.
 * A Task can be marked as done or not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskTypes type;

    /**
     * Creates a Task with the given description.
     *
     * @param description The description of the task.
     * @param type The type from enum class TaskTypes
     */
    public Task(String description, TaskTypes type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public TaskTypes getType() {
        return type;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Parse the task to the format for saving
     * @return A string representation of the task
     */
    public abstract String toFileString();

    /**
     * Parse the string from local file to tasks
     * @param s The line of string from the file
     * @return A task object representing the string
     */
    // this still works when changing to LocalDate, since the constructor for
    // ddl is (des, String by), so it does conversion automatically
    public static Task convertToTask(String s) {
        String[] getTaskType = s.split(" \\| ");
        String type = getTaskType[0];
        boolean isDone = getTaskType[1].equals("1");
        String task = getTaskType[2];
        switch(type) {
        case "T":
            Todo todo = new Todo(task);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            Deadline ddl = new Deadline(task, getTaskType[3]);
            if (isDone) {
                ddl.markAsDone();
            }
            return ddl;
        case "E":
            String time = getTaskType[3];
            // form is fromTo[0], to is fromTo[1]
            String[] fromTo = time.split(" to ");
            Event e = new Event(task, fromTo[0], fromTo[1]);
            if (isDone) {
                e.markAsDone();
            }
            return e;
        default:
            return null;
        }
    }
}
