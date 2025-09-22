package chash.ui;

import java.util.Scanner;
import java.util.stream.Stream;

/** {@link ChashUi} CLI implementation using STDIN/OUT/ERR */
public class ChashConsole extends ChashUi {
    private static final String LINEINDENT = "    ";
    private static final String LINESEP = "____________________________________________________________";
    private final Scanner sc;

    /** Initializes the CLI user interface */
    public ChashConsole() {
        sc = new Scanner(System.in);
    }

    //Input

    /**
     * Reads a single line of user input.
     *
     * @return Input line
     */
    @Override
    public String readLine() {
        return this.sc.nextLine();
    }

    //Output

    private void printLine(String line) {
        System.out.println(ChashConsole.LINEINDENT + line);
    }

    private void printLineSeparator() {
        printLine(ChashConsole.LINESEP);
    }

    /**
     * Prints a newline separated message to STDOUT with auto indent and wrapped by separators.
     *
     * @param txt Message text
     */
    @Override
    public void printMsg(String txt) {
        //todo: does not check for empty txt string
        //does not enforce print line max length
        Stream<String> lineStream = Stream.<String>of(txt.split("\n"));
        printLineSeparator();
        lineStream.forEach(line -> printLine(line)); //lambda can replace with mtd ref this::printLine
        printLineSeparator();
    }

    /** Not implemented on CLI UI */
    @Override
    public void printUserInput(String txt) throws UnsupportedOperationException {
        //Ref: https://stackoverflow.com/a/829139
        throw new UnsupportedOperationException("This method is not supported on ChashConsole.");
    }

    //Error

    /**
     * Prints an error message to standard error, wrapped with separators.
     *
     * @param txt Error text
     */
    @Override
    public void printErr(String txt) {
        printLineSeparator();
        System.err.println(ChashConsole.LINEINDENT + txt);
        printLineSeparator();
    }
}
