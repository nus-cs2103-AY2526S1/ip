import java.util.Scanner;

public class Jackbot {

    // Helper function to print framed messages
    private static void printFramed(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + msg);
        System.out.println("____________________________________________________________\n");
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        // Start session
        printFramed("Hello! I'm Jackbot\n"
                  + "What can I do for you?\n");

        
        // Echo back
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            printFramed(" " + input + "\n");
        }

        // End session
        printFramed(" Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
