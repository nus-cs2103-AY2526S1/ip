package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
	@Test
	void toString_containsTypeDescriptionAndDate() {
		LocalDate from = LocalDate.of(2025, 12, 5);
		LocalDate to = from.plusDays(1);
		Event e = new Event("conference", from, to);

		String s = e.toString();
		assertTrue(s.contains("[E]"), "toString should include task type [E]");
		assertTrue(s.contains("conference"), "toString should include description");
		assertTrue(s.contains(Task.dateToString(from)) || s.contains(Task.dateToString(to)),
				"toString should include formatted date");
	}

	@Test
	void toFileString_startsWithE_containsSeparatorDescriptionAndIsoDate() {
		LocalDate from = LocalDate.of(2025, 12, 5);
		LocalDate to = from.plusDays(1);
		Event e = new Event("conference", from, to);

		String f = e.toFileString();
		assertTrue(f.startsWith("E"), "toFileString should start with 'E'");
		assertTrue(f.contains(" | "), "toFileString should contain separator ' | '");
		assertTrue(f.contains("conference"), "toFileString should include description");
		assertTrue(f.contains(from.toString()) && f.contains(to.toString()), "toFileString should include ISO dates");
	}

	@Test
	void equals_behaviour() {
		LocalDate from = LocalDate.of(2025, 12, 5);
		LocalDate to = from.plusDays(1);
		Event a = new Event("meet", from, to);
		Event b = new Event("meet", from, to);
		Event c = new Event("meet", from.plusDays(2), to.plusDays(2));
		Object other = "not an event";

		assertEquals(a, b, "Events with same description and dates should be equal");
		assertNotEquals(a, c, "Events with different dates should not be equal");
		assertNotEquals(a, other, "Event should not equal an object of different type");
		assertNotEquals(a, null, "Event should not equal null");
	}
}
