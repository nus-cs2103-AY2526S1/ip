import java.util.Scanner;
public class PoopieMeow {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm PoopieMeow");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            input = sc.nextLine();
            
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("   Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("     " + input);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
