package jimbot.exceptions;

public class InvalidToDoException extends JimbotException {
    public InvalidToDoException() {
        super("""
                        ┌─────────────────────────┐
                        │ Your input is empty!    │
                        └─────────────────────────┘
                    ┻━┻︵╰(‵□′)╯︵┻━┻
                   """);
    }
}
