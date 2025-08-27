package jimbot.exceptions;

public class InvalidIndexException extends JimbotException {
    public InvalidIndexException(){
        super("""
                       ┌──────────────────────────┐
                       │ I can't find that task!  │
                       └──────────────────────────┘
                 (╥﹏╥) ╯
                """);
    }
}
