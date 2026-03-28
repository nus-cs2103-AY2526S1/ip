package sanbot.reminder;

import sanbot.storage.Storage;
import sanbot.task.Task;

import java.util.ArrayList;

public class DeadlineTasksReminder {
    public static String remindDeadlinesMessage() {
        ArrayList<Task> deadlineTasks = Storage.getDeadlineTasks();

        StringBuilder reminder = new StringBuilder();
        reminder.append("You have the following deadline tasks due\n");

        int count = 0;

        for (Task task : deadlineTasks) {
            if (task.toSaveString().split("\\|")[1].trim().equals("1")) {
                continue;
            }
            reminder.append(task).append("\n");
            count++;
        }

        if (count == 0) {
            return "You have no deadline tasks due or you have already completed them.\n Keep it up!";
        }

        String endLine = String.format("You have %d deadline task(s) pending completion.\nGet to work!", count);

        return reminder.append(endLine).toString();
    }
}
