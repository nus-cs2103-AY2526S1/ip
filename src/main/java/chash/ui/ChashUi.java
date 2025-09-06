package chash.ui;

import java.util.Scanner;
import java.util.stream.Stream;

/** Handles user input/output for CHASH. */
public class ChashUi {
    private static final String LINEINDENT = "    ";
    private static final String LINESEP = "____________________________________________________________";
    private final Scanner sc;

    /** Initializes the CLI user interface */
    public ChashUi() {
        sc = new Scanner(System.in);
    }

    //Input

    /**
     * Reads a single line of user input. 
     *
     * @return Input line
     */
    public String readLine() {
        return this.sc.nextLine();
    }

    //Output

    private void printLine(String line) {
        System.out.println(ChashUi.LINEINDENT + line);
    }

    private void printLineSeparator() {
        printLine(ChashUi.LINESEP);
    }

    /** 
     * Prints a newline separated message to STDOUT with auto indent and wrapped by separators.
     *
     * @param txt Message text
     */
    public void printMsg(String txt) {
        //todo: does not check for empty txt string
        //does not enforce print line max length
        Stream<String> lineStream = Stream.<String>of(txt.split("\n"));
        printLineSeparator();
        lineStream.forEach(line -> printLine(line));
        printLineSeparator();
    }

    //Error

    /** 
     * Prints an error message to standard error, wrapped with separators. 
     *
     * @param txt Error text
     */
    public void printErr(String txt) {
        printLineSeparator();
        System.err.println(ChashUi.LINEINDENT + txt);
        printLineSeparator();
    }

    //CHASH default messages

    /** Prints the CHASH welcome message. */
    public void printWelcome() {
        printMsg("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)." + 
            "\n" + "What can I do for you?");
    }
}
