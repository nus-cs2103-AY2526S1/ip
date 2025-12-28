package thecoolerduke.feature;

import java.time.LocalDateTime;
import java.util.ArrayList;

import thecoolerduke.feature.tasks.Deadline;
import thecoolerduke.feature.tasks.Event;
import thecoolerduke.feature.tasks.ToDo;
import thecoolerduke.main.Priority;

/**
 * Acts as a list-based task manager.
 * Ability to add, view, mark/unmark, and delete tasks.
 */
public class TaskManager {
    private final ArrayList<Task> taskList;
    private final TaskHistoryManager thm;

    /**
     * Initialises the TaskManager class.
     * After initialisation, you must run startupTaskManager() to fetch task history.
     *
     * @param thm The initialised TaskHistoryManager
     * @see TaskHistoryManager
     */
    public TaskManager(TaskHistoryManager thm) {
        this.taskList = new ArrayList<>();
        this.thm = thm;
    }
    //returns a single task as "X. taskName"
    private String viewTask(int taskIdx) {
        return String.format("%d. %s", taskIdx + 1, taskList.get(taskIdx).showTask());
    }

    private void sortTaskListByPriority() {
        taskList.sort((t1, t2) -> t2.getPriorityLevel() - t1.getPriorityLevel());
    }

    //returns size of list
    private int getListSize() {
        return taskList.size();
    }

    /**
     * Retrieves task history through the TaskHistoryManager.
     *
     * @return Output string for display
     */
    public String startupTaskManager() {
        return thm.retrieveTaskHistory(taskList)
                ? String.format("Previous task history found! %d tasks retrieved", taskList.size())
                : String.format("%s", "No task history found/History is corrupt. Creating new taskList!");
    }

    /**
     * Creates a new ToDo task and adds to the task list.
     *
     * @param task Name of the task
     * @param priority Priority enum of the task
     * @return Output string for display
     */
    public String addTodoTask(String task, Priority priority) {
        ToDo newTask = new ToDo(task, false, priority);
        taskList.add(newTask);
        thm.updateHistory(taskList);
        sortTaskListByPriority();
        return String.format(
                "Alright, I have added a new todo:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(taskList.indexOf(newTask)),
                getListSize()
        );
    }

    /**
     * Creates a new Deadline task and adds to the task list.
     *
     * @param task Name of the task
     * @param priority Priority enum of the task
     * @param completeBy Completion date/time of the task as String
     * @return Output string for display
     */
    public String addDeadlineTask(String task, Priority priority, LocalDateTime completeBy) {
        Deadline newTask = new Deadline(task, false, priority, completeBy);
        taskList.add(newTask);
        thm.updateHistory(taskList);
        sortTaskListByPriority();
        return String.format(
                "Alright, I have added a new deadline:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(taskList.indexOf(newTask)),
                getListSize()
        );
    }

    /**
     * Creates a new Event task and adds to the task list.
     *
     * @param task Name of the task
     * @param start Start date/time of the task as String
     * @param end End date/time of the task as String
     * @param priority Priority enum of the task
     * @return Output string for display
     */
    public String addEventTask(String task, Priority priority, LocalDateTime start, LocalDateTime end) {
        Event newTask = new Event(task, false, priority, start, end);
        taskList.add(newTask);
        thm.updateHistory(taskList);
        sortTaskListByPriority();
        return String.format(
                "Alright, I have added a new event:\n\t%s\nYou now have %d tasks in the list.",
                viewTask(taskList.indexOf(newTask)),
                getListSize()
        );
    }

    /**
     * Deletes a task at the specified index from the task list.
     * Checks for invalid index and returns appropriate String response.
     *
     * @param taskIdx The index of the task to be deleted
     * @return Output string for display
     */
    public String deleteTask(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            String deleted = viewTask(taskIdx - 1);
            taskList.remove(actualIdx);
            thm.updateHistory(taskList);

            return String.format(
                    "Alright, I've removed this task:\n\t%s\nYou now have %d tasks in the list.",
                    deleted,
                    getListSize()
            );

        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    /**
     * Marks a task as done at the specified index from the task list.
     * Checks for invalid index and returns appropriate String response.
     *
     * @param taskIdx The index of the task to be marked
     * @return Output string for display
     */
    public String markTaskAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            taskList.get(actualIdx).markDone();
            thm.updateHistory(taskList);

            return String.format("Ok! I've marked this task as done:\n%s", viewTask(actualIdx));

        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    /**
     * Unmarks a task as done at the specified index from the task list.
     * Checks for invalid index and returns appropriate String response.
     *
     * @param taskIdx The index of the task to be unmarked
     * @return Output string for display
     */
    public String unmarkTaskAsDone(int taskIdx) {
        try {
            int actualIdx = taskIdx - 1; //account for display vs actual index
            taskList.get(actualIdx).unmarkDone();
            thm.updateHistory(taskList);

            return String.format("Ok! I've removed the mark from this task:\n%s", viewTask(actualIdx));

        } catch (IndexOutOfBoundsException e) {
            return "The task at this index does not exist!";
        }
    }

    /**
     * Searches for tasks in taskList containing the query string in the task name.
     * If no tasks found, returns appropriate String response.
     *
     * @param taskName The search string given by the user
     * @return Output string for display as a list of tasks found
     */
    public String findTaskByName(String taskName) {
        StringBuilder outputString = new StringBuilder();

        //add all tasks containing search string to output string
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);

            if (currTask.isSimilarTaskName(taskName)) {
                outputString.append(viewTask(i)).append("\n");
            }
        }

        return !outputString.isEmpty()
                ? String.format("%s\n%s", "Here are the tasks I found:" , outputString)
                : "No tasks found!";
    }

    /**
     * Displays the list of tasks in the task list.
     * Checks for empty list and returns appropriate String response.
     *
     * @return Output string for display
     */
    public String viewList() {
        if (taskList.isEmpty()) {
            //Account for empty taskList
            return "The list is empty!";
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                msg.append(viewTask(i)).append("\n");
            }

            //Remove last "\n" for formatting purposes
            return msg.substring(0, msg.length() - 1);
        }
    }
}
