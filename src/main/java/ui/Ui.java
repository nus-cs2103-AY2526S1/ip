package ui;

import java.util.Random;

/**
 * Class that handles all display related functions
 */
public class Ui {
    /**
     * Random int generator used for dynamic face and hand generation
     */
    private static final Random r = new Random();
    /**
     * Editable list of faces
     */
    private static final String[] faces = {"^_^", "O_O", "Y_Y", "U_U", "o_o", "C_C", "P_P"};
    /**
     * Editable list of hands
     */
    private static final String[] hands = {"/", "_/", "_|", "d", "P", "c", "_P", "o/"};
    /**
     * Standardised border for all texts
     */
    private static final String border = "---------------------------------------------------------";

    /**
     * Handles printing of most messages to users.
     * Prints all lines formatted nicely.
     * @param messages  Accepts a String or array of String to be printed
     */
    public static void display(String... messages) {
        Ui.printLine(Ui.border);
        Ui.printLine(Ui.getEmotion());
        for (String s : messages) {
            Ui.printLine(s);
        }
        Ui.printLine(Ui.border);
    }

    /**
     * Handles printing of error messages to users.
     * @param e DukeException which specifies error
     */
    public static void displayError(Exception e) {
        Ui.printLine(Ui.border);
        Ui.printLine("(X_X)__/\t!!An error occurred!!");
        Ui.printLine(e.toString());
        Ui.printLine(Ui.border);
    }

    /**
     * Generates random emotion using random int generator.
     * @return  String representing face of bot
     */
    private static String getEmotion() {
        return "main.DukeGpt || (" + Ui.faces[r.nextInt(Ui.faces.length)] + ")" + Ui.hands[r.nextInt(Ui.hands.length)];
    }

    /**
     * Standardised formatter for every line printed.
     * Abstracted to allow easy changes.
     * @param message   One line of string to be printed
     */
    private static void printLine(String message) {
        System.out.println("\t" + message);
    }
}
