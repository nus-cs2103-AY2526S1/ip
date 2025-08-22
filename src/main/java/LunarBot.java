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
            else if (input.split(" ")[0].equals("mark") && input.split(" ").length > 1){
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll mark that one off your list!");
                tasks.get(index).setCompleted(true);
                System.out.println(tasks.get(index).print());
            }
            // Unmark
            else if (input.split(" ")[0].equals("unmark") && input.split(" ").length > 1){
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll unmark that!");
                tasks.get(index).setCompleted(false);
                System.out.println(tasks.get(index).print());
            }
            // Todos
            else if (input.split(" ")[0].equals("todo")) {
                System.out.println("Okay! I'll add this to your TODOs");
                tasks.add(new Todo(input.substring(input.indexOf(" ") + 1), false));
            }
            // Deadlines
            else if (input.split(" ")[0].equals("deadline")) {
                System.out.println("Okay! I'll add this to your deadlines~");

                tasks.add(new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, input.split("/by ")[1]));
            }
            // Events
            else if (input.split(" ")[0].equals("event")) {
                String[] tmp = input.split("/from ")[1].split(" /to ");
                System.out.println("Okay! I'll add this to your events!");
                tasks.add(new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, tmp[0], tmp[1]));
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