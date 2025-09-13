package hermione.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hermione.exceptions.NumberUtilsException;

public class NumberUtilsTest {

    @Test
    public void parsePositiveInt_validPositiveInteger_returnsInteger() {
        String validString = "5";
        int result = NumberUtils.parsePositiveInt(validString);
        Assertions.assertEquals(5, result);
    }

    @Test
    public void parsePositiveInt_validPositiveIntegerWithSpaces_returnsInteger() {
        String validString = "  10  ";
        int result = NumberUtils.parsePositiveInt(validString);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void parsePositiveInt_zero_throwsException() {
        String zeroString = "0";
        Assertions.assertThrows(
                NumberUtilsException.class, () -> NumberUtils.parsePositiveInt(zeroString));
    }

    @Test
    public void parsePositiveInt_negativeInteger_throwsException() {
        String negativeString = "-5";
        Assertions.assertThrows(
                NumberUtilsException.class, () -> NumberUtils.parsePositiveInt(negativeString));
    }

    @Test
    public void parsePositiveInt_invalidString_throwsException() {
        String invalidString = "abc";
        Assertions.assertThrows(
                NumberUtilsException.class, () -> NumberUtils.parsePositiveInt(invalidString));
    }

    @Test
    public void parsePositiveInt_emptyString_throwsException() {
        String emptyString = "";
        Assertions.assertThrows(
                NumberUtilsException.class, () -> NumberUtils.parsePositiveInt(emptyString));
    }

    @Test
    public void parsePositiveInt_whitespaceOnly_throwsException() {
        String whitespaceString = "   ";
        Assertions.assertThrows(
                NumberUtilsException.class, () -> NumberUtils.parsePositiveInt(whitespaceString));
    }

    @Test
    public void parsePositiveInt_decimalNumber_throwsException() {
        String decimalString = "5.5";
        Assertions.assertThrows(
                NumberUtilsException.class, () -> NumberUtils.parsePositiveInt(decimalString));
    }

    @Test
    public void parsePositiveInt_largePositiveInteger_returnsInteger() {
        String largeString = "2147483647"; // Integer.MAX_VALUE
        int result = NumberUtils.parsePositiveInt(largeString);
        Assertions.assertEquals(Integer.MAX_VALUE, result);
    }
}
