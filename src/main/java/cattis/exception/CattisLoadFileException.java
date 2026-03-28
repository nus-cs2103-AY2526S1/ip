package cattis.exception;

/**
 * Handle load file error
 */
public class CattisLoadFileException extends CattisException {
    public CattisLoadFileException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format(LOAD_FILE, super.getMessage());
    }
}
