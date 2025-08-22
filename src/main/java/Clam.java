import java.util.Objects;
import java.util.Scanner;

public class Clam {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");

        greet();

        boolean end = false;
        Scanner sc = new Scanner(System.in);
        while(!end) {
            String input = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if(Objects.equals(input, "bye")) {
                end = true;
                bye();
                sc.close();
            } else {
                System.out.println("    " + input);
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    public static void greet() {
        String greeting = "    Hello! I'm Clam!\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static void bye() {
        String bye = "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
        System.out.println(bye);
    }
}
