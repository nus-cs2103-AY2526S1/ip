package jimbot.exceptions;

public class NoSuchTaskException extends JimbotException {
    public NoSuchTaskException() {
        super("""
                       ┌──────────────────────────┐
                       │ I can't find that task!  │
                       └──────────────────────────┘
                 (╥﹏╥) ╯
                """);
    }
}
