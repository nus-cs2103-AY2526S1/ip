package poopiemeow.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.task.Task;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(" Hello! I'm PoopieMeow");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println(" Oops! " + message);
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showGoodbye() {
        System.out.println(LINE);
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    public void showTaskMarked(Task task) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(LINE);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(LINE);
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println(LINE);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    public void showTasksOnDate(ArrayList<Task> tasks, LocalDateTime date) {
        System.out.println(LINE);
        System.out.println(" Here are the tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        int count = 0;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDeadline().toLocalDate().equals(date.toLocalDate())) {
                    System.out.println(" " + (++count) + "." + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().toLocalDate().equals(date.toLocalDate()) || 
                    event.getEndTime().toLocalDate().equals(date.toLocalDate())) {
                    System.out.println(" " + (++count) + "." + task);
                }
            }
        }
        if (count == 0) {
            System.out.println(" No tasks found on this date.");
        }
        System.out.println(LINE);
    }
}
