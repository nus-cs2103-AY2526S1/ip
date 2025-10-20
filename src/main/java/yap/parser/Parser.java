package yap.parser;

import java.util.Optional;

/**
 * Parser interprets raw user input into structured commands.
 *
 * <p>Responsibilities: trim and split input, recognize command keywords, and produce a Parsed
 * object with command kind and payload. Collaborators: works with Parser.Kind and Parser.Parsed
 * inner classes.
 */
public class Parser {

    /**
     * Enum representing different command types that can be parsed.
     */
    public enum Kind {
        LIST,
        SHOW,
        ADD,
        EDIT,
        DELETE,
        COMPLETE,
        HELP,
        EXIT,
        FIND,
        UNKNOWN
    }

    /** Encapsulates the result of parsing: command kind and remaining payload. */
    public static class Parsed {
        public final Kind kind;
        public final String rest; // whatever follows the command

        /**
         * Creates a new Parsed object with the specified kind and rest text.
         *
         * @param kind the command kind
         * @param rest the remaining text after the command
         */
        public Parsed(Kind kind, String rest) {
            assert kind != null : "Parsed.kind must not be null";
            assert rest != null : "Parsed.rest must not be null";
            this.kind = kind;
            this.rest = rest;
        }
    }

    /**
     * Parses raw user input into a Parsed representation.
     *
     * @param raw the raw input string
     * @return a Parsed object containing command kind and remaining text
     */
    public Parsed parse(String raw) {
        String input = normalizeInput(raw);
        if (input.isEmpty()) {
            return createUnknownCommand(input);
        }

        String lowerInput = input.toLowerCase();
        return recognizeCommand(input, lowerInput);
    }

    /**
     * Normalizes the input string for parsing.
     *
     * @param raw the raw input string
     * @return the normalized input string
     */
    private String normalizeInput(String raw) {
        return Optional.ofNullable(raw).orElse("").trim();
    }

    /**
     * Recognizes the command type and extracts the remaining text.
     *
     * @param original the original input string
     * @param lower the lowercase version for comparison
     * @return the parsed command
     */
    private Parsed recognizeCommand(String original, String lower) {
        if (isShowCommand(lower)) {
            return createShowCommand();
        } else if (lower.startsWith("add")) {
            return createAddCommand(original);
        } else if (isEditCommand(lower)) {
            return createEditCommand(original, lower);
        } else if (lower.startsWith("delete")) {
            return createDeleteCommand(original);
        } else if (isCompleteCommand(lower)) {
            return createCompleteCommand(original, lower);
        } else if (isHelpCommand(lower)) {
            return createHelpCommand();
        } else if (isExitCommand(lower)) {
            return createExitCommand();
        } else if (lower.equals("done")) {
            return createDoneCommand();
        } else if (isFindCommand(lower)) {
            return createFindCommand(original);
        }
        return createUnknownCommand(original);
    }

    /**
     * Checks if the input is a show command.
     *
     * @param lower the lowercase input
     * @return true if it's a show command
     */
    private boolean isShowCommand(String lower) {
        return lower.equals("list") || lower.equals("show");
    }

    /**
     * Creates a show command result.
     *
     * @return the parsed show command
     */
    private Parsed createShowCommand() {
        return new Parsed(Kind.SHOW, "");
    }

    /**
     * Creates an add command result.
     *
     * @param original the original input
     * @return the parsed add command
     */
    private Parsed createAddCommand(String original) {
        return new Parsed(Kind.ADD, original.substring(3).trim());
    }

    /**
     * Checks if the input is an edit command.
     *
     * @param lower the lowercase input
     * @return true if it's an edit command
     */
    private boolean isEditCommand(String lower) {
        return lower.equals("edit") || lower.matches("^edit\\s+.*");
    }

    /**
     * Creates an edit command result.
     *
     * @param original the original input
     * @param lower the lowercase input
     * @return the parsed edit command
     */
    private Parsed createEditCommand(String original, String lower) {
        String rest = lower.equals("edit") ? "" : original.replaceFirst("(?i)^edit\\s+", "").trim();
        return new Parsed(Kind.EDIT, rest);
    }

    /**
     * Creates a delete command result.
     *
     * @param original the original input
     * @return the parsed delete command
     */
    private Parsed createDeleteCommand(String original) {
        return new Parsed(Kind.DELETE, original.substring(6).trim());
    }

    /**
     * Checks if the input is a complete command.
     *
     * @param lower the lowercase input
     * @return true if it's a complete command
     */
    private boolean isCompleteCommand(String lower) {
        return lower.startsWith("complete") || lower.startsWith("done ");
    }

    /**
     * Creates a complete command result.
     *
     * @param original the original input
     * @param lower the lowercase input
     * @return the parsed complete command
     */
    private Parsed createCompleteCommand(String original, String lower) {
        String rest = lower.startsWith("done ") ? original.substring(4).trim() : original.substring(8).trim();
        return new Parsed(Kind.COMPLETE, rest);
    }

    /**
     * Checks if the input is a help command.
     *
     * @param lower the lowercase input
     * @return true if it's a help command
     */
    private boolean isHelpCommand(String lower) {
        return lower.equals("help");
    }

    /**
     * Creates a help command result.
     *
     * @return the parsed help command
     */
    private Parsed createHelpCommand() {
        return new Parsed(Kind.HELP, "");
    }

    /**
     * Checks if the input is an exit command.
     *
     * @param lower the lowercase input
     * @return true if it's an exit command
     */
    private boolean isExitCommand(String lower) {
        return lower.equals("exit") || lower.equals("quit");
    }

    /**
     * Creates an exit command result.
     *
     * @return the parsed exit command
     */
    private Parsed createExitCommand() {
        return new Parsed(Kind.EXIT, "");
    }

    /**
     * Creates a done command result (special case for add mode exit).
     *
     * @return the parsed done command
     */
    private Parsed createDoneCommand() {
        return new Parsed(Kind.ADD, "done");
    }

    /**
     * Checks if the input is a find command.
     *
     * @param lower the lowercase input
     * @return true if it's a find command
     */
    private boolean isFindCommand(String lower) {
        return lower.startsWith("find ") || lower.equals("find");
    }

    /**
     * Creates a find command result.
     *
     * @param original the original input
     * @return the parsed find command
     */
    private Parsed createFindCommand(String original) {
        String keyword = original.startsWith("find ") ? original.substring(5).trim() : "";
        return new Parsed(Kind.FIND, keyword);
    }

    /**
     * Creates an unknown command result.
     *
     * @param original the original input
     * @return the parsed unknown command
     */
    private Parsed createUnknownCommand(String original) {
        return new Parsed(Kind.UNKNOWN, original);
    }
}
