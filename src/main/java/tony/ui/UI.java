package tony.ui;

import java.util.ArrayList;
import java.util.Scanner;

import tony.tasks.Task;
import tony.tasks.TaskList;

public class UI {
    private Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public String showAddTask(TaskList list, Task task) {
        StringBuilder s = new StringBuilder();
        s.append("Alright, just let one of my machines do it for you:\n  ").append(task);
        s.append("\nNow you have ").append(list.getSize()).append(" tasks in the list.");
        return s.toString();
    }

    public String greeting() {
        StringBuilder s = new StringBuilder();
        s.append("Hey, it's Tony.\n");
        s.append("Genius, billionaire, philanthropist… and apparently your personal assistant now.");
        return s.toString();
    }

    public String exit() {
        StringBuilder s = new StringBuilder();
        s.append("I’m powering down. Don’t break anything while I’m gone.");
        return s.toString();
    }

    public String showError(String msg) {
        return msg;
    }

    public String showList(TaskList list) {
        StringBuilder s = new StringBuilder();
        s.append("JARVIS, show them their list of tasks:\n");
        for (int i = 1; i <= list.getSize(); i++) {
            s.append("  ").append(i).append(": ").append(list.getList().get(i - 1).toString()).append("\n");
        }
        return s.toString();
    }

    public String showMark(Task t) {
        t.markDone();
        StringBuilder s = new StringBuilder();
        s.append("Done. Look at you, being efficient.\n  ");
        s.append(t);
        return s.toString();
    }

    public String showUnmark(Task t) {
        t.markUndone();
        StringBuilder s = new StringBuilder();
        s.append("Unmarked. Happens to the best of us.\n  ");
        s.append(t);
        return s.toString();
    }

    public String showDelete(Task t) {
        StringBuilder s = new StringBuilder();
        s.append("Overachieving might not be for everybody.\n  ");
        s.append(t);
        return s.toString();
    }

    public String showTasksOnDate(ArrayList<Task> tasks, boolean isFound) {
        StringBuilder s = new StringBuilder();
        if (!isFound) {
            s.append("You're as busy as a rock.");
        } else {
            s.append("Looks like you've got some things on your plate.\n");
            for (int i = 1; i <= tasks.size(); i++) {
                s.append("  ").append(i).append(": ").append(tasks.get(i - 1).toString()).append("\n");
            }
        }
        return s.toString();
    }

    public String showFound(ArrayList<Task> tasks, String keyword) {
        StringBuilder s = new StringBuilder();
        if (tasks.isEmpty()) {
            s.append("Well, I tried my best.");
        } else {
            s.append("That didn't take long.\n");
            for (int i = 1; i <= tasks.size(); i++) {
                s.append("  ").append(i).append(": ").append(tasks.get(i - 1).toString()).append("\n");
            }
        }
        return s.toString();
    }
}
