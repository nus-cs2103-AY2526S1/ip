package klalopz.enums;

import java.util.Objects;

/**
 * Enum representing a tag for a task.
 * Each tag has a unique integer ID.
 */
public enum Tag {
    FAMILY(0),
    FRIENDS(1),
    WORK(2),
    URGENT(3),
    HOME(4),
    SCHOOL(5),
    NONE(-1);

    private final int id;

    /**
     * Constructs a Tag enum with the given ID.
     *
     * @param id the numeric ID for this tag
     */
    Tag(int id) {
        this.id = id;
    }

    /**
     * Returns the numeric ID of this tag.
     *
     * @return the ID associated with this tag
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the Tag corresponding to the given ID.
     * If no matching Tag is found, returns {@link #NONE}.
     *
     * @param id the numeric ID to look up
     * @return the Tag matching the ID, or NONE if not found
     */
    public static Tag fromId(int id) {
        for (Tag tag : Tag.values()) {
            if (tag.id == id) {
                return tag;
            }
        }
        return NONE; // default
    }

    /**
     * Returns the Tag corresponding to the given name (case-insensitive).
     * If no matching Tag is found, returns {@link #NONE}.
     *
     * @param name the name to look up
     * @return the Tag matching the name, or NONE if not found
     */
    public static Tag fromName(String name) {
        try {
            return Tag.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return NONE;
        }
    }

    @Override
    public String toString() {
        return "#" + this.name();
    }
}
