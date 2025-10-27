package ui;

import java.util.Scanner;

import model.Task;

/**
 * Some messages to print as output
 */
public class ui {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Show greeting message
     */
    public void greet() {
        System.out.println("Hello! I'm Luka\n" +
                "What can I do for you?");
    }
    
    /**
     * Show goodbye message
     */
    public static void exit() {
        System.out.println("Bye.Hope to see you again soon!");
        sc.close();
    }
    
    /**
     * Show command message
     *
     * @return the command
     */
    public String readcommand() {
        return sc.nextLine();
    }

    /**
     * Show error message
     *
     * @param massage  The error exist
     */
    public void showError(String massage) {
        System.out.println("OPPS!! " + massage);
    }

    /**
     * Show output message
     *
     * @param massage  The massage to print
     */
    public void showMassage(String massage) {
        System.out.println(massage);
    }

    /**
     * Show task added message
     *
     * @param task        The task added
     * @param totalTasks  The current number of task exist
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list");
    }
    
    /**
     * Show task delete message
     *
     * @param task        The task deleted
     * @param totalTasks  The current number of task exist
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task: \n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list");
    }
    
    /**
     * Show list of task message
     *
     * @param tasks  All current tasks in list
     */
    public void showListTask(java.util.List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void showMatchingTask(java.util.List<Task> tasks){
        if(tasks.isEmpty()){
            System.out.println("No tasks found");
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public String showListTaskInString(java.util.List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks found";
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return result.toString();
    }

    public String showMatchingTaskInString(java.util.List<Task> tasks){
        if(tasks.isEmpty()){
            return "No tasks found";
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return result.toString();
    }
}
