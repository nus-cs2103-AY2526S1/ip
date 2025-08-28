import java.util.Scanner;

public class Jackbot {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // Start session
        System.out.println("____________________________________________________________\n"
                         + " Hello! I'm Jackbot\n"
                         + " What can I do for you?\n"
                         + "____________________________________________________________\n");
        // Echo back
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("____________________________________________________________\n" 
                             + " " + input + "\n"
                             + "____________________________________________________________\n");
        }

        // End session
        System.out.println("____________________________________________________________\n" 
                         + " Bye. Hope to see you again soon!\n"
                         + "____________________________________________________________\n");
        
        sc.close();
    }
}
