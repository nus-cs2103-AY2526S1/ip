import java.util.Scanner;

public class Sam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[100];  
        int taskCount = 0;                 

        String intro = "____________________________________________________________\n"
                + " Hello! I'm Sam\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(intro);

        while (true) { //loop only breaks when user inputs bye
            String input = sc.nextLine();  

            // user inputs "bye"
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // exit the loop
            } else if (input.equals("list")) {
                // Print stored tasks
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // user inputs anything else
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" " + input);
                System.out.println("____________________________________________________________");
            }
        }

        sc.close();
    }
}
