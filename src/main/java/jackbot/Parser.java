package jackbot;

public class Parser {

    public enum Type {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public static class Result {
        public final Type type;
        public final String text; // payload for todo/deadline/event
        public final int index;   // 1-based index for mark/unmark/delete

        private Result(Type type, String text, int index) {
            this.type = type;
            this.text = text;
            this.index = index;
        }

        public static Result of(Type t) {
            return new Result(t, "", -1);
        }

        public static Result text(Type t, String s) {
            return new Result(t, s, -1);
        }

        public static Result index(Type t, int i) {
            return new Result(t, "", i);
        }
    }

    public Result parse(String rawInput) throws JackbotException {
        String input = rawInput == null ? "" : rawInput.trim();
        if (input.isEmpty()) {
            // Treat empty as no-op (stay in loop)
            return Result.of(Type.LIST);
        }

        // Fast paths
        if (equalsIgnoreCase(input, "bye"))  return Result.of(Type.BYE);
        if (equalsIgnoreCase(input, "list")) return Result.of(Type.LIST);

        // Commands with arguments
        if (startsWithIgnoreCase(input, "mark "))   return Result.index(Type.MARK,   parseIndex(input.substring(5)));
        if (startsWithIgnoreCase(input, "unmark ")) return Result.index(Type.UNMARK, parseIndex(input.substring(7)));
        if (startsWithIgnoreCase(input, "delete ")) return Result.index(Type.DELETE, parseIndex(input.substring(7)));

        if (startsWithIgnoreCase(input, "todo "))     return Result.text(Type.TODO,     input.substring(5).trim());
        if (startsWithIgnoreCase(input, "deadline ")) return Result.text(Type.DEADLINE, input.substring(9).trim());
        if (startsWithIgnoreCase(input, "event "))    return Result.text(Type.EVENT,    input.substring(6).trim());

        throw new JackbotException("Command doesn't exist");
    }

    // ----- helpers -----
    private boolean equalsIgnoreCase(String a, String b) {
        return a.equalsIgnoreCase(b);
    }

    private boolean startsWithIgnoreCase(String s, String prefix) {
        return s.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    private int parseIndex(String s) throws JackbotException {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            throw new JackbotException("Failed to parse task index number");
        }
    }
}
