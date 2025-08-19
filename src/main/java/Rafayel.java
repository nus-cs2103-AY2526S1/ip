import java.util.Scanner;

public class Rafayel {
    public static void main(String[] args) {
        String LINE = "____________________________________________________________";
        String START_MSG = LINE + "\n"
                + " Hello! I'm Rafayel\n"
                + " What can I do for you?\n"
                + LINE;
        String END_MSG = " Bye. Hope to see you again soon!\n";

        String[] data = new String[100]; // assume less than 100
        int counter = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println(START_MSG);

        while (true) {
            String input = sc.nextLine();
            System.out.println(LINE);

            if (input.equals("bye")) {
                System.out.println(END_MSG);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + data[i]);
                    // System.out.println(String.format("%d. %s", i + 1, data[counter]));
                }
            } else {
                data[counter] = input;
                counter++;

                System.out.println("added: " + input);
            }

            System.out.println(LINE);
        }

        sc.close();
    }
}
