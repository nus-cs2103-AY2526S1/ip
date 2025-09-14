package tsundere.command;

import java.util.Arrays;

/**
 * Displays the format of the autocompleted command for the current user input.
 */
public class Autocomplete {
    private static final String[] AUTOCOMPLETE_COMMANDS = new String[]{
        "help", "todo", "event", "deadline", "list", "find", "mark", "unmark", "undo", "bye"
    };

    /**
     * Autocompletes the given user input and returns the first command that it matches.
     * @param input User input to be autocompleted
     * @return String of the completed command
     */
    public static String generateAutocomplete(String input) {
        if (input.isEmpty()) {
            return "";
        }
        String[] words = input.split(" ");
        String currCommand = Arrays.stream(AUTOCOMPLETE_COMMANDS)
                .filter(c -> c.equals(words[0]))
                .findFirst()
                .orElse("");

        if (currCommand.isEmpty() && words.length == 1) {
            return autocompleteCommand(words[0]);
        }

        return switch (currCommand) {
        case "todo" -> "<name of task>";
        case "deadline" -> autocompleteDeadline(words);
        case "event" -> autocompleteEvent(words);
        case "mark", "unmark", "delete" -> "<number>";
        case "find" -> "<substring to search>";
        default -> "";
        };
    }

    /**
     * Takes in the first incomplete word and returns the first autocompleted command found.
     * @param input The first incomplete command
     * @return The autocompleted command, or empty string if none is found.
     */
    private static String autocompleteCommand(String input) {
        assert input.split(" ").length == 1 : "input should be one word.";
        return Arrays.stream(AUTOCOMPLETE_COMMANDS)
                .filter(c -> c.startsWith(input))
                .findFirst()
                .orElse(null);
    }

    private static String autocompleteDeadline(String[] words) {
        assert words[0].equals("deadline") : "wrong method call";
        if (words.length == 1) {
            return "<name of task>";
        } else if (words[words.length - 2].equals("/by")) {
            if (words[words.length - 1].length() == 16 && !strictMatchDateRegex(words[words.length - 1])) {
                return null;
            } else if (!partialMatchDateRegex(words[words.length - 1])) {
                return null;
            }
            return "<YYYY-MM-DDTHH:MM>";
        } else if (words[words.length - 1].equals("/by")) {
            return "<YYYY-MM-DDTHH:MM>";
        } else if (Arrays.asList(words).contains("/by")) {
            return null;
        } else {
            return "</by>";
        }
    }

    private static String autocompleteEvent(String[] words) {
        assert words[0].equals("event") : "wrong method call";
        if (words.length == 1) {
            return "<name of task>";
        } else if (words[words.length - 2].equals("/from") || (words[words.length - 2].equals("/to"))) {
            if (words[words.length - 1].length() == 16 && !strictMatchDateRegex(words[words.length - 1])) {
                return null;
            } else if (!partialMatchDateRegex(words[words.length - 1])) {
                return null;
            }
            return "<YYYY-MM-DDTHH:MM>";
        } else if (words[words.length - 1].equals("/from") || (words[words.length - 1].equals("/to"))) {
            return "<YYYY-MM-DDTHH:MM>";
        } else if (Arrays.asList(words).contains("/to")) {
            return null;
        } else if (Arrays.asList(words).contains("/from")) {
            return "</to>";
        } else {
            return "</from>";
        }
    }

    private static boolean partialMatchDateRegex(String s) {
        return s.matches("\\d{0,4}?-?\\d{0,2}?-?\\d{0,2}?T?\\d{0,2}?:?\\d{0,2}?");
    }

    private static boolean strictMatchDateRegex(String s) {
        return s.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}");
    }
}
