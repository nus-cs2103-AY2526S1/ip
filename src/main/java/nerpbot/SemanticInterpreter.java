package nerpbot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Interprets natural language input and converts it to proper NerpBot commands.
 * Uses light natural language processing techniques like keyword matching and pattern recognition.
 */
public class SemanticInterpreter {

    // Keywords that suggest different command types
    private static final String[] TODO_KEYWORDS = {"need to", "have to", "must", "should", "want to", "gotta", "gonna"};
    private static final String[] DEADLINE_KEYWORDS = {"by", "before", "due", "until", "deadline"};
    private static final String[] EVENT_KEYWORDS = {"from", "meeting", "appointment", "event", "scheduled"};
    private static final String[] DELETE_KEYWORDS = {"delete", "remove", "cancel", "get rid of"};
    private static final String[] MARK_KEYWORDS = {"done", "complete", "completed", "finished", "finish", "mark"};
    private static final String[] UNMARK_KEYWORDS = {"not done", "incomplete", "undo", "unmark", "uncomplete"};
    private static final String[] LIST_KEYWORDS = {"show", "list", "display", "what", "all tasks", "my tasks"};
    private static final String[] FIND_KEYWORDS = {"find", "search", "look for", "where is"};

    // Day keywords for relative date parsing
    private static final Map<String, Integer> DAY_OFFSETS = new HashMap<>();

    static {
        DAY_OFFSETS.put("today", 0);
        DAY_OFFSETS.put("tomorrow", 1);
        DAY_OFFSETS.put("day after tomorrow", 2);
    }

    /**
     * Represents the result of semantic interpretation.
     */
    public static class InterpretationResult {
        private final String command;
        private final String explanation;
        private final double confidence;
        private final boolean isNaturalLanguage;

        public InterpretationResult(String command, String explanation, double confidence, boolean isNaturalLanguage) {
            this.command = command;
            this.explanation = explanation;
            this.confidence = confidence;
            this.isNaturalLanguage = isNaturalLanguage;
        }

        public String getCommand() {
            return command;
        }

        public String getExplanation() {
            return explanation;
        }

        public double getConfidence() {
            return confidence;
        }

        public boolean isNaturalLanguage() {
            return isNaturalLanguage;
        }

        public boolean needsConfirmation() {
            return isNaturalLanguage && confidence < 1.0;
        }
    }

    /**
     * Checks if the input is already a valid command.
     *
     * @param input The user's input string.
     * @return true if the input starts with a valid command word.
     */
    public static boolean isDirectCommand(String input) {
        String commandWord = input.split(" ")[0].toLowerCase();
        return commandWord.equals("help") || commandWord.equals("bye") || commandWord.equals("list")
                || commandWord.equals("mark") || commandWord.equals("unmark") || commandWord.equals("delete")
                || commandWord.equals("todo") || commandWord.equals("deadline") || commandWord.equals("event")
                || commandWord.equals("find");
    }

    /**
     * Interprets natural language input and converts it to a proper command.
     *
     * @param input The user's natural language input.
     * @return An InterpretationResult containing the interpreted command and metadata.
     */
    public static InterpretationResult interpret(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new InterpretationResult(input, null, 0, false);
        }

        String lowerInput = input.toLowerCase().trim();

        // Check if it's already a direct command
        if (isDirectCommand(input)) {
            return new InterpretationResult(input, null, 1.0, false);
        }

        // Try to interpret as list command
        InterpretationResult listResult = tryParseListCommand(lowerInput);
        if (listResult != null) {
            return listResult;
        }

        // Try to interpret as find command
        InterpretationResult findResult = tryParseFindCommand(lowerInput, input);
        if (findResult != null) {
            return findResult;
        }

        // Try to interpret as mark/unmark command
        InterpretationResult markResult = tryParseMarkCommand(lowerInput);
        if (markResult != null) {
            return markResult;
        }

        // Try to interpret as delete command
        InterpretationResult deleteResult = tryParseDeleteCommand(lowerInput);
        if (deleteResult != null) {
            return deleteResult;
        }

        // Try to interpret as event command
        InterpretationResult eventResult = tryParseEventCommand(lowerInput, input);
        if (eventResult != null) {
            return eventResult;
        }

        // Try to interpret as deadline command
        InterpretationResult deadlineResult = tryParseDeadlineCommand(lowerInput, input);
        if (deadlineResult != null) {
            return deadlineResult;
        }

        // Try to interpret as todo command
        InterpretationResult todoResult = tryParseTodoCommand(lowerInput, input);
        if (todoResult != null) {
            return todoResult;
        }

        // Could not interpret
        return new InterpretationResult(input, null, 0, false);
    }

    private static InterpretationResult tryParseListCommand(String lowerInput) {
        for (String keyword : LIST_KEYWORDS) {
            if (lowerInput.contains(keyword)
                    && (lowerInput.contains("task") || lowerInput.contains("list") || lowerInput.equals("show")
                    || lowerInput.contains("all") || lowerInput.contains("what do i have"))) {
                return new InterpretationResult("list",
                        "Show all tasks", 0.85, true);
            }
        }
        return null;
    }

    private static InterpretationResult tryParseFindCommand(String lowerInput, String originalInput) {
        for (String keyword : FIND_KEYWORDS) {
            if (lowerInput.startsWith(keyword)) {
                String searchTerm = extractAfterKeyword(originalInput, keyword);
                if (!searchTerm.isEmpty()) {
                    return new InterpretationResult("find " + searchTerm,
                            "Search for tasks containing: " + searchTerm, 0.85, true);
                }
            }
        }
        return null;
    }

    private static InterpretationResult tryParseMarkCommand(String lowerInput) {
        // Check for unmark first (more specific)
        for (String keyword : UNMARK_KEYWORDS) {
            if (lowerInput.contains(keyword)) {
                Integer taskNum = extractTaskNumber(lowerInput);
                if (taskNum != null) {
                    return new InterpretationResult("unmark " + taskNum,
                            "Mark task " + taskNum + " as not done", 0.80, true);
                }
            }
        }

        // Check for mark
        for (String keyword : MARK_KEYWORDS) {
            if (lowerInput.contains(keyword)) {
                Integer taskNum = extractTaskNumber(lowerInput);
                if (taskNum != null) {
                    return new InterpretationResult("mark " + taskNum,
                            "Mark task " + taskNum + " as done", 0.80, true);
                }
            }
        }
        return null;
    }

    private static InterpretationResult tryParseDeleteCommand(String lowerInput) {
        for (String keyword : DELETE_KEYWORDS) {
            if (lowerInput.contains(keyword)) {
                Integer taskNum = extractTaskNumber(lowerInput);
                if (taskNum != null) {
                    return new InterpretationResult("delete " + taskNum,
                            "Delete task " + taskNum, 0.80, true);
                }
            }
        }
        return null;
    }

    private static InterpretationResult tryParseEventCommand(String lowerInput, String originalInput) {
        // Pattern: something from X to Y
        Pattern eventPattern = Pattern.compile("(.+?)\\s+from\\s+(.+?)\\s+to\\s+(.+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = eventPattern.matcher(lowerInput);

        if (matcher.find()) {
            String description = cleanDescription(matcher.group(1));
            String from = matcher.group(2).trim();
            String to = matcher.group(3).trim();

            // Parse relative dates
            from = parseRelativeDate(from);
            to = parseRelativeDate(to);

            String command = "event " + description + " /from " + from + " /to " + to;
            return new InterpretationResult(command,
                    "Create event: " + description + " (from " + from + " to " + to + ")", 0.85, true);
        }

        // Check for event keywords with simpler patterns
        for (String keyword : EVENT_KEYWORDS) {
            if (lowerInput.contains(keyword) && !keyword.equals("from")) {
                // Try to extract times
                Pattern simpleEventPattern = Pattern.compile("(.+?)\\s+(at|on)\\s+(.+)", Pattern.CASE_INSENSITIVE);
                Matcher simpleMatcher = simpleEventPattern.matcher(originalInput);
                if (simpleMatcher.find()) {
                    String description = cleanDescription(simpleMatcher.group(1));
                    String time = simpleMatcher.group(3).trim();
                    // For now, use the time as both from and to
                    String command = "event " + description + " /from " + time + " /to " + time;
                    return new InterpretationResult(command,
                            "Create event: " + description + " (at " + time + ")", 0.70, true);
                }
            }
        }
        return null;
    }

    private static InterpretationResult tryParseDeadlineCommand(String lowerInput, String originalInput) {
        // Pattern: something by/before/due/until date
        for (String keyword : DEADLINE_KEYWORDS) {
            Pattern deadlinePattern = Pattern.compile("(.+?)\\s+" + keyword + "\\s+(.+)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = deadlinePattern.matcher(lowerInput);

            if (matcher.find()) {
                String description = cleanDescription(matcher.group(1));
                String deadline = matcher.group(2).trim();

                // Parse relative dates
                deadline = parseRelativeDate(deadline);

                String command = "deadline " + description + " /by " + deadline;
                return new InterpretationResult(command,
                        "Create deadline: " + description + " (by " + deadline + ")", 0.85, true);
            }
        }
        return null;
    }

    private static InterpretationResult tryParseTodoCommand(String lowerInput, String originalInput) {
        // Check for todo keywords
        for (String keyword : TODO_KEYWORDS) {
            if (lowerInput.contains(keyword)) {
                String description = extractAfterKeyword(originalInput, keyword);
                if (!description.isEmpty()) {
                    return new InterpretationResult("todo " + description,
                            "Create todo: " + description, 0.75, true);
                }
            }
        }

        // If input starts with "i" (like "i need to..." or "i have to...")
        if (lowerInput.startsWith("i ")) {
            String remaining = originalInput.substring(2).trim();
            for (String keyword : TODO_KEYWORDS) {
                if (remaining.toLowerCase().startsWith(keyword)) {
                    String description = remaining.substring(keyword.length()).trim();
                    if (!description.isEmpty()) {
                        return new InterpretationResult("todo " + description,
                                "Create todo: " + description, 0.75, true);
                    }
                }
            }
        }

        // Check for "add" or "create" prefix
        if (lowerInput.startsWith("add ") || lowerInput.startsWith("create ")) {
            String description = originalInput.substring(originalInput.indexOf(' ') + 1).trim();
            if (!description.isEmpty() && !containsAnyKeyword(lowerInput, DEADLINE_KEYWORDS)
                    && !containsAnyKeyword(lowerInput, EVENT_KEYWORDS)) {
                return new InterpretationResult("todo " + description,
                        "Create todo: " + description, 0.70, true);
            }
        }

        return null;
    }

    private static String extractAfterKeyword(String input, String keyword) {
        int index = input.toLowerCase().indexOf(keyword.toLowerCase());
        if (index >= 0) {
            return input.substring(index + keyword.length()).trim();
        }
        return "";
    }

    private static Integer extractTaskNumber(String input) {
        // Look for patterns like "task 1", "#1", "number 1", or just "1"
        Pattern numPattern = Pattern.compile("(?:task\\s*|#|number\\s*)?(\\d+)");
        Matcher matcher = numPattern.matcher(input);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static String cleanDescription(String description) {
        // Remove common prefixes
        String[] prefixes = {
                "i need to", "i have to", "i must", "i should", "i want to",
                "i gotta", "i gonna", "need to", "have to", "must", "should",
                "want to", "gotta", "gonna", "add", "create", "schedule", "plan"
        };

        String result = description.trim();
        for (String prefix : prefixes) {
            if (result.toLowerCase().startsWith(prefix)) {
                result = result.substring(prefix.length()).trim();
            }
        }
        return result;
    }

    private static String parseRelativeDate(String dateStr) {
        String lowerDate = dateStr.toLowerCase().trim();

        for (Map.Entry<String, Integer> entry : DAY_OFFSETS.entrySet()) {
            if (lowerDate.contains(entry.getKey())) {
                LocalDate date = LocalDate.now().plusDays(entry.getValue());
                return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }

        return dateStr;
    }

    private static boolean containsAnyKeyword(String input, String[] keywords) {
        for (String keyword : keywords) {
            if (input.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
