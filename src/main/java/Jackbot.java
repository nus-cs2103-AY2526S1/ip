import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jackbot {

    // Helper function to print framed messages
    private static void printFramed(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        List<String> history = new ArrayList<>();

        // Start session
        printFramed("Hello! I'm Jackbot\nWhat can I do for you?\n");
        
        // Echo back
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("history")) {
                StringBuilder sb = new StringBuilder("Your previous entries:");
                for (int i = 0; i < history.size(); i++) {
                    sb.append("\n").append(i + 1).append(". ").append(history.get(i));
                }
                printFramed(sb.toString());
                continue; // Don’t store "history"
            }

            // Else, store input in history
            history.add(input);
            printFramed(" " + input + "\n");
        }

        // End session
        printFramed(" Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
