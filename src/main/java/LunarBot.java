import java.util.Scanner;

public class LunarBot {
    public static final String LINE = "__________________________________________";
    public static void main(String[] args) {
        System.out.println("Hello from LunarBot!\n");
        System.out.println("Nice to meet you! What can I do for you?\n" + LINE);
        echo();
        System.out.println(LINE);
        System.out.println("Hope to see you soon!\n");

    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Input: ");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
            System.out.println(LINE);
        }
    }
}