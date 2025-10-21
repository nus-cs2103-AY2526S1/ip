package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DukeGptTest {
	@Test
	void getResponse_setsCommandTypeToGreet() {
		DukeGpt bot = new DukeGpt();
		String[] response = bot.getResponse("greet");
		assertNotNull(response);
		assertEquals("Greet", bot.getCommandType());
	}

	@Test
	void getResponse_setsCommandTypeToInvalid() {
		DukeGpt bot = new DukeGpt();
		String[] response = bot.getResponse("unknown command xyz");
		assertNotNull(response);
		assertEquals("Invalid", bot.getCommandType());
	}

	@Test
	void getResponse_setsCommandTypeToExit() {
		DukeGpt bot = new DukeGpt();
		String[] response = bot.getResponse("bye");
		assertNotNull(response);
		assertEquals("Exit", bot.getCommandType());
	}

	@Test
	void getResponse_setsCommandTypeToListOut() {
		DukeGpt bot = new DukeGpt();
		String[] response = bot.getResponse("list");
		assertNotNull(response);
		assertEquals("ListOut", bot.getCommandType());
	}
}
