import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    String name;
    Boolean completed;

    Task(String name, Boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public void setCompeleted(Boolean bool) {
        this.completed = bool;
    }

    public String print() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.name;
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
            if (input.equals("bye")) {
                // exit
                break;
            }
            else if (input.equals("list")) {
                // print history
                System.out.println("Printing history!");
                for (int i = 1; i < tasks.size() + 1; i++) {
                    System.out.print(i + ": ");
                    System.out.println(tasks.get(i-1).print());
                }
            }
            else if (input.split(" ")[0].equals("mark") && input.split(" ").length > 1){
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll mark that one off your list!");
                tasks.get(index).setCompeleted(true);
                System.out.println(tasks.get(index).print());
            }
            else if (input.split(" ")[0].equals("unmark") && input.split(" ").length > 1){
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                if (index < 0 || index > tasks.size()) {
                    System.out.println("Operation not possible!");
                    System.out.println(LINE);
                    continue;
                }
                System.out.println("Okay, I'll unmark that!");
                tasks.get(index).setCompeleted(false);
                System.out.println(tasks.get(index).print());
            }
            else {
                System.out.println("added: " + input);
                tasks.add(new Task(input, false));
            }
            // add to history
            System.out.println(LINE);
        }
    }
}