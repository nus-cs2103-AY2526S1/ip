package piper.ui;

import java.util.Scanner;

public class Ui {
    private final String chatbotName;
    private final Scanner scanner;

    public Ui(String chatbotName) {
        this.chatbotName = chatbotName;
        this.scanner = new Scanner(System.in);
    }

    // scanner helper functions
    public String read() {
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    public void close() {
        try {
            scanner.close();
        } catch (IllegalStateException ignored) { }
    }

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

    public void greetUser() {
        System.out.println(
                ascii_Greet +
                        "Hi! " + chatbotName + " here.\n" +
                        "What shall we do today?\n"
        );
    }

    public void echoUser(String userInput) {
        System.out.println(userInput);
    }

    public void farewellUser() {
        System.out.println(
                "Til next time!\n" + ascii_Exit
        );
    }

}
