package horus.tasks;

import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Represents the 3 different task classes.
     */
    public enum taskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Saves the content of the task list to an array to be saved to a file.
     *
     * @return Array of strings with containing task data to be saved to a file.
     */
    public String[] saveToFile() {
        String[] fileContents = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            fileContents[i] = currentTask.getTaskData();
        }

        return fileContents;
    }

    /**
     * Adds the tasks represented in fileData to the task list
     *
     * @param fileData Array of strings representing contents of a save file.
     * @return String to be printed by Ui.
     */
    public String readFromFile(String[] fileData)  {
        String out = "";
        int addedTasks = 0;

        for (int i = 0; i < fileData.length; i++) {
            try {
                tasks.add(Task.readTaskData(fileData[i]));
                addedTasks++;
            } catch (InvalidInputException e) {
//                out += "Local file corrupted. " + fileData[i] + " is not a valid task\n";
                out += "Corruption detected. The data shard " + fileData[i] + " bears heresy unfit for duty.\n";
            }
        }
//        out += addedTasks + " tasks retrieved.";
        out +=  "By the Emperor’s grace, " + addedTasks + " sworn duties have been recovered.\n";

        return out;
    }

    /**
     * Adds a task to the TaskList and prints a corresponding message if successful.
     *
     * @param taskStr String containing task description and other details such as /to /from and /by.
     * @param taskType enum representing type of task to be added.
     * @return String to be printed by Ui.
     * @throws InvalidInputException If taskStr is invalid.
     */
    public String addTask(String taskStr, taskTypes taskType) throws InvalidInputException {
        if (taskStr == "") {
//            throw new InvalidInputException("Error: Description of task cannot be empty.");
            throw new InvalidInputException("Error: A warrior’s oath cannot be sworn upon silence. " +
                    "The task description must be spoken.");
        }
        switch(taskType) {
        case TODO:
            tasks.add(new ToDoTask(taskStr));
            break;
        case DEADLINE:
            if(!taskStr.contains("/by")){
//                throw new InvalidInputException("Error: Deadline tasks must have a deadline (denoted with /by).");
                throw new InvalidInputException(
                        "Error: A mission of deadline requires its appointed hour, denoted by /by.");
            }

            if (taskStr.contains("/every")){
                tasks.add(new RecurringDeadlineTask(taskStr));
            } else {
                tasks.add(new DeadlineTask(taskStr));
            }

            break;
        case EVENT:
            if(!taskStr.contains("/from") || !taskStr.contains("/to")){
//                throw new InvalidInputException(
//                        "Error: Event tasks must have a start and end (denoted with /from and /to).");
                throw new InvalidInputException(
                        "Error: An event must be bound by inception and cessation, /from and /to mark its span.");
            }
            if (taskStr.contains("/every")){
                tasks.add(new RecurringEventTask(taskStr));
            } else {
                tasks.add(new EventTask(taskStr));
            }

            break;
        }

        Task addedTask = tasks.get(tasks.size()-1);
//        return "Got it. I've added this task:\n  " + addedTask.toString() + "\n" +
//                "Now you have " + tasks.size() + " tasks in the list.\n";

        return "It is done. A new duty is inscribed upon your litany:\n " + addedTask.toString() +
                "\nYour sacred roster now bears " + tasks.size() + " charges.";

    }

    //Prints the contents of the taskList
    /**
     * Returns a string representing the contents of taskList to be printed.
     *
     * @return A string with each line representing a task in tasklist.
     */
    public String showList() {
        String out = "";
        for(int i = 0; i < tasks.size(); i++) {
            int taskId = i+1;
            Task currentTask = tasks.get(i);
            out += (taskId + "." + currentTask.toString() + "\n");
        }
        return out;
    }

    /**
     * Marks selected task as done.
     *
     * @param taskId String representing the index of task to be marked.
     * @return String to be printed by Ui.
     * @throws InvalidInputException If taskId is not a valid task index.
     */
    public String markTask(String taskId) throws InvalidInputException {
        int taskIndex = Integer.parseInt(taskId) - 1;
        if(taskIndex >= tasks.size()) {
//            throw new InvalidInputException(
//                    "Error: No task at index " + taskId + ". Please input a valid task number." );
            throw new InvalidInputException(
                    "Error: No such duty exists at index " + taskId + ". Speak a valid number, or be silent." );
        }
        tasks.get(taskIndex).mark();
//        return "Nice! I've marked this task as done:\n  " + tasks.get(taskIndex) + "\n";
        return "The oath is fulfilled:\n " + tasks.get(taskIndex) + "\nLet it be recorded in the annals of duty.";
    }

    /**
     * Unmarks selected task
     *
     * @param taskId String representing the index of task to be unmarked
     * @return String to be printed by Ui
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public String unmarkTask(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }
        tasks.get(task_index).unmark();
//        return "OK, I've marked this task as not done yet:\n  " + tasks.get(task_index) + "\n";
        return "The charge remains incomplete:\n " + tasks.get(task_index)
                + "\nBear it forward until victory is achieved.";
    }

    /**
     * Deletes selected task
     *
     * @param taskId String representing the index of task to be deleted
     * @return String to be printed by Ui
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public String delete(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }

//        String out = "Noted. I've removed this task:\n  " + tasks.get(task_index);
        String out = "The burden is lifted. This duty is struck from your record:\n " + tasks.get(task_index);
        tasks.remove(task_index);
//        out += "\nNow you have " + tasks.size() + " tasks in the list.\n";
        out += "\nYour ledger now holds " + tasks.size() + " sacred charges.";
        return out;
    }

    /**
     * Finds all tasks which contains input substring
     *
     * @param substring String which may be contained by tasks
     * @return A string with each line representing a task in TaskList which contains substring
     */
    public String find(String substring) {
        String out = "";
        int matchingTasksCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String taskStr = tasks.get(i).toString();
            if (taskStr.contains(substring)) {
                matchingTasksCount++;
                out += matchingTasksCount + "." + taskStr + "\n";
            }
        }

        if (matchingTasksCount == 0) {
//            return "There are no matching tasks in your list.";
            return "Your search yields only silence. No duties align with your query.";
        } else {
//            return "Here are the matching tasks in your list:\n" + out;
            return "By vigilance, the following tasks answer your summons:\n" + out;
        }


    }
}