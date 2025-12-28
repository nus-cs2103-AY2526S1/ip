package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent ToDo
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo with name and isDone = false
     */
    public ToDo(String name) {
        setName(name);
        setIsDone(false);
    }

    /**
     * Constructs a ToDo with name and tag with isDone = false
     */
    public ToDo(String name, String tag) {
        setName(name);
        setIsDone(false);
        setTag(tag);
    }

    /**
     * Constructs a ToDo with name, isDone and tag for deserialization
     */
    @JsonCreator
    public ToDo(@JsonProperty("name") String name,
            @JsonProperty("isDone") boolean isDone,
            @JsonProperty("tag") String tag) {
        setName(name);
        setIsDone(isDone);
        setTag(tag);
    }

    @Override
    public String toString() {
        if (super.getTag().equals(null) || super.getTag().isEmpty()) {
            return String.format("[T]%s", super.toString());
        }
        return String.format("[T]%s #tag: %s", super.toString(), super.getTag());
    }
}
