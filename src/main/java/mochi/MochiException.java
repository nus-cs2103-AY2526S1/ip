package mochi;

/**
 * Implements Exceptions that handle Mochi errors with proper text formatting.
 */
public class MochiException extends Exception {
    private String info = null;

    public MochiException() {
        super();
    }

    /**
     * Returns an exception that contains a Mochi related error message.
     *
     * @param info the details of the error
     */
    public MochiException(String info) {
        super();
        this.info = info;
    }

    @Override
    public String toString() {
        if (this.info != null) {
            return "[ ERROR ]\n" + this.info;
        } else {
            return """
                [ ERROR ]
                 Very confused, much wow. Invalid command entered.
                 Enter command 'help' for a list of commands
                """;
        }
    }
}
