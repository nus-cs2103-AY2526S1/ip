package notebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotebookTest {
	@Test
	void notebook_canAddNoteAndGetSize() {
		NoteBook nb = new NoteBook();
		nb.addNote("buy groceries");
		
		assertEquals(1, nb.getSize(), "NoteBook should have 1 note after adding");
		assertTrue(nb.get(0).toString().contains("buy groceries"));
	}

	@Test
	void notebook_addMultipleNotes() {
		NoteBook nb = new NoteBook();
		nb.addNote("task one");
		nb.addNote("task two");
		
		assertEquals(2, nb.getSize(), "NoteBook should have 2 notes");
		assertTrue(nb.get(0).toString().contains("task one"));
		assertTrue(nb.get(1).toString().contains("task two"));
	}

	@Test
	void notebook_deleteNote() {
		NoteBook nb = new NoteBook();
		nb.addNote("note to delete");
		nb.addNote("note to keep");
		
		Note deleted = nb.deleteNote(0);
		assertEquals(1, nb.getSize(), "NoteBook should have 1 note after deletion");
		assertTrue(deleted.toString().contains("note to delete"));
		assertTrue(nb.get(0).toString().contains("note to keep"));
	}

	@Test
	void notebook_initiallyEmpty() {
		NoteBook nb = new NoteBook();
		assertEquals(0, nb.getSize(), "New NoteBook should be empty");
	}
}
