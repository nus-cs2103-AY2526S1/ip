package bobby.task;

import java.util.ArrayList;
import java.util.List;

import bobby.exception.BobbyException;

/**
 * Stores the task list and allows users to add, delete, mark, unmark tasks in the task list
 */
public class TaskList {
    private static final String INVALID_DATE_FORMAT_MESSAGE = "Use the yyyy-MM-dd HHmm format.";
    private ArrayList<Task> taskList;

    /**
     * Loads the taskList String from Storage back into a ArrayList of type Task
     * @param tasks String from Storage
     * @throws BobbyException
     */
    public TaskList(List<String> tasks) throws BobbyException {
        this.taskList = new ArrayList<>();
        int taskType;
        boolean isMark;

        for (String task : tasks) {
            String[] split = task.split(" \\| ");
            taskType = Integer.parseInt(split[0]);
            isMark = split[1].equals("1");
            this.addTask(taskType, isMark, split[2]);
        }
    }

    public Task makeTask(int taskType, boolean isMark, String content) throws BobbyException {
        if (taskType == 0) {
            return new ToDo(content, isMark);
        }
        String[] split = content.split(" /");
        if (taskType == 1) {
            if (split.length == 2 && split[1].substring(0, 2).equalsIgnoreCase("by")) {
                return new Deadline(split[0], isMark, split[1].substring(3));
            } else {
                throw new BobbyException(INVALID_DATE_FORMAT_MESSAGE);
            }
        }
        if (split.length == 3
                && split[1].substring(0, 4).equalsIgnoreCase("from")
                && split[2].substring(0, 2).equalsIgnoreCase("to")
        ) {
            return new Event(split[0], isMark, split[1].substring(5), split[2].substring(3));
        } else {
            throw new BobbyException(INVALID_DATE_FORMAT_MESSAGE);
        }
    }

    /**
     * Adds the task to the taskList. Bobby should first check if the input is more than just the command,
     * if not throw BobbyException when user sends input.
     * @param taskType 0 is ToDo, 1 is Deadline, 2 is Event
     * @param content  includes the description, and the dates if necessary. Does not include command.
     * @param isMark   whether the task has been marked
     * @throws BobbyException if content does not match required format
     */
    public void addTask(int taskType, boolean isMark, String content) throws BobbyException {
        int oldSize = taskList.size();
        taskList.add(makeTask(taskType, isMark, content));
        assert oldSize + 1 == taskList.size();
    }

    /**
     * deletes a task from the taskList. Parser checks if taskNum is not an int
     * @param taskNum Number of the task user wishes to delete. 1-indexed
     * @throws BobbyException if task does not exist
     */
    public void deleteTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't delete a task that does not exist");
        }
        taskList.remove(taskNum - 1);
    }

    /**
     * marks a task from the taskList. Parser checks if taskNum is not an int
     *
     * @param taskNum Number of the task user wishes to delete. 1-indexed
     * @throws BobbyException if task does not exist
     */
    public void markTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't mark a task that does not exist");
        }
        taskList.get(taskNum - 1).mark();
    }

    /**
     * unmarks a task from the taskList. Parser checks if taskNum is not an int
     *
     * @param taskNum Number of the task user wishes to delete. 1-indexed
     * @throws BobbyException if task does not exist
     */
    public void unmarkTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't unmark a task that does not exist");
        }
        taskList.get(taskNum - 1).unmark();
    }

    /**
     * retrieve task for displaying
     *
     * @param taskNum 1-indexed
     * @return task
     * @throws BobbyException if taskNum is out of range of taskList
     */
    public Task getTask(int taskNum) throws BobbyException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new BobbyException("You can't delete a task that does not exist.");
        }
        return taskList.get(taskNum - 1);
    }

    /**
     * retrieve last task for displaying
     *
     * @return last task in task list
     * @throws BobbyException
     */
    public Task getLastTask() throws BobbyException {
        return this.getTask(taskList.size());
    }

    /**
     * looks for tasks whose description fits the given string
     *
     * @param search string that user inputs
     * @return a TaskList of Tasks that fit the given search string
     * @throws BobbyException
     */
    public TaskList findTasks(String search) throws BobbyException {
        List<String> result = new ArrayList<>();
        for (Task task: taskList) {
            if (task.description.contains(search)) {
                result.add(task.toStorage());
            }
        }
        return new TaskList(result);
    }

    public void editTask(int taskNum, int taskType, String input) throws BobbyException {
        Task task = this.getTask(taskNum);
        String description = task.getDescription();
        boolean isMark = task.getIsMark();
        String formattedInput = description + " " + input;
        taskList.set(taskNum - 1, makeTask(taskType, isMark, formattedInput));
    }

    public void snoozeTasks(String input) throws BobbyException {
        String[] split = input.split(" /");
        int taskNum;
        try {
            taskNum = Integer.parseInt(split[0]);
        } catch (NumberFormatException e) {
            throw new BobbyException("Snooze task number must be an integer.");
        }
        if (taskNum < 0 || taskNum > taskList.size()) {
            throw new BobbyException("Task does not exist to snooze");
        }
        Task task = this.getTask(taskNum);
        int taskType = task.getTaskType();
        if (taskType == 0) {
            throw new BobbyException("ToDos cannot be snoozed");
        } else if (taskType == 1) {
            if (split.length != 2) {
                throw new BobbyException("Editing a deadline must be in the format 'snooze {taskNum} /by {new date}");
            } else if (!split[1].substring(0, 3).equalsIgnoreCase("by ")) {
                throw new BobbyException("Editing a deadline must be in the format 'snooze {taskNum} /by {new date}");
            }
        } else {
            if (split.length != 3) {
                throw new BobbyException(
                        "Editing an event must be in the format 'snooze {taskNum} /from {newDate} /to {newDate}"
                );
            } else if (!split[1].substring(0, 5).equalsIgnoreCase("from ")
                    && !split[2].substring(0, 3).equalsIgnoreCase("to ")) {
                throw new BobbyException(
                        "Editing an event must be in the format 'snooze {taskNum} /from {newDate} /to {newDate}"
                );
            }
        }
        editTask(taskNum, taskType, input.split(" ", 2)[1]);
    }

    /**
     * transforms current taskList into a List of String to be saved by Storage.
     * Example Format:
     * 0 | 1 | task | description
     * 1 | 0 | task | description | by
     * 2 | 1 | task | description | from | to
     * Same as above, 0 for ToDo, 1 for Deadline, 2 for Event
     *
     * @return List of tasks in String format.
     */
    public List<String> saveTasks() {
        List<String> output = new ArrayList<>();

        for (Task task : taskList) {
            output.add(task.toStorage());
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Here are your tasks:\n");
        for (int i = 1; i <= taskList.size(); i++) {
            str.append(i + ". " + taskList.get(i - 1) + "\n");
        }
        return str.toString();
    }
}
