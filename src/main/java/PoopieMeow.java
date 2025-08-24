import java.util.Scanner;
public class PoopieMeow {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int taskCount = 0;
        
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
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ".[" + (isDone[i] ? "X" : " ") + "] " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < taskCount) {
                    isDone[index] = true;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   [X] " + tasks[index]);
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskCount) {
                    isDone[index] = false;
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   [ ] " + tasks[index]);
                    System.out.println("____________________________________________________________");
                }
            } else {
                tasks[taskCount] = input;
                isDone[taskCount] = false;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
                taskCount++;
            }
        }
    }
}
