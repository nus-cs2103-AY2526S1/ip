import java.util.Scanner;

public class Penguin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        System.out.println("Hello! I'm Penguin\nWhat can I do for you?\n");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i+1) + ". " + tasks[i]);
                }
            } else if (line.startsWith("mark ")) {
                String num = line.substring(5).trim();
                int idx = Integer.parseInt(num) - 1;
                if (idx >= 0 && idx < taskCount) {
                    Task task = tasks[idx];
                    task.setDone(true);
                    System.out.println("Nice! I've marked this task as done:\n" + task);
                } else {
                    System.out.println("invalid task");
                }
            } else if (line.startsWith("unmark ")) {
                String num = line.substring(7).trim();
                int idx = Integer.parseInt(num) - 1;
                if (idx >=0 && idx < taskCount) {
                    Task task = tasks[idx];
                    task.setDone(false);
                    System.out.println("Nice! I've marked this task as not done yet:\n" + task);
                } else {
                    System.out.println("invalid task");
                }
            } else {
                tasks[taskCount] = new Task(line);
                taskCount++;
                System.out.println(line);
            }
        }
    }
}

