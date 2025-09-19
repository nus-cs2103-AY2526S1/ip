package jerome;

import jerome.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void hello() {
        System.out.println("Hello! I'm Jerome.\nWhat can I do for you?");
    }

    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showLength(TaskList t) {
        return String.format("Now you have %d tasks in the list.", t.size());

    }

    public String showTask(Task t) {
        return t.toString();
    }

    public String showMark(Task t) {
        String output = "Nice! I've marked this task as done:\n";
        output += showTask(t);
        return output;
    }

    public String showUnmark(Task t) {
        String output = "Okay, I've marked this task as not done yet:\n";
        output += showTask(t);
        return output;
    }

    public String showUpdated(Task t) {
        return "Okay, I have updated the date for this task:\n" + showTask(t);
    }

    public String showDeleted(Task task, TaskList tasks) {
        String output ="Noted. I've removed this task:";
        output += showTask(task);
        output += showLength(tasks);
        return output;
    }

    public String showAddition(Task task, TaskList tasks) {
        String output = "";
        output += "Got it. I've added this task:\n";
        output += showTask(task);
        output += showLength(tasks);
        return output;
    }

}
