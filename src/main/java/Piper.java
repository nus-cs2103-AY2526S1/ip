import java.util.Scanner;

public class Piper {
    public static void main(String[] args) {
        final String chatbot_Name = "Piper";
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        // ascii art
        final String ascii_Greet =
                " _______                                   \n" +
                "|_   __ \\ ( ) .\\                         \n" +
                "  | |__) |__  | ''\\   .---.  _ .--.       \n" +
                "  |  ___/[  | | /'\\\\ / /__\\\\[  /''\\|  \n" +
                " _| |_    | | | \\_/ || \\__.'| |          \n" +
                "|_____|  [___]| |\\_/  \\___/[___]         \n" +
                "           / /| | \\                       \n" +
                "           [ \\] |  ]                      \n" +
                "            \\__ __/      (l_              \n" +
                "              | |        <'  }             \n" +
                "            [___/        (  (_\\.&         \n" +
                "--------------------<>----''--\\\\---------\n" +
                "                                \\         \n";
        final String ascii_Exit =
                "             ______         ___,           \n" +
                "              `--- \\   _))/.--`           \n" +
                "            ,__`--. \\/  '>--`             \n" +
                "----------- `._.-.      /``----------------\n" +
                "                  '.__.'                   \n" +
                "                   ' '                     \n";

        // greet user
        System.out.println(
                ascii_Greet +
                        "Hi! " + chatbot_Name + " here.\n" +
                        "What shall we do today?\n"
        );

        while (!exit) { // user is active
            String input = scanner.nextLine();
            if (input.equals("bye")) { // user is inactive
                System.out.println(
                        "Til next time!\n" + ascii_Exit
                );
                exit = true;
            } else {
                System.out.println(input); // echo user
            }
        }
    }
}