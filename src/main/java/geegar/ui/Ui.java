package geegar.ui;

import java.util.Scanner;

import geegar.task.Task;
import geegar.task.TaskList;

/**
 * This Class was the initial User Interface Class used
 * It will be kept for archival and reference
 */
public class Ui {
    private static final int UNDERSCORE_LENGTH = 60;
    private static final String OGRE_EMOJI = "\uD83E\uDDCC";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("_".repeat(UNDERSCORE_LENGTH));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printIntroduction() {
        showLine();
        System.out.println("Hello! I'm Geegar " + OGRE_EMOJI);
        System.out.println("What can I do for you today?");
        showLine();
    }

    public void printGoodbye() {
        close();
        System.out.println(OGRE_EMOJI + ": Alright Bye ! Stay Geeky!");
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(OGRE_EMOJI + ": There are currently no tasks");
        } else {
            System.out.println(OGRE_EMOJI + ": Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void printError(String message) {
        System.out.println(OGRE_EMOJI + ": Ooooopsies, " + message);
    }

    public void printLoadingError() {
        System.out.println(OGRE_EMOJI + ": Error loading tasks from file! I'll start with an empty task list.");
    }

    public void printTaskAdded(Task task, int totalTasks) {
        System.out.println(OGRE_EMOJI + ": Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");

    }

    public void printTaskMarked(Task task) {
        System.out.println(OGRE_EMOJI + ": Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void printTaskUnmarked(Task task) {
        System.out.println(OGRE_EMOJI + ": Alright! I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("Lock in Harder man");
    }

    public void printTaskDeleted(Task task, int totalTasks) {
        System.out.println(OGRE_EMOJI + ": Got it. I've deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void printSchedule() {
        System.out.println(OGRE_EMOJI + ": here are your deadlines/events due or during your requested time");
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printFind() {
        System.out.println(OGRE_EMOJI + ": here are the following tasks that with similiar keywords!");
    }

    public void printEmpty() {
        System.out.println(OGRE_EMOJI + ": There are currently no tasks! ");
    }

    public void close() {
        this.scanner.close();
    }

}
