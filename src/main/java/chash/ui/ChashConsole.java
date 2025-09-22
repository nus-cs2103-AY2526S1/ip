package chash.ui;

import java.util.Scanner;
import java.util.stream.Stream;

/** Handles user input/output for CHASH. */
public class ChashConsole extends ChashUi {
    private static final String LINEINDENT = "    ";
    private static final String LINESEP = "____________________________________________________________";
    private final Scanner sc;

    /** Initializes the CLI user interface */
    public ChashConsole() {
        sc = new Scanner(System.in);
    }

    //Input

    //todo: add inherit javadoc
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

    @Override
    public void printMsg(String txt) {
        assert txt != null;

        //todo: does not check for empty txt string
        //does not enforce print line max length
        Stream<String> lineStream = Stream.<String>of(txt.split("\n"));
        printLineSeparator();
        lineStream.forEach(line -> printLine(line));  //lambda can replace with mtd ref this::printLine
        printLineSeparator();
    }

    @Override
    public void printUserInput(String txt) throws UnsupportedOperationException {
        //Ref: https://stackoverflow.com/a/829139
        throw new UnsupportedOperationException("This method is not supported on ChashConsole.");
    }

    //Error

    @Override
    public void printErr(String txt) {
        assert txt != null;

        printLineSeparator();
        System.err.println(ChashConsole.LINEINDENT + txt);
        printLineSeparator();
    }
}
