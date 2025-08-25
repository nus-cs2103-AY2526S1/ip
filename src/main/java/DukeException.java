public class DukeException extends RuntimeException {
    public DukeException(String msg) {
        super(msg);
    }

    public DukeException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
