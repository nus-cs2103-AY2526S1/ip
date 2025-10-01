package cat;

/**
 * Represents a Todo task, which is a type of Task without a specific deadline.
 * Inherits from the Task class.
 */

public class Todo extends Task {

    /**
     * Creates a new Todo task with the given name.
     * By default, the task is not completed.
     *
     * @param name The name or description of the Todo task.
     */
    public Todo(String name){
        super(name);
    }



    /**
     * Returns the status of the Todo task as a formatted string.
     * Format: [T][status] name
     * Example: [T][X] Buy groceries
     *
     * @return A string representing the task's completion status.
     */
    @Override
    public String getStatus(){
        String stat=  isDone ? "[X]" : "[ ]";
        return "[T]" + stat + " " + name;
    }


    /**
     * Returns a formatted string for storing or saving the Todo task.
     * Format: T | status | name
     * Status: "1" if done, "0" if not done
     *
     * @return A string representing the formatted Todo task for storage.
     */
    @Override
    public String getFormat(){
        String d=  isDone ? "1" : "0";
        return "T" + " | " + d + " | " + name;
    }
}
