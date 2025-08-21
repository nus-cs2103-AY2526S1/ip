import java.util.Scanner;

public class Penguin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
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
            } else {
                tasks[taskCount] = line;
                taskCount++;
                System.out.println("added: " + line);
            }
        }
    }
}

