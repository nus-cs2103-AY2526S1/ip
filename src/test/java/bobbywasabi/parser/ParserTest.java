package bobbywasabi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import bobbywasabi.client.Client;
import bobbywasabi.exceptions.BobbyWasabiException;

class ParserTest {

    @Test
    void testParseTodo_validTodo_success() throws BobbyWasabiException {
        String input = "todo, read book";
        String desc = Parser.parseTodo(input);
        assertEquals("read book", desc);
    }

    @Test
    void testParseTodo_missingDescription_throwsException() {
        String input = "todo ";
        assertThrows(BobbyWasabiException.class, () -> Parser.parseTodo(input));
    }

    @Test
    void testParseDeadline_validDeadline_success() throws BobbyWasabiException {
        String input = "deadline,submit report ,30/8/2025 1800";
        String[] result = Parser.parseDeadline(input);
        assertEquals("submit report", result[0]);
        assertEquals("30/8/2025 1800", result[1]);
    }

    @Test
    void testParseEvent_validEvent_success() throws BobbyWasabiException {
        String input = "event,   project meeting , 29/8/2025 1200  ,29/8/2025 1400";
        String[] result = Parser.parseEvent(input);
        assertEquals("project meeting", result[0]);
        assertEquals("29/8/2025 1200", result[1]);
        assertEquals("29/8/2025 1400", result[2]);
    }

    @Test
    void testParseAddClient_validClient_success() throws BobbyWasabiException {
        String input = "ADDCLIENT, John Doe, 91234567, 30, Engineer, LifeInsurance";
        Client client = Parser.parseAddClient(input);
        assertEquals("John Doe", client.getName());
        assertEquals("91234567", client.getContactNumber());
        assertEquals(30, client.getAge());
        assertEquals("Engineer", client.getOccupation());
        assertEquals("LifeInsurance", client.getCurrentPolicies());
    }

    @Test
    void testParseAddClient_missingAge_throwsException() {
        String input = "ADDCLIENT  ,John Doe, 91234567, , Engineer, LifeInsurance";
        assertThrows(BobbyWasabiException.class, () -> Parser.parseAddClient(input));
    }

    @Test
    void testParseEditClient_validEdit_success() throws BobbyWasabiException {
        String input = "EDItCLIENT, 1,name,Jane Doe";
        String[] result = Parser.parseEditClient(input, 2);
        assertEquals("1", result[0]);
        assertEquals("NAME", result[1]);
        assertEquals("Jane Doe", result[2]);
    }

    @Test
    void testParseCommandIndex_validDeleteIndex_success() throws BobbyWasabiException {
        String input = "delete, 2";
        int idx = Parser.parseCommandIndex(input, 5);
        assertEquals(2, idx);
    }

    @Test
    void testParseCommandIndex_outOfRange_throwsException() {
        String input = "delete, 10";
        assertThrows(BobbyWasabiException.class, () -> Parser.parseCommandIndex(input, 5));
    }

    @Test
    void testParseDateString_validDateString_success() throws BobbyWasabiException {
        String dateStr = "22/8/2025 1930";
        LocalDateTime dt = Parser.parseDateString(dateStr);
        assertEquals(2025, dt.getYear());
        assertEquals(8, dt.getMonthValue());
        assertEquals(22, dt.getDayOfMonth());
        assertEquals(19, dt.getHour());
        assertEquals(30, dt.getMinute());
    }

    @Test
    void testParseDateString_invalidFormat_throwsException() {
        String dateStr = "2025-08-22 1930";
        assertThrows(BobbyWasabiException.class, () -> Parser.parseDateString(dateStr));
    }

    @Test
    void testIsContactAValidNumber_validNumbers_success() {
        assertTrue(Parser.isContactAValidNumber("91234567"));
        assertTrue(Parser.isContactAValidNumber("61234567"));
        assertTrue(Parser.isContactAValidNumber("81234567"));
        assertTrue(Parser.isContactAValidNumber("71234567"));
    }

    @Test
    void testIsContactAValidNumber_invalidNumbers_failure() {
        assertFalse(Parser.isContactAValidNumber("5123456799"));
        assertFalse(Parser.isContactAValidNumber("1234567"));
        assertFalse(Parser.isContactAValidNumber("a1234567"));
        assertFalse(Parser.isContactAValidNumber("91234 567"));
        assertFalse(Parser.isContactAValidNumber("91234-567"));
        assertFalse(Parser.isContactAValidNumber(""));
        assertFalse(Parser.isContactAValidNumber(" "));
    }
}
