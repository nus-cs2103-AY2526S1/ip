package burrito.ui;

import burrito.tasklist.*;
import java.util.List;

public class TextUI {

    public TextUI() { }

    /**
     * Prints the contents of the Task List
     *
     * @return String representation of the Task List.
     */
    static public String list(List<Task> cache) {
        String output = "Here are the tasks in your list:" + "\n";

        for (int i = 0; i < cache.size(); i++) {
            int tempi = i + 1;
            output += tempi + ". " + cache.get(i).toString() + "\n ";
        }
        return output;
    }


    /**
     * Message for terminating the program
     *
     * @return String representation of goodbye message.
     */
    static public String help() {
        return "Here are the commands you can use: \n" +
                "bye, help, list, mark, unmark, todo, deadline, event, delete, find" + "\n ";
    }

    /**
     * Message for terminating the program
     *
     * @return String representation of goodbye message.
     */
    static public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Welcome message for program initialisation
     *
     * @return void.
     */
    static public void welcome() {
        System.out.println("Hello! I'm Burrito.\nWhat can I do for you?");
    }


}
