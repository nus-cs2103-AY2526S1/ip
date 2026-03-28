package cattis.exception;

/**
 * Handle save file error
 */
public class CattisSaveFileException extends CattisException {
    public CattisSaveFileException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format(SAVE_FILE, super.getMessage());
    }
}
