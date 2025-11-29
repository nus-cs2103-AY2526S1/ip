package seedu.duke.util;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Tiny utilities for inline {@code #tags} inside task names.
 */
public final class TagUtil {

    private TagUtil() {
        // Utility class.
    }

    /**
     * Extracts distinct {@code #tags} from a line (e.g., {@code #fun}, {@code #cs2103t}).
     *
     * @param line The raw input string.
     * @return A set of normalized lowercase tags.
     */
    public static Set<String> extractTags(String line) {
        final Set<String> tags = new LinkedHashSet<>();
        if (line == null) {
            return tags;
        }
        // Split on whitespace; accept tokens starting with '#', at least 2 chars.
        for (String tok : line.split("\\s+")) {
            if (tok.length() >= 2 && tok.charAt(0) == '#') {
                // Strip trailing punctuation like ',', '.', ';'.
                final String t = tok.replaceAll("[^#A-Za-z0-9_-]+$", "");
                // Normalize to lower-case for matching.
                tags.add(t.toLowerCase());
            }
        }
        return tags;
    }

    /**
     * Returns whether the line contains the tag (case-insensitive).
     *
     * @param line     Input line to check.
     * @param tagQuery Tag to look for, must start with {@code #}.
     * @return {@code true} if tag is present.
     */
    public static boolean hasTag(String line, String tagQuery) {
        if (tagQuery == null || tagQuery.isBlank() || tagQuery.charAt(0) != '#') {
            return false;
        }
        final String q = tagQuery.toLowerCase();
        return extractTags(line).contains(q);
    }

    /**
     * Removes all {@code #tags} from the input line.
     *
     * @param line Input string.
     * @return The line without tags.
     */
    public static String stripTags(String line) {
        if (line == null) {
            return "";
        }
        final String[] toks = line.split("\\s+");
        final StringBuilder sb = new StringBuilder();
        for (String tok : toks) {
            if (!(tok.startsWith("#") && tok.length() > 1)) {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(tok);
            }
        }
        return sb.toString();
    }
}
