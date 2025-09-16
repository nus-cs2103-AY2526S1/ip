package penguin.app;

public class PenguinException extends Exception {
    private final String errorType;

    public PenguinException(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        if (errorType.equals("todo") || errorType.equals("deadline") || errorType.equals("event")) {
            return errorType + " is fine but what do you want to do?";
        }
        return "I'm smart but even I do not know what that means";
    }

    @Override
    public String toString() {
        return getMessage(); // optional, keep toString consistent
    }
}

