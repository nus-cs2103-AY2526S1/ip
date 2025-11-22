package models;

/**
 * Represents data structures that is able to store in files.
 */
public interface Savable {
    /**
     * Returns string that will be written into files
     */
    public String getFormattedString();
}
