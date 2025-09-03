package Note.ui;

public class Parser {

    public static String getCommand(String input) {
        // extract the first word as command
        return input.split(" ")[0];
    }

    public static String getArguments(String input) {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) return "";
        return input.substring(spaceIndex + 1);
    }
}
