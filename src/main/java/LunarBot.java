import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    String name;
    Boolean completed;
    String type;
    String from = "";
    String by = "";

    // Todos
    Task(String name, Boolean completed, String type) {
        this.name = name;
        this.completed = completed;
        this.type = type;
    }

    // Deadlines
    Task(String name, Boolean completed, String type, String by) {
        this.name = name;
        this.completed = completed;
        this.type = type;
        this.by = by;
    }

    // Events
    Task(String name, Boolean completed, String type, String from, String by) {
        this.name = name;
        this.completed = completed;
        this.type = type;
        this.by = by;
        this.from = from;
    }

    public void setCompleted(Boolean bool) {
        this.completed = bool;
    }

    public String print() {
        return "[" + this.type + "] [" + (this.completed ? "X" : " ") + "] " + this.name +
                (this.by.isEmpty() ? "" : (" (" +
                (this.from.isEmpty() ? "by: " : "from: " + this.from + " to: ") + this.by + ")"));
    }
}

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
                tasks.add(new Task(input.substring(input.indexOf(" ") + 1), false, "T"));
            }
            // Deadlines
            else if (input.split(" ")[0].equals("deadline")) {
                System.out.println("Okay! I'll add this to your deadlines~");

                tasks.add(new Task(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, "D", input.split("/by ")[1]));
            }
            // Events
            else if (input.split(" ")[0].equals("event")) {
                String[] tmp = input.split("/from ")[1].split(" /to ");
                System.out.println("Okay! I'll add this to your events!");
                tasks.add(new Task(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1),
                        false, "E", tmp[0], tmp[1]));
            }
            // Catch
            else {
                System.out.println("added: " + input);
                tasks.add(new Task(input, false, " "));
            }
            // add to history
            System.out.println(LINE);
        }
    }
}