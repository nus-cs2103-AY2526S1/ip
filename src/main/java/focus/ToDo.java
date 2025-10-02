package focus;

/**
 * Represents a ToDo task without any date or time component.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo event with the given description.
     *
     * @param description Description of this ToDo event.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (isTagged()) {
            return String.format("       [T]%s %s", super.toString(), getTag().toString());
        }
        return String.format("       [T]%s", super.toString());
    }

    /**
     * Returns a storage-friendly representation of this ToDo event.
     *
     * @return Encoded string to be written to disk.
     */
    @Override
    public String toStorageString() {
        if (isTagged()) {
            return String.format("T %s | %s", super.toStorageString(), getTag().toString());
        }
        return String.format("T %s", super.toStorageString());
    }

}
