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
        List<String> history = new ArrayList<>();
        List<Boolean> completed = new ArrayList<>();

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
                for (int i = 1; i < history.size() + 1; i++) {
                    System.out.println(i + ": [" + (completed.get(i-1) ? "X" : " ") + "]" + history.get(i-1));
                }
            }
            else if (input.split(" ")[0].equals("mark") && input.split(" ").length > 1){
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                System.out.println("Okay, I'll mark that one off your list!");
                System.out.println("[X] " + history.get(index));
                completed.set(index, true);
            }
            else if (input.split(" ")[0].equals("unmark") && input.split(" ").length > 1){
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                System.out.println("Okay, I'll unmark that!");
                System.out.println("[ ] " + history.get(index));
                completed.set(index, false);
            }
            else {
                System.out.println("added: " + input);
                history.add(input);
                completed.add(false);
            }
            // add to history
            System.out.println(LINE);
        }
    }
}