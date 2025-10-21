package notebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregation of notes for easier management
 */
public class NoteBook {
    private final List<Note> notes;

    /**
     * Initialise with empty notebook
     */
    public NoteBook() {
        this.notes = new ArrayList<Note>();
    }

    /**
     * Return size of notebook
     * @return size of notebook
     */
    public int getSize() {
        return this.notes.size();
    }

    /**
     * Adds a new note
     * @param s content of new note
     */
    public void addNote(String s) {
        Note newNote = new Note(s);
        this.notes.add(newNote);
    }

    /**
     * Removes note
     * @param index index to be removed
     * @return note removed
     */
    public Note deleteNote(int index) {
        return this.notes.remove(index);
    }

    /**
     * Return note at index
     * @param index index
     * @return note to be retrieved
     */
    public Note get(int index) {
        return this.notes.get(index);
    }
}

