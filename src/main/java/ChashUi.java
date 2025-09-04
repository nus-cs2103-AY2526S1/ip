import java.util.Scanner;

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

    public static void printLine(String line) {
        System.out.println(ChashUi.LINEINDENT + line);
    }

    public static void printLineSeparator() {
        printLine(ChashUi.LINESEP);
    }

    public static void printMsg(String txt) {
        //todo: does not check for empty txt string
        //does not enforce print line max length
        Stream<String> lineStream = Stream.<String>of(txt.split("\n"));
        printLineSeparator();
        lineStream.forEach(line -> printLine(line));
        printLineSeparator();
    }

    //Error

    public static void printErr(String txt) {
        System.err.println(txt);
    }

    //CHASH default messages

    public static void printWelcome() {
        printMsg("Hello! I'm Crysis Heir Activity Sentre Hepdesk (CHASH)." + 
            "\n" + "What can I do for you?");
    }
}
