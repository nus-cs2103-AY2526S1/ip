package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.common.exceptions.MissingArgumentException;
import com.arnavjhajharia.penguin.model.TaskList;
import com.arnavjhajharia.penguin.model.TaskType;

/**
 * Represents a command that adds a new task to the {@link TaskList}.
 * <p>
 * The command validates the raw user input string against the expected format
 * for the specified {@link TaskType} (TODO, DEADLINE, or EVENT) before
 * attempting to add it to the task list.
 */
public final class AddCommand implements Command {

    /** The raw user input for the task description and metadata. */
    private final String raw;

    /** The type of task to be added (e.g., TODO, DEADLINE, EVENT). */
    private final TaskType type;

    /**
     * Constructs a new {@code AddCommand}.
     *
     * @param raw  the raw input string containing the task description and additional details
     * @param type the type of task to be created
     */
    public AddCommand(String raw, TaskType type) {
        this.raw = raw;
        this.type = type;
    }

    /**
     * Executes the add command by validating the input and adding a new task to the given task list.
     * <p>
     * - If the input is missing or invalid, throws a {@link MissingArgumentException}
     *   with details of the expected format.
     * - If valid, the task is added and a success message is returned.
     *
     * @param tasks the {@link TaskList} to which the new task will be added
     * @return a {@link CommandResult} containing the success message after adding the task
     * @throws MissingArgumentException if the input is missing or does not match the expected format
     */
    @Override
    public CommandResult execute(TaskList tasks) throws MissingArgumentException {
        String safe = raw == null ? "" : raw;
        if (safe.isBlank()) {
            throw new MissingArgumentException(expectedUsage(type));
        }

        // Validation step for input format
        boolean valid = switch (type) {
            case TODO -> !safe.isBlank();
            case DEADLINE -> {
                // Accept either "/<date>" or "/by <date>"
                boolean hasSlash = safe.contains("/") && safe.indexOf("/") != safe.length() - 1;
                boolean hasByToken = safe.toLowerCase().contains("/by ");
                // Require a non-empty description before the first slash token
                boolean hasDesc = !extractDescriptionPrefix(safe).isBlank();
                yield (hasSlash || hasByToken) && hasDesc;
            }
            case EVENT -> {
                // Accept "/<from> /<to>" or "/from <from> /to <to>"
                int count = safe.length() - safe.replace("/", "").length();
                boolean hasMinTwoSlashes = count >= 2;
                boolean hasFromToTokens = safe.toLowerCase().contains("/from ") && safe.toLowerCase().contains("/to ");
                // Require a non-empty description before the first slash token
                boolean hasDesc = !extractDescriptionPrefix(safe).isBlank();
                yield (hasMinTwoSlashes || hasFromToTokens) && hasDesc;
            }
        };

        if (!valid) {
            throw new MissingArgumentException("Invalid format. Expected: " + expectedUsage(type));
        }

        String msg = tasks.add(safe, type).toString();
        return CommandResult.of(msg);
    }

    private static String expectedUsage(TaskType type) {
        return switch (type) {
            case TODO -> "todo <description>";
            case DEADLINE -> "deadline <desc> /by <yyyy-MM-dd>";
            case EVENT -> "event <desc> /from <start> /to <end>";
        };
    }

    private static String extractDescriptionPrefix(String raw) {
        if (raw == null) return "";
        String s = raw.trim();
        if (s.startsWith("/")) return ""; // immediately a token, so no desc
        int idx = s.indexOf(" /");
        if (idx < 0) return s; // no slash found, whole string considered desc
        return s.substring(0, idx).trim();
    }
}
