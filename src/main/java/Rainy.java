import java.util.Scanner;

public class Rainy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        Task[] tasks = new Task[100];
        int taskCount = 0;

        /** intro**/
        System.out.println(line
                + "Hello! I'm Rainy\n"
                + "What can I do for you?\n"
                + line);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line + "Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(line + "Nice! I've marked this task as done:" + tasks[taskNumber]);
            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].unmark();
                System.out.println(line + "OK, I've marked this task as not done yet:" + tasks[taskNumber]);
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(line + " added: " + input +"\n" + line);
            }
        }
        sc.close();
    }
}
