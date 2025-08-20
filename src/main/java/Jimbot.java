import java.util.Scanner;

public class Jimbot {
    public static void main(String[] args) {
        String greet = "   ____________________________________________________________\n" +
                        "    Hello! I'm Jimbot.\n" +
                        "    What can I do for you?\n" +
                        "   ____________________________________________________________";
        System.out.println(greet);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] userList = new Task[100];
        int count = 0;

        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("   ____________________________________________________________\n" +
                        "    Bye. Hope to see you again soon!\n" +
                        "   ____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                String list = "    Here are the tasks in your list:\n";
                for (int i = 0; i < count; i++) {
                    list += "    " + (i + 1) + ". " + userList[i] + "\n";
                }
                System.out.println("   ____________________________________________________________\n" +
                        list +
                        "   ____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    userList[index].markAsDone();
                    System.out.println("   ____________________________________________________________\n" +
                            "    Nice! I've marked this task as done: \n" +
                            "       " + userList[index] + "\n" +
                            "   ____________________________________________________________");
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (index >= 0 && index < count) {
                    userList[index].markAsUndone();
                    System.out.println("   ____________________________________________________________\n" +
                            "    OK, I've marked this task as not done yet: \n" +
                            "       " + userList[index] + "\n" +
                            "   ____________________________________________________________");
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                if (count >= 100) {
                    System.out.println("Too many tasks!");
                } else {
                    userList[count++] = new Task(userInput);
                    System.out.println("   ____________________________________________________________\n" +
                            "    added: " + userInput + "\n" +
                            "   ____________________________________________________________");
                }
            }
        }
        scanner.close();
    }
}
