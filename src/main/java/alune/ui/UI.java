package alune.ui;

import alune.tasks.Task;
import alune.tasks.TaskList;

/**
 * This class handles the UI of the program's responses to users.
 * 
 * @author nghnaomi
 */

public class UI {
    public String greet() {
        return "hello there! i'm alune, your personal task manager~ what can i do for you today? ( ⸝⸝ᵕᴗᵕ⸝⸝ )";
    }

    public String farewell() {
        return "bye, see you again~ ꉂ(˵˃ ᗜ ˂˵)";
    }

    public String listTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder("your tasks: ᕙ( •̀ ᗜ •́ )ᕗ\n");
        if (tasks.isEmpty()) {
            return sb.append("no tasks recorded.").toString();
        } else {
            return tasks.printTasks(sb);
        }
    }

    public String markedDone(Task task) {
        return "marked as done. nice! (˵ •̀ ᴗ - ˵ )";
    }

    public String markedUndone(Task task) {
        return "okay, marked as undone. ( ó﹏ò｡ )";
    }

    public String taskNotFound() {
        return "task does not exist. ∘ ∘ ∘ ( °ヮ° ) ?";
    }

    public String invalidInput() {
        return "i do not understand, please try again. ( ╥﹏╥ )";
    }

    public String invalidDateTime() {
        return "please use dd/mm/yyyy hhmm format. (,,>﹏<,,)";
    }

    public String addedTask(String desc, int count) {
        return "added: " + desc +
                "\nyou have " + count + " task(s) now. („• ֊ •„)";
    }

    public String deletedTask(Task task, int total) {
        return "deleted: " + task.getName() +
                "\nyou have " + total + " task(s) now. o(≧∇≦o)";
    }

    public String clearedTasks(int total) {
        return "cleared a total of " + total + " tasks from the list! (￣^￣ )ゞ";
    }

    public String invalidCommand() {
        return "command not recognised. ( • ᴖ • ｡)";
    }

    public String listFilteredTasks(TaskList tasks, String key) {
        TaskList filtered = tasks.searchList(key);
        if (filtered.isEmpty()) {
            return "there are no matching tasks in your list. (─.─||)";
        } else {
            StringBuilder sb = new StringBuilder("here are the matching tasks in your list: ٩( ^ᗜ^ )و\n");
            return filtered.printTasks(sb);
        }
    }

    public String undidCommand() {
        return "the most recent change has been undone! (´｡• ω •｡`)";
    }

    public String failedUndoCommand() {
        return "there is nothing to undo. ( • ᴖ • ｡)";
    }

    public String wipedDoneTasks(int removedCount, int remaining) {
        return "removed " + removedCount + " completed task(s)! " + remaining + " task(s) remaining. (ᴗ_ ᴗ。)";
    }
}
