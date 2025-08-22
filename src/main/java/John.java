import java.util.Scanner;

public class John {
    public static void main(String[] args) {
        command("start");
        Scanner listen = new Scanner(System.in);
        while (true) {
            String cmd = listen.nextLine();
            command(cmd);
            if (cmd.equals("bye")) {
                break;
            }
        }
    }

    public static void command(String cmd) {
        switch (cmd) {
            case "start":
                System.out.println("Hello! I'm John");
                System.out.println("What can I do for you?");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                System.out.println(cmd);
        }
    }
}
