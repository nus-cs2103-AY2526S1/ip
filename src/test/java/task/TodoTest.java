package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
	@Test
	void toString_containsTypeAndDescription() {
		ToDo t = new ToDo("read book");
		String s = t.toString();
		assertTrue(s.contains("[T]"), "toString should include task type [T]");
		assertTrue(s.contains("read book"), "toString should include description");
	}

	@Test
	void toFileString_containsTypeSeparatorAndDescription() {
		ToDo t = new ToDo("read book");
		String f = t.toFileString();
		assertTrue(f.startsWith("T"), "toFileString should start with 'T'");
		assertTrue(f.contains(" | "), "toFileString should contain separator ' | '");
		assertTrue(f.contains("read book"), "toFileString should include description");
	}

	@Test
	void equals_behaviour() {
		ToDo a = new ToDo("shopping");
		ToDo b = new ToDo("shopping");
		ToDo c = new ToDo("clean");
		Object other = "not a todo";

		assertEquals(a, b, "Todos with same description should be equal");
		assertNotEquals(a, c, "Todos with different descriptions should not be equal");
		assertNotEquals(a, other, "Todo should not equal an object of different type");
		assertNotEquals(a, null, "Todo should not equal null");
	}
}
