public class ChashException extends RuntimeException {
    public ChashException(String msg) {
        super(msg);
    }

    public ChashException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
