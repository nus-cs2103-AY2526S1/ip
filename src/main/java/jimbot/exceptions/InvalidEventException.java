package jimbot.exceptions;

public class InvalidEventException extends JimbotException {
    public InvalidEventException() {
        super("""
                    ┌────────────────────────────────────────────────────────────┐
                    │ Invalid event format...                                    │
                    │ Make sure you've /from (date/time) AND /to (date/time)...  │
                    └────────────────────────────────────────────────────────────┘
                (￣ー￣) ╯
                """);
    }
}
