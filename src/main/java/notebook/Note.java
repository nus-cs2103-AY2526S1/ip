package notebook;

/**
 * Atomic part of a notebook. Represents a single note.
 */
public class Note {
    private String note;

    /**
     * Constructor of note
     * @param s Note content
     */
    public Note(String s) {
        this.note = s;
    }
    @Override
    public String toString() {
        return "Note | " + this.note;
    }
}
