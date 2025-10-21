package notebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTest {
	@Test
	void toString_formatsCorrectly() {
		Note n = new Note("remember to buy milk");
		assertEquals("Note | remember to buy milk", n.toString());
	}

	@Test
	void toString_containsNotePrefix() {
		Note n = new Note("test content");
		String s = n.toString();
		assertTrue(s.startsWith("Note | "), "toString should start with 'Note | '");
		assertTrue(s.contains("test content"), "toString should include note content");
	}

	@Test
	void toString_emptyContent() {
		Note n = new Note("");
		assertEquals("Note | ", n.toString());
	}

	@Test
	void toString_specialCharacters() {
		Note n = new Note("buy milk @50% off!");
		assertEquals("Note | buy milk @50% off!", n.toString());
	}
}