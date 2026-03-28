package dad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
	/* @Test
	public void parseTest_standardInput() {
		TaskList taskList = new TaskList();
		assertEquals(Parser.parse("todo item one", taskList), false);
		assertEquals(Parser.parse("deadline /by 2020-05-02", taskList), false);
		assertEquals(Parser.parse("event item two /from 2010-10-10 /to 2010-12-10", taskList), false);
		assertEquals(Parser.parse("list", taskList), false);
	}

	@Test
	public void parseTest_malformedInput() {
		TaskList taskList = new TaskList();
		assertEquals(Parser.parse("not a command", taskList), false);
		assertEquals(Parser.parse("event broken /from bad args", taskList), false);
		
	}

	@Test
	public void parseTest_terminatingInput() {
		TaskList taskList = new TaskList();
		assertEquals(Parser.parse("bye", taskList), true);
	} */
}
