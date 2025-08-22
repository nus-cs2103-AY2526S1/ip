import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LunarBot {
    public static final String LINE = "__________________________________________";
    public static void main(String[] args) {
        System.out.println("Hello from LunarBot!\n");
        System.out.println("Nice to meet you! What can I do for you?\n" + LINE);
        echo();
        System.out.println(LINE);
        System.out.println("Hope to see you soon!\n");

    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("Input: ");
            String input = sc.nextLine();

            String[] tmp = input.split(" ");

            // Bye
            if (input.equals("bye")) {
                // exit
                break;
            }
            // List
            else if (input.equals("list")) {
                // print history
                System.out.println("Printing history!");
                for (int i = 1; i < tasks.size() + 1; i++) {
                    System.out.print(i + ": ");
                    System.out.println(tasks.get(i-1).print());
                }
            }
            // Mark
            else if (tmp[0].equals("mark") && tmp.length > 1){
                if (!tmp[1].matches("[-+]?\\d+")) {
                    System.out.println("Operation not possible! Index has to be an integer!");
                    System.out.println(LINE);
                    continue;
                }
                int index = Integer.valueOf(tmp[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible! Need to specify a valid index to mark!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll mark that one off your list!");
                tasks.get(index).setCompleted(true);
                System.out.println(tasks.get(index).print());
            }
            // Unmark
            else if (tmp[0].equals("unmark") && tmp.length > 1){
                if (!tmp[1].matches("[-+]?\\d+")) {
                    System.out.println("Operation not possible! Index has to be an integer!");
                    System.out.println(LINE);
                    continue;
                }
                int index = Integer.valueOf(tmp[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible! Need to specify a valid index to unmark!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll unmark that!");
                tasks.get(index).setCompleted(false);
                System.out.println(tasks.get(index).print());
            }
            // Todos
            else if (tmp[0].equals("todo")) {
                if (tmp.length == 1) {
                    System.out.println("Operation not possible! TODO's descriptor cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay! I'll add this to your TODOs");
                tasks.add(new Todo(input.substring(input.indexOf(" ") + 1), false));
            }
            // Deadlines
            else if (tmp[0].equals("deadline")) {
                if (tmp.length == 1) {
                    System.out.println("Operation not possible! Deadline's descriptor cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                if (input.split("/by").length == 1) {
                    System.out.println("Operation not possible! Deadline's end time cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay! I'll add this to your deadlines~");
                tasks.add(new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, input.split("/by ")[1]));
            }
            // Events
            else if (tmp[0].equals("event")) {
                if (tmp.length == 1) {
                    System.out.println("Operation not possible! Event's descriptor cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                if (input.split("/from").length == 1) {
                    System.out.println("Operation not possible! Event's start time cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                if (input.split("/to").length == 1) {
                    System.out.println("Operation not possible! Event's end time cannot be empty!");
                    System.out.println(LINE);
                    continue;
                }
                String[] tmp2 = input.split("/from ")[1].split(" /to ");
                System.out.println("Okay! I'll add this to your events!");
                tasks.add(new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, tmp2[0], tmp2[1]));
            }
            // Delete
            else if (tmp[0].equals("delete") && tmp.length > 1){
                if (!tmp[1].matches("[-+]?\\d+")) {
                    System.out.println("Operation not possible! Index has to be an integer!");
                    System.out.println(LINE);
                    continue;
                }
                int index = Integer.valueOf(tmp[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible! Need to specify a valid index to delete!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll delete this one from your list!");
                System.out.println(tasks.get(index).print());
                tasks.remove(tasks.get(index));
            }
            // Catch
            else {
                System.out.println("added: " + input);
                tasks.add(new Task(input, false));
            }
            // add to history
            System.out.println(LINE);
        }
    }
}