package joe.ui;

import java.util.List;
import java.util.Scanner;

import joe.task.Task;
import joe.task.TaskList;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public Ui() {

    }

    /**
     * Prints the welcome message onto the terminal.
     */
    public static String welcomeText() {
        return "Hello I'm Joe\n" + "What can I do for you?";
    }

    /**
     * Prints a line that acts as a divider between input of user and output of joe.
     */
    public void line() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints the exit message onto the terminal.
     */
    public void byeText() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    /**
     * Allows the user to input a string via the terminal. Returns the input as a
     * string.
     * 
     * @return Input as a string.
     */
    public String takeInput() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints all the tasks in the todo list onto the terminal one after another.
     * 
     * @param todoList Current list of tasks.
     */
    public String printTodoList(TaskList todoList) {
        String output = "Your To-Do List: \n";
        for (int i = 0; i < todoList.getTodoList().size(); i++) {
            output += (i + 1) + ". " + todoList.getTodoList().get(i) + "\n";
        }
        return output;
    }

    public String printMatches(List<Task> matches) {
        String output = "Here are the matching tasks in your list: \n";
        if (matches.size() == 0) {
            output += "No matches!";
        } else {
            for (int i = 0; i < matches.size(); i++) {
                output += (i + 1) + ". " + matches.get(i) + "\n";
            }
        }

        return output;
    }
}
