package clare.exception;

/**
 * The exception for invalid string commands
 */
public class StringConvertExceptions extends Exception {
    private final String str;

    public StringConvertExceptions(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
