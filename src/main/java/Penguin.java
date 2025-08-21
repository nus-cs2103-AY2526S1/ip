import java.util.Scanner;

public class Penguin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Penguin\nWhat can I do for you?\n");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }
            System.out.println(line);
        }
    }
}

