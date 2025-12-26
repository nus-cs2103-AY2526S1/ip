package Chunky;

//when users send deadline to do event and nothing else
public class MissingArgumentException extends ChunkyException {
    public MissingArgumentException(String msg) {
        super(msg);
    }
}
