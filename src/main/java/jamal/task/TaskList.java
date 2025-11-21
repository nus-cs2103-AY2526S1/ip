package jamal.task;

import java.util.ArrayList;
import java.util.List;

import jamal.exception.InvalidCommandException;

/**
 * Tasklist contains an Arraylist of Task objects to perform command like operations on
 *
 * @return various print statements in accordance to type of command calling on method
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Initialisation of Arraylist to store Task objects
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialisation of Arraylist to store Task objects from a list of datastring received from Storage or a datafile
     *
     * @param taskDataStrings list of string of formatted task data from Storage
     *
     */
    public TaskList(List<String> taskDataStrings) {
        this.taskList = new ArrayList<>();
        for (String line : taskDataStrings) {
            String[] lineInfo = line.split("`");
            String description = lineInfo[3];
            int priority = Integer.parseInt(lineInfo[2]);
            switch (lineInfo[0]) {
            case "T":
                Task todoTask = new ToDo(description, priority);
                this.taskList.add(todoTask);
                break;
            case "D":
                Task deadlineTask = new Deadline(description, priority, lineInfo[4]);
                this.taskList.add(deadlineTask);
                break;
            case "E":
                Task eventTask = new Event(description, priority, lineInfo[4], lineInfo[5]);
                this.taskList.add(eventTask);
                break;
            default:
                throw new IllegalArgumentException("Invalid Argument");
            }
        }
    }

    /**
     * Lists all tasks in tasklist, default by order of insertion
     *
     * @return statement of all tasks in tasklist
     */
    public String list() {
        StringBuilder listString = new StringBuilder();
        listString.append("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return listString.toString();
    }

    /**
     * Lists all tasks in tasklist that are ongoing
     *
     * @return  statement of all tasks in tasklist that are ongoing, dependent on task typing
     */
    public String listOngoing() {
        StringBuilder listString = new StringBuilder();
        listString.append("These are your ongoing tasks:\n");
        taskList.stream()
                .filter(t -> t.isOngoing())
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Lists all tasks in tasklist that are upcoming
     *
     * @return statement of all tasks in tasklist that are upcoming, dependent on task typing
     */
    public String listUpcoming() {
        StringBuilder listString = new StringBuilder();
        listString.append("These are your upcoming tasks:\n");
        taskList.stream()
                .filter(t -> t.isUpcoming())
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Lists all tasks in tasklist that are overdue
     *
     * @return print statement of all tasks in tasklist that are overdue, dependent on task typing
     */
    public String listOverdue() {
        StringBuilder listString = new StringBuilder();
        listString.append("These are your overdue tasks:\n");
        taskList.stream()
                .filter(t -> t.isOverdue())
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Marks tasks in tasklist
     * Updates task as done
     *
     * @param idx line to mark
     * @return statement of task marked
     * @throws InvalidCommandException Exception thrown if index is out of range of tasklist
     */
    public String mark(int idx) throws InvalidCommandException {
        if (idx >= taskList.size()) {
            throw new InvalidCommandException("Task out of range!");
        }
        Task taskToMark = taskList.get(idx);
        taskToMark.markAsDone();
        assert taskToMark.isDone : "Task is not marked";
        return "Solid work! I've marked this task as done:\n" + taskToMark.toString() + "\n";
    }

    /**
     * Unmarks tasks in tasklist
     * Updates task as not done
     *
     * @param idx line to unmark
     * @return statement of task unmarked
     * @throws InvalidCommandException Exception thrown if index is out of range of tasklist
     */
    public String unmark(int idx) throws InvalidCommandException {
        if (idx >= taskList.size()) {
            throw new InvalidCommandException("Task out of range!");
        }
        Task taskToUnmark = taskList.get(idx);
        taskToUnmark.markAsUndone();
        assert !taskToUnmark.isDone : "Task is not unmarked";
        return "Aite bet, I've marked this task as not done yet:\n" + taskToUnmark.toString() + "\n";
    }

    /**
     * Prioritizes tasks in tasklist
     * Updates task with priority number
     *
     * @param idx line to prioritize
     * @param priority number to set for priority
     * @return statement of task prioritized
     * @throws InvalidCommandException Exception thrown if index is out of range of tasklist
     */
    public String prioritize(int idx, int priority) throws InvalidCommandException {
        if (idx >= taskList.size()) {
            throw new InvalidCommandException("Task out of range!");
        }
        Task taskToPrioritize = taskList.get(idx);
        taskToPrioritize.setPriority(priority);
        assert taskToPrioritize.priority == priority : "Task priority mismatch";
        return "Aite bet, I've set this task with priority " + priority + ":\n"
                    + taskToPrioritize.toString() + "\n";
    }
    /**
     * Prints task statements from the tasklist that match the input string
     *
     * @param match string to match task description
     * @return statement of tasks that contain the matching string in its description
     * */
    public String find(String match) {
        StringBuilder listString = new StringBuilder();
        listString.append("Here are your matching tasks in your list:\n");
        taskList.stream()
                .filter(t -> t.getDescription()
                        .contains(match))
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Deletes events in tasklist
     *
     * @param idx index of element to be deleted
     * @return statement of deleted task
     * @throws InvalidCommandException Exception thrown if index is out of range of tasklist
     */
    public String delete(int idx) throws InvalidCommandException {
        if (idx >= taskList.size()) {
            throw new InvalidCommandException("Task out of range!");
        }
        Task removal = taskList.get(idx);
        taskList.remove(removal);
        return "Sure thing, I've deleted this task:\n" + removal.toString() + "\n";
    }

    /**
     * Adds events into tasklist
     *
     * @return statement of added task
     */
    public String addTask(Task task) {
        try {
            taskList.add(task);
            return "Gotcha. I've added this task:\n" + task.toString() + "\n"
                            + "Now you've got " + taskList.size() + " tasks in the list." + "\n";
        } catch (Exception e) {
            return "Unable to add task, please try again!";
        }
    }
}
