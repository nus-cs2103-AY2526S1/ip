package larry.util;

public final class Strings {
    private Strings() {}

    /**
     * Joins parts using the platform line separator.
     *
     * @param parts strings to join
     * @return a single string containing all parts separated by {@code System.lineSeparator()}
     */
    public static String lines(String... parts) {
        return String.join(System.lineSeparator(), parts);
    }
}