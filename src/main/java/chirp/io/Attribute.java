package chirp.io;

/**
 * Represents enum of attribute values in input string
 */
public enum Attribute {
    FROM("/from"), TO("/to"), BY("/by"), ON("/on");

    private final String tag;

    Attribute(String tag) {
        this.tag = tag;
    }

    /**
     * Gets tag
     *
     * @return Underlying string of attribute tag
     */
    public String getTag() {
        return tag;
    }
}
