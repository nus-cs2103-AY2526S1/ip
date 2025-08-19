import java.util.Scanner;

public class Rainy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        System.out.println(line
                + "Hello! I'm Rainy\n"
                + "What can I do for you?\n"
                + line);
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
                break;
            }
            System.out.println(line + input + "\n" + line);
        }
        sc.close();
    }
}
