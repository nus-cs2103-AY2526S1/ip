package utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test suite for the IO utility class.
 * Tests command extraction, argument parsing, and integer parsing functionality.
 */
class IOTest {

    @Nested
    @DisplayName("extractCommand() tests")
    class ExtractCommandTests {

        @Test
        @DisplayName("should extract simple lowercase command")
        void testExtractSimpleCommand() {
            String input = "hello world";
            String result = IO.extractCommand(input);
            assertEquals("hello", result);
        }

        @Test
        @DisplayName("should extract and convert uppercase command to lowercase")
        void testExtractUppercaseCommand() {
            String input = "DELETE 5";
            String result = IO.extractCommand(input);
            assertEquals("delete", result);
        }

        @Test
        @DisplayName("should extract mixed case command as lowercase")
        void testExtractMixedCaseCommand() {
            String input = "ToDo buy milk";
            String result = IO.extractCommand(input);
            assertEquals("todo", result);
        }

        @Test
        @DisplayName("should return empty string for input starting with non-letter")
        void testExtractCommandWithLeadingNumber() {
            String input = "123abc";
            String result = IO.extractCommand(input);
            assertEquals("", result);
        }

        @Test
        @DisplayName("should return empty string for input starting with space")
        void testExtractCommandWithLeadingSpace() {
            String input = " command";
            String result = IO.extractCommand(input);
            assertEquals("", result);
        }

        @Test
        @DisplayName("should return empty string for null input")
        void testExtractCommandNull() {
            String result = IO.extractCommand(null);
            assertEquals("", result);
        }

        @Test
        @DisplayName("should return empty string for empty input")
        void testExtractCommandEmpty() {
            String result = IO.extractCommand("");
            assertEquals("", result);
        }

        @Test
        @DisplayName("should extract command up to first non-letter character")
        void testExtractCommandWithNumbers() {
            String input = "todo123";
            String result = IO.extractCommand(input);
            assertEquals("todo", result);
        }

        @Test
        @DisplayName("should extract command up to first special character")
        void testExtractCommandWithSpecialChar() {
            String input = "list-all";
            String result = IO.extractCommand(input);
            assertEquals("list", result);
        }

        @Test
        @DisplayName("should handle single letter command")
        void testExtractSingleLetterCommand() {
            String input = "x 123";
            String result = IO.extractCommand(input);
            assertEquals("x", result);
        }
    }

    @Nested
    @DisplayName("extractArgs() tests")
    class ExtractArgsTests {

        @Test
        @DisplayName("should extract arguments after command")
        void testExtractSimpleArgs() {
            String input = "delete 5";
            String command = "delete";
            String result = IO.extractArgs(input, command);
            assertEquals("5", result);
        }

        @Test
        @DisplayName("should extract arguments with multiple words")
        void testExtractMultipleWordArgs() {
            String input = "todo buy milk and bread";
            String command = "todo";
            String result = IO.extractArgs(input, command);
            assertEquals("buy milk and bread", result);
        }

        @Test
        @DisplayName("should trim leading and trailing whitespace from args")
        void testExtractArgsWithExtraSpaces() {
            String input = "command    arguments here   ";
            String command = "command";
            String result = IO.extractArgs(input, command);
            assertEquals("arguments here", result);
        }

        @Test
        @DisplayName("should return empty string when no arguments present")
        void testExtractArgsNoArgs() {
            String input = "list";
            String command = "list";
            String result = IO.extractArgs(input, command);
            assertEquals("", result);
        }

        @Test
        @DisplayName("should return empty string when input equals command")
        void testExtractArgsInputEqualsCommand() {
            String input = "bye";
            String command = "bye";
            String result = IO.extractArgs(input, command);
            assertEquals("", result);
        }

        @Test
        @DisplayName("should return empty string when input is shorter than command")
        void testExtractArgsShorterInput() {
            String input = "cmd";
            String command = "command";
            String result = IO.extractArgs(input, command);
            assertEquals("", result);
        }

        @Test
        @DisplayName("should handle arguments with special characters")
        void testExtractArgsWithSpecialChars() {
            String input = "search @important #work";
            String command = "search";
            String result = IO.extractArgs(input, command);
            assertEquals("@important #work", result);
        }

        @Test
        @DisplayName("should handle arguments with only whitespace")
        void testExtractArgsOnlyWhitespace() {
            String input = "command     ";
            String command = "command";
            String result = IO.extractArgs(input, command);
            assertEquals("", result);
        }
    }

    @Nested
    @DisplayName("parseIntArg() tests")
    class ParseIntArgTests {

        @Test
        @DisplayName("should parse simple integer")
        void testParseSimpleInt() {
            String args = "5";
            Integer result = IO.parseIntArg(args);
            assertEquals(5, result);
        }

        @Test
        @DisplayName("should parse integer with surrounding text")
        void testParseIntWithText() {
            String args = "task 42 here";
            Integer result = IO.parseIntArg(args);
            assertEquals(42, result);
        }

        @Test
        @DisplayName("should parse negative integer")
        void testParseNegativeInt() {
            String args = "-10";
            Integer result = IO.parseIntArg(args);
            assertEquals(-10, result);
        }

        @Test
        @DisplayName("should parse integer from mixed alphanumeric string")
        void testParseIntFromMixedString() {
            String args = "abc123def";
            Integer result = IO.parseIntArg(args);
            assertEquals(123, result);
        }

        @Test
        @DisplayName("should parse integer ignoring special characters")
        void testParseIntIgnoringSpecialChars() {
            String args = "item #7 @priority";
            Integer result = IO.parseIntArg(args);
            assertEquals(7, result);
        }

        @Test
        @DisplayName("should return null for null input")
        void testParseIntNull() {
            Integer result = IO.parseIntArg(null);
            assertNull(result);
        }

        @Test
        @DisplayName("should return null for empty string")
        void testParseIntEmpty() {
            String args = "";
            Integer result = IO.parseIntArg(args);
            assertNull(result);
        }

        @Test
        @DisplayName("should return null for blank string")
        void testParseIntBlank() {
            String args = "   ";
            Integer result = IO.parseIntArg(args);
            assertNull(result);
        }

        @Test
        @DisplayName("should return null for string with no digits")
        void testParseIntNoDigits() {
            String args = "abc xyz";
            Integer result = IO.parseIntArg(args);
            assertNull(result);
        }

        @Test
        @DisplayName("should return null for string with only special characters")
        void testParseIntOnlySpecialChars() {
            String args = "@#$%^&*";
            Integer result = IO.parseIntArg(args);
            assertNull(result);
        }

        @Test
        @DisplayName("should parse large integer")
        void testParseLargeInt() {
            String args = "999999";
            Integer result = IO.parseIntArg(args);
            assertEquals(999999, result);
        }

        @Test
        @DisplayName("should parse zero")
        void testParseZero() {
            String args = "0";
            Integer result = IO.parseIntArg(args);
            assertEquals(0, result);
        }

        @Test
        @DisplayName("should handle multiple digits concatenated after stripping")
        void testParseMultipleDigitsAfterStrip() {
            String args = "1a2b3c";
            Integer result = IO.parseIntArg(args);
            assertEquals(123, result);
        }

        @Test
        @DisplayName("should return null for string that becomes empty after stripping")
        void testParseIntEmptyAfterStrip() {
            String args = "no numbers here";
            Integer result = IO.parseIntArg(args);
            assertNull(result);
        }

        @Test
        @DisplayName("should handle negative sign in middle of string")
        void testParseIntNegativeInMiddle() {
            String args = "abc-123";
            Integer result = IO.parseIntArg(args);
            // After stripping, becomes "-123" which is a valid negative number
            assertEquals(-123, result);
        }

        @Test
        @DisplayName("should return null for multiple minus signs")
        void testParseIntMultipleMinusSigns() {
            String args = "---";
            Integer result = IO.parseIntArg(args);
            // Multiple minus signs without digits should fail parsing
            assertNull(result);
        }

        @Test
        @DisplayName("should return null for invalid number format after stripping")
        void testParseIntInvalidFormat() {
            String args = "1-2-3";
            Integer result = IO.parseIntArg(args);
            // After stripping becomes "1-2-3" which is invalid
            assertNull(result);
        }
    }
}
