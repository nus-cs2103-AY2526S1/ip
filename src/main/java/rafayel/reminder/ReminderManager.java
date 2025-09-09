package rafayel.reminder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import rafayel.task.Task;

/**
 * Manager for reminders. 
 * Includes all the functions needed to check the date.
 */
public class ReminderManager {

    /* An array list to store all the tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor for reminder manager.
     * Filters tasks and return tasks that are due in 24 hours or 
     * have been due but not marked as done yet.
     * 
     * @param tasks takes in an ArrayList of tasks.
     */
    public ReminderManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Get all upcoming reminders that are 24 hours away from deadline or
     * overdue.
     * 
     * @return list of reminders.
     */
    public ArrayList<Task> getUpcomingReminders() {
        ArrayList<Task> reminders = new ArrayList<>();
        // ArrayList<Task> overdue = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Task task : tasks) {
            if (task.hasDeadline() && !task.isDone()) {
                LocalDateTime deadline = task.getDeadline();
                long hoursUntilDeadline = ChronoUnit.HOURS.between(now, deadline);

                // Remind if within 24 hours and not past deadline
                if (hoursUntilDeadline <= 24) {
                    reminders.add(task);
                }
            }
        }

        return reminders;
    }

    /**
     * Formatting reminders using String Builder.
     * 
     * @param reminders takes in an Arraylist of tasks representing reminders.
     * @return String of reminders formatted.
     */
    public String formatReminders(ArrayList<Task> reminders) {
        if (reminders.isEmpty()) {
            return "No upcoming reminders!";
        }

        StringBuilder sb = new StringBuilder("Upcoming Deadlines:\n\n");
        for (int i = 0; i < reminders.size(); i++) {
            Task task = reminders.get(i);
            sb.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        return sb.toString();
    }
}
