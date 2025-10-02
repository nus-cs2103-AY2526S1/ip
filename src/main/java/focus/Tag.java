package focus;

/**
 * Represents a tag that can be assigned to specific tasks.
 */
public class Tag {

    private String description;
    private int tagIndex;

    /**
     * Constructs a Tag.
     *
     * @param description Description of the tag.
     * @param tagIndex The task index to tag.
     */
    public Tag(int tagIndex, String description) {
        this.description = description;
        this.tagIndex = tagIndex;
    }

    @Override
    public String toString() {
        return String.format("#%s", this.description);
    }

}


