package jimbot.exceptions;

public class TaskLimitException extends JimbotException {
    public TaskLimitException(){
        super("""
                         ┌────────────────────┐
                         │ Too many tasks!!!  │
                !(◎_◎;) ─┴────────────────────┘
                """);
    }
}
