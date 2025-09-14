package waddles.task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A task can be tagged with zero or more tags.
 */
public class Tags {
    private final ArrayList<String> tags;

    /**
     * Constructs an empty list of Tags.
     */
    public Tags() {
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a new Tags object from a list of tags.
     */
    public Tags(ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * Adds a tag to this task.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Removes a tag from this task.
     */
    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    /**
     * Returns a serialization of these tags.
     * Serialization format: Comma separated tags.
     */
    public String toSerializedString() {
        return String.join(",", this.tags);
    }

    /**
     * Returns a Tags object from the serialized string.
     * WARNING: Assumes that the given string is a serialization of some Tags.
     */
    public static Tags fromSerializedString(String serialized) {
        if (serialized.isEmpty()) {
            return new Tags();
        }
        ArrayList<String> tags = new ArrayList<>(Arrays.asList(serialized.split(",")));
        return new Tags(tags);
    }

    @Override
    public String toString() {
        return String.join(",", this.tags);
    }
}
