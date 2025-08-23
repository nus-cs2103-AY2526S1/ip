import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Clam {
    static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");

        greet();

        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while(!end) {
            String input = sc.nextLine();
            System.out.println("    ____________________________________________________________");

            if (Objects.equals(input, "bye")) {
                end = true;
                bye();
                sc.close();

            } else if (Objects.equals(input, "list")) {
                printlist();
                System.out.println("    ____________________________________________________________");

            } else if (input.matches("mark \\d+")) {
                int item = Integer.parseInt(input.substring(5));
                tasks.get(item-1).markAsDone();
                System.out.println("    Nice! I've marked this task as done:\n    "
                + tasks.get(item-1));

            } else if (input.matches("unmark \\d+")) {
                int item = Integer.parseInt(input.substring(7));
                tasks.get(item - 1).markAsUndone();
                System.out.println("    Ok, I've marked this task as not done yet:\n    "
                        + tasks.get(item - 1));

            } else if (input.startsWith("todo ")) {
                ToDos td = new ToDos(input.substring(5));
                tasks.add(td);
                System.out.println("    Got it. I've added this task:\n      " + td);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");

            } else if (input.startsWith("deadline ")) {
                Deadlines td = new Deadlines(input.substring(9, input.indexOf("/by")-1), input.substring(input.indexOf("/by")+4));
                tasks.add(td);
                System.out.println("    Got it. I've added this task:\n      " + td);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");

            } else if (input.startsWith("event ")) {
                Events td = new Events(input.substring(6, input.indexOf("/from")-1),
                        input.substring(input.indexOf("/from")+6,input.indexOf("/to")-1),
                        input.substring(input.indexOf("/to")+4));
                tasks.add(td);
                System.out.println("    Got it. I've added this task:\n      " + td);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");

            } else {
                System.out.println("    Sorry, I'm not sure what you mean. Can you try again? ");
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    public static void greet() {
        String greeting = "    Hello! I'm Clam!\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void bye() {
        String bye = "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        System.out.println(bye);
    }

    public static void printlist() {
        StringBuilder list = new StringBuilder();
        int index = 0;
        list.append("    Here are the tasks in your list:\n");
        for (Task i : tasks) {
            index++;
            list.append("    ").append(index).append(".").append(i).append("\n");
        }
        System.out.println(list.toString());

    }
}
