import java.util.Scanner;

public class Stella {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Task[] lists = new Task[100];
        int counter = 0;

        System.out.println(" > Hello! I am Stella");
        System.out.println(" > What can I do for you?");

        String user_input = scan.nextLine();

        while (!user_input.equals("bye")) {
            if (user_input.equals(("list"))) {
               for (int i = 1; i - 1 < counter; i = i + 1) {
                   System.out.println(" > " +i + ". " + lists[i-1]);
               }
            }

            else if (user_input.contains("unmark")) {
                Integer index = Integer.valueOf(user_input.substring(7)) - 1;
                lists[index].markUndone();
                System.out.println(" > OK, I've marked this task as not done yet: ");
                System.out.println(" > " + lists[index]);

            }

            else if (user_input.contains("mark")) {
                Integer index = Integer.valueOf(user_input.substring(5)) - 1;
                lists[index].markDone();
                System.out.println(" > Nice! I've marked this task as done: ");
                System.out.println(" > " + lists[index]);

            }

            else {
                lists[counter] = new Task(user_input);
                counter = counter + 1;
                System.out.println(" > added: " + user_input);
            }

            user_input = scan.nextLine();

        }

        System.out.println(" > Goodbye!");


    }
}
