package objectclasses.exception;

/**
 * Represents an error identified by the program itself.
 */
public class LynxException extends Exception {

    private boolean isSecret = false;

    public LynxException(String message) {
        super(message);
    }

    /**
     * Summons a... Lynx?
     */
    public static LynxException secret() {
        LynxException secret = new LynxException("*unskippable monologue*\n(The command seems to be invalid.)");
        secret.isSecret = true;
        return secret;
    }

    public boolean isSecret() {
        return isSecret;
    }

}
