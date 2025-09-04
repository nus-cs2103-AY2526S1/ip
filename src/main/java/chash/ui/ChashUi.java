package chash.ui;

import java.util.Scanner;
import java.util.stream.Stream;

public class ChashUi {
    private static final String LINEINDENT = "    ";
    private static final String LINESEP = "____________________________________________________________";
    private final Scanner sc;

    public ChashUi() {
        sc = new Scanner(System.in);
    }

    //Input

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

    public void printMsg(String txt) {
        //todo: does not check for empty txt string
        //does not enforce print line max length
        Stream<String> lineStream = Stream.<String>of(txt.split("\n"));
        printLineSeparator();
        lineStream.forEach(line -> printLine(line));
        printLineSeparator();
    }

    //Error

    public void printErr(String txt) {
        System.err.println(ChashUi.LINEINDENT + txt);
    }

    //CHASH default messages

    public void printWelcome() {
        printMsg("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)." + 
            "\n" + "What can I do for you?");
    }
}
