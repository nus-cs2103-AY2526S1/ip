public class Parser {
    public static String[] parse(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        String verb = parts[0].toLowerCase();
        String rest = (parts.length > 1) ? parts[1].trim() : "";
        return new String[]{verb, rest};
    }
}
