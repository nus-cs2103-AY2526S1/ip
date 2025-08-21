import java.util.Scanner;
public class Sam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String intro = "____________________________________________________________\n"
                + " Hello! I'm Sam\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
                //+ " Bye. Hope to see you again soon!\n"
                //+ "____________________________________________________________\n";
        System.out.println(intro);

        while (true) {
            String input = sc.nextLine();  // <-- this is where user types

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // exit the loop
            }

            // Echo the input
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");
        }

        sc.close();
    }
}
