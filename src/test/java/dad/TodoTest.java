package dad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
	@Test
	public void toRecordTest_standard() {
		Todo todo = new Todo("Test Item");
		assertEquals(todo.toRecord(), "T|Test Item");
	}

	@Test
	public void toStringTest_standard() {
		Todo todo = new Todo("Test Item");
		assertEquals(todo.toString(), "[T] [ ] Test Item");
	}

	@Test
	public void toStringTest_mark() {
		Todo todo = new Todo("Test Item");
		todo.mark();
		assertEquals(todo.toString(), "[T] [X] Test Item");
		todo.unmark();
		assertEquals(todo.toString(), "[T] [ ] Test Item");
	}
}
