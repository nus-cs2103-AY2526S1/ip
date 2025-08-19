import java.util.Scanner;

public class Rafayel {
    public static void main(String[] args) {
        String LINE = "____________________________________________________________";
        String START_MSG = LINE + "\n"
                + " Hello! I'm Rafayel\n"
                + " What can I do for you?\n"
                + LINE;
        String END_MSG = LINE + "\n"
                + " Bye. Hope to see you again soon!\n"
                + LINE;

        Scanner sc = new Scanner(System.in);

        System.out.println(START_MSG);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(END_MSG);
                break;
            }

            System.out.println(LINE);
            System.out.println(" " + input);
            System.out.println(LINE);
        }

        sc.close();
    }
}
