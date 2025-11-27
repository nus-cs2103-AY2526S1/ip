package mikey.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class ParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Test
    @DisplayName("Should parse valid todo command")
    void testValidTodoCommand() {
        Parser.ParseResult result = parser.parse("todo buy milk");
        assertFalse(result.isError);
        assertEquals(Parser.Command.TODO, result.command);
        assertEquals("buy milk", result.arguments.description);
    }

    @Test
    @DisplayName("Should normalize whitespace in commands")
    void testInputNormalization() {
        Parser.ParseResult result1 = parser.parse("  todo    buy   milk  ");
        Parser.ParseResult result2 = parser.parse("todo buy milk");
        Parser.ParseResult result3 = parser.parse("\ttodo\tbuy\tmilk\t");

        assertFalse(result1.isError);
        assertFalse(result2.isError);
        assertFalse(result3.isError);
        assertEquals("buy milk", result1.arguments.description);
        assertEquals("buy milk", result2.arguments.description);
        assertEquals("buy milk", result3.arguments.description);
    }

    @Test
    @DisplayName("Should handle empty input")
    void testEmptyInput() {
        Parser.ParseResult result = parser.parse("");
        assertTrue(result.isError);
        assertNotNull(result.errorMessage);
    }

    @Test
    @DisplayName("Should handle null input")
    void testNullInput() {
        Parser.ParseResult result = parser.parse(null);
        assertTrue(result.isError);
    }

    @Test
    @DisplayName("Should validate valid date formats")
    void testValidDateFormats() {
        String command1 = "deadline test /by 1/1/2024 1200";
        String command2 = "deadline test /by 31/12/2023 2359";

        Parser.ParseResult result1 = parser.parse(command1);
        Parser.ParseResult result2 = parser.parse(command2);

        assertFalse(result1.isError);
        assertFalse(result2.isError);
    }

    @Test
    @DisplayName("Should reject invalid date formats")
    void testInvalidDateFormats() {
        String command1 = "deadline test /by 32/1/2024 1200";  // Invalid day
        String command2 = "deadline test /by 1/13/2024 1200";   // Invalid month
        String command3 = "deadline test /by 1/1/24 1200";      // 2-digit year

        Parser.ParseResult result1 = parser.parse(command1);
        Parser.ParseResult result2 = parser.parse(command2);
        Parser.ParseResult result3 = parser.parse(command3);

        assertTrue(result1.isError);
        assertTrue(result2.isError);
        assertTrue(result3.isError);
    }

    @Test
    @DisplayName("Should reject events where end time is before start time")
    void testEventTimeValidation() {
        String command = "event meeting /from 1/1/2024 1400 /to 1/1/2024 1300";
        Parser.ParseResult result = parser.parse(command);
        assertTrue(result.isError);
        assertTrue(result.errorMessage.contains("end time must be after start time"));
    }

    @Test
    @DisplayName("Should handle case insensitive commands")
    void testCaseInsensitiveCommands() {
        Parser.ParseResult result1 = parser.parse("LIST");
        Parser.ParseResult result2 = parser.parse("list");
        Parser.ParseResult result3 = parser.parse("List");
        Parser.ParseResult result4 = parser.parse("LiSt");

        assertFalse(result1.isError);
        assertFalse(result2.isError);
        assertFalse(result3.isError);
        assertFalse(result4.isError);

        assertEquals(Parser.Command.LIST, result1.command);
        assertEquals(Parser.Command.LIST, result2.command);
        assertEquals(Parser.Command.LIST, result3.command);
        assertEquals(Parser.Command.LIST, result4.command);
    }

    @Test
    @DisplayName("Should reject descriptions that are too long")
    void testLongDescription() {
        StringBuilder longDesc = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            longDesc.append("a");
        }
        Parser.ParseResult result = parser.parse("todo " + longDesc.toString());
        assertTrue(result.isError);
        assertTrue(result.errorMessage.contains("too long"));
    }

    @Test
    @DisplayName("Should detect multiple /by keywords in deadline")
    void testMultipleByKeywords() {
        Parser.ParseResult result = parser.parse("deadline test /by 1/1/2024 1200 /by 2/1/2024 1300");
        assertTrue(result.isError);
        assertTrue(result.errorMessage.contains("Multiple '/by'"));
    }

    @Test
    @DisplayName("Should handle unknown commands")
    void testUnknownCommand() {
        Parser.ParseResult result = parser.parse("unknown command");
        assertTrue(result.isError);
        assertTrue(result.errorMessage.contains("Unknown command"));
    }
}