package broccoli.Tasks;

/**
 * Represents a basic task with completion status and description.
 */
public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
    }

   public String getDescription(){
        return this.description;
   }

    public boolean getDone() {

        return isDone;
    }

    /**
     * Returns the formatted text representation for file storage.
     * Converts the task into a standardized string format that can be
     * saved to a file and later parsed back into a Task object.
     * The format includes task type, completion status, and description.
     *
     * @return The task formatted as a string for storage
     */
    public String taskText() {
        String isComplete = this.isDone ? "1" : "0";
        String taskText = "todo " + isComplete + " " + this.description;
        return taskText;
    }

    /**
     * Parses a task from stored text format and creates the appropriate task type.
     * Determines the task type from the first character and creates TodoTask,
     * DeadlineTask, or EventTask instances accordingly. Handles task completion
     * status and type-specific attributes.
     *
     * @param task The stored task string to parse, must start with T, D, or E
     * @return The parsed Task object of the appropriate subtype (TodoTask, DeadlineTask, or EventTask)
     * @throws IllegalArgumentException if the task format is invalid or doesn't start with proper task type
     */
    public static Task parseTask(String task){
        if(task.startsWith("T")){
            String[] description = task.split(" \\| ");
            String taskDescription = description[2];
            boolean isDone = description[1].equals("0") ? false : true;
            Task taskToAdd = new TodoTask(taskDescription, isDone);
            return taskToAdd;
        } else if(task.startsWith("D")){
            String[] description = task.split(" \\| ");
            String taskDescription = description[2];
            boolean isDone =description[1].equals("0")? false : true;
            String deadlineTime = description[3];
            Task taskToAdd = new DeadlineTask(taskDescription,deadlineTime, isDone);
            return taskToAdd;
        } else if(task.startsWith("E")){
            String[] description = task.split(" \\| ");
            String taskDescription = description[2];
            boolean isDone =description[1].equals("0")? false : true;
            String eventTime = description[3];
            String[] eventTime2 = eventTime.split("to");
            Task taskToAdd = new EventTask(taskDescription, eventTime2[0], eventTime2[1],isDone);
            return taskToAdd;
        } else {
            throw new IllegalArgumentException("OOPS! Please enter a correct task start with proper task type(todo/deadline/event)");
        }
    }

    /**
     * Creates a task from user input command and returns the appropriate task type.
     * Parses user commands starting with "todo", "deadline", or "event" and creates
     * the corresponding task object. The method extracts the task description and
     * any additional parameters from the input string.
     *
     * @param task The user input string containing the task command and description
     * @return A Task object of the appropriate subtype based on the command
     * @throws IllegalArgumentException if the task command is invalid or doesn't start with a recognized task type
     */
    public static Task checkTask(String task) {
        if(task.startsWith("todo")){
            String description = task.substring(4);
            try{
           Task todoTask = new TodoTask(description);
                return todoTask;
            } catch (Exception e) {
              throw new IllegalArgumentException(e.getMessage());
            }
        } else if(task.startsWith("deadline")){
            String description = task.substring(8);
            try{
               Task deadlineTask = new DeadlineTask(description);
                return deadlineTask;
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else if(task.startsWith("event")){
            String description = task.substring(5);
            try{
              Task  eventTask = new EventTask(description);
                return eventTask;
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("OOPS! Please enter a correct task start with proper task type(todo/deadline/event)");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }


    public String toString() {
        return getStatusIcon() + this.description;
    }
//    public static void main(String[] args){
//        Broccoli.Broccoli.Tasks.Broccoli.Broccoli.Tasks.Task task = new Broccoli.Broccoli.Tasks.Broccoli.Broccoli.Tasks.Task("do homework");
//        System.out.println(task.toString());
//    }
}
