package exceptions;

public class InvalidDateTimeException extends JimbotException {
    public InvalidDateTimeException() {
        super("""
                       ┌──────────────────────────────────────────────────────────┐
                       │ Invalid date or time! Use dd/mm/yyyy hhmm format!!!      │
                       └──────────────────────────────────────────────────────────┘
                    (つД`)ノ  ╯
                   """);
    }
}
