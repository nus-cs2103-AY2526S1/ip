package Note.ui;

/**
 * Parses user input into commands and arguments.
 */
public class Parser {

    /**
     * Extracts the command word (first token).
     */
    public static String getCommand(String input) {
        assert input != null : "Input cannot be null";
        input = input.trim();
        assert !input.isBlank() : "Input cannot be blank";
        return input.trim().split(" ")[0];
    }

    /**
     * Extracts arguments after the command.
     */
    public static String getArguments(String input) {
        assert input != null : "Input cannot be null";
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            return "";
        }
        return input.substring(spaceIndex + 1).trim();
    }

    /**
     * Extracts the description part before a flag (e.g., /by, /from).
     */
    public static String extractDescription(String args, String flag) {
        assert args != null : "Arguments cannot be null";
        assert flag != null && !flag.isBlank() : "Flag cannot be null/blank";
        int flagIndex = args.indexOf(flag);
        if (flagIndex == -1) {
            return args.trim();
        }
        return args.substring(0, flagIndex).trim();
    }

    /**
     * Extracts the value after a given flag (e.g., "/by", "/from", "/to").
     */
    public static String extractFlagValue(String args, String flag) {
        assert args != null : "Arguments cannot be null";
        assert flag != null && !flag.isBlank() : "Flag cannot be null/blank";
        int flagIndex = args.indexOf(flag);
        if (flagIndex == -1) {
            return "";
        }

        // find next flag (if any)
        int nextSlash = args.indexOf("/", flagIndex + flag.length());
        if (nextSlash == -1) {
            return args.substring(flagIndex + flag.length()).trim();
        }
        return args.substring(flagIndex + flag.length(), nextSlash).trim();
    }
}
