package exceptions;

public class InvalidDeadlineException extends JimbotException {
    public InvalidDeadlineException() {
        super("""
                   ┌───────────────────────────────────────────────────────────────────┐
                   │ Invalid deadline format! Make sure you include /by (date/time)!!! │
                   └───────────────────────────────────────────────────────────────────┘
                 (╯°□°）╯︵ ┻━┻
                """);
    }
}
