package horus;

import java.util.Scanner;

/**
 * Class that handles inputs and outputs of the user.
 */
public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets user input.
     *
     * @return String representing user input.
     */
    public String getInputStr(){
        return scanner.nextLine();
    }

    /**
     * Prints the contents of outputArray surrounded by lines.
     *
     * @param outputArray An array containing strings to be printed,
     *                    with each string representing a line to be printed on the output terminal.
     */
    public void printOutputArray(String[] outputArray) {
        printLine();
        for (int i = 0; i < outputArray.length; i++) {
            System.out.println(outputArray[i]);
        }
        printLine();
    }

    /**
     * Prints the contents of outputString surrounded by lines.
     *
     * @param outputString String to be printed to output terminal.
     */
    public void printOutputString(String outputString) {
        printLine();
        System.out.print(outputString);
        printLine();
    }


    /**
     * Prints the contents of out.
     *
     * @param out String to be printed.
     */
    public void print(String out) {
        System.out.println(out);
    }

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println("____________________________________________________________" );
    }


}
