package pero.ui;

import pero.model.Task;
import pero.model.TaskList;
import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

/** Handles user-facing interactions.*/
public class GuiUi {
    private static final int REMINDER_WINDOW_DAYS = 3;

    /**
     * Displays an error message to the console.
     *
     * @param message Error message.
     */
    public String getExceptions(String message){
        return message;
    }

    /** Displays welcome message.*/
    public String getWelcome(TaskList tasks) {
        return "Hello, I'm Pero! I am here to track ur tasks. \n"
                + "If anything unsure, input 'help' into command line. \n"
                + getReminders(tasks);

    }

    private String getReminders(TaskList tasks) {

        StringBuilder reminders = new StringBuilder();
        LocalDate today = LocalDate.now();
        LocalDate daysLater = today.plusDays(REMINDER_WINDOW_DAYS);

        for (Task t : tasks.getAllTasks()) {
            LocalDate due = t.getDueDate();
            if (due != null && !due.isBefore(today) && !due.isAfter(daysLater)) {
                reminders.append("- ")
                        .append(t.toString())
                        .append("\n");
            }
        }

        if (reminders.isEmpty()) {
            return " No upcoming tasks in the next " + REMINDER_WINDOW_DAYS + " days!";
        } else {
            return " Tasks due in the next " + REMINDER_WINDOW_DAYS + " days:\n" + reminders;
        }
    }


    /** Displays exit message. */
    public String getExitMessage() {
        return "Thank you for using Pero! Saving your list and exiting now...";
    }

    /**
     * Displays stored tasks in list if there is.
     * Else prompts users to start.
     *
     * @param tasks List of tasks.
     */
    public String getTaskListMessage(TaskList tasks) {
        if (tasks.getAllTasks().isEmpty()) {
            return "No stored tasks in your list yet. Start adding!";
        }
        return "Here are the stored tasks in your list:\n" +
                tasks.getAllTasks().stream()
                        .map(task -> (tasks.getAllTasks().indexOf(task) + 1) + ". " + task)
                        .collect(Collectors.joining("\n"));
    }

    /**
     * Starts users off with guide: possible inputs and explanations.
     */
    public String getGuidelines() {
        return """
        Welcome to Pero!

        Here’s how to use me:

        Adding tasks:
          - todo [description]
              Add a simple task
          - deadline [description] /by [YYYY-MM-DD HHmm]
              Add a task with a deadline
          - event [description] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]
              Add an event with start and end times

        Managing tasks:
          - list
              Show all tasks
          - mark [task number]
              Mark a task as done
          - unmark [task number]
              Mark a task as not done
          - delete [task number]
              Delete a task

        Exiting:
          - bye
              Exit Pero and save tasks

        Note:
          Date/time format must be YYYY-MM-dd HH:mm
          (e.g. 2025-09-01 16:00)

        Now let’s start!
        """;

    }

    /** Displays prompt for task.*/
    public String getPrompt(){
        return "What do you want to do?";
    }

    /**
     * Displays confirmation that task has been marked done.
     *
     * @param task Current task.
     */
    public String getMarkedTaskMessage(Task task) {
        return "Ok marked done: " + task;
    }

    /**
     * Displays confirmation that task has been marked undone.
     *
     * @param task Current task.
     */
    public String getUnmarkedTaskMessage(Task task) {
        return "Ok marked undone: " + task;
    }

    /**
     * Displays current number of tasks in task list.
     *
     * @param tasks List of tasks.
     */
    public String getTasksSizeMessage(TaskList tasks) {
        return "Now, you have " + tasks.getAllTasks().size() + " tasks in your list.";
    }

    /**
     * Displays confirmation that task is being deleted from the list.
     *
     * @param deletedTask Task that is being deleted.
     */
    public String getDeleteTaskMessage(Task deletedTask) {
        return "Noted, removing task: " + deletedTask.getDescription();
    }

    /**
     * Displays confirmation that a new task has been added.
     *
     * @param currTask Newly added task.
     */
    public String getAddedTaskMessage(Task currTask) {
        return "Got it. I've added task:\n" + currTask;
    }

    /**
     * Displays confirmation that tasks are being saved to storage.
     *
     * @param tasks List of tasks being saved/
     * @param filePath File path where tasks will be stored at.
     */
    public String getSavingToStorage(TaskList tasks, String filePath){
        return String.format("Saving %d tasks into %s%n", tasks.getAllTasks().size(), filePath);
    }


    /**
     * Displays warning that current task line being read from storage has wrong format.
     *
     * @param firstLetter First letter of line which indicates what task type.
     * @param currTaskLine Full line of text read from storage.
     */
    public String getWrongFormatMessage(String firstLetter, String currTaskLine){
        String taskType = switch (firstLetter) {
            case "T" -> "ToDo";
            case "D" -> "Deadline";
            case "E" -> "Event";
            default -> "Unknown Task";
        };
        return "Skipped invalid " + taskType + " line: " + currTaskLine;
    }


    /**
     * Displays list of tasks that match the keyword user is finding.
     *
     * @param tasks List of tasks accumulated.
     * @param keyword Keyword to search for relevant tasks that have it.
     */
    public String getMatchedTasks(TaskList tasks, String keyword) {
        if (!tasks.getAllTasks().isEmpty()) {
            String m1 = String.format("Here are the tasks that match '%s' in your list: %n", keyword);
            String m2 = tasks.getAllTasks().stream()
                    .map(task -> (tasks.getAllTasks().indexOf(task) + 1) + ". " + task)
                    .collect(Collectors.joining("\n"));
            return m1 + m2;

        } else if (keyword.trim().isEmpty()){
            return "Sorry, please key in the keyword you are looking to find.";

        } else {
            return String.format("Sorry, no tasks match '%s' in your list. %n", keyword);
        }
    }
}
