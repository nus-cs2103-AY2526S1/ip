import java.util.Scanner;

public class Dobby {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Dobby \n");
        System.out.println("What can I do for you? \n");
        boolean flag = true;

        while (flag) {
            System.out.print("> "); // prompt
            String input = sc.nextLine(); // read user input
            System.out.print("I hear that you have said: " + input + "\n");

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                flag = false;
            }
        }
    }
}
