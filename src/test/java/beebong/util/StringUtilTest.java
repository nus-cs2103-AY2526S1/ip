package beebong.util;

import beebong.exception.BBongException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilTest {
    @Test
    public void encode_string_producesCorrectBase64String() {
        String result = StringUtil.encode("Hi");
        assertEquals("SGk=", result);
    }

    @Test
    public void decode_base64String_producesCorrectString() {
        String result = StringUtil.decode("SGk=");
        assertEquals("Hi", result);
    }

    @Test
    public void encodeDecode_string_returnsOriginalString() {
        String original = "Hi";
        String encoded = StringUtil.encode(original);
        String decoded = StringUtil.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void encodeDecode_emptyString_returnsEmptyString() {
        String encoded = StringUtil.encode("");
        String decoded = StringUtil.decode(encoded);
        assertEquals("", decoded);
    }

    // Exception Handling Tests
    @Test
    public void encode_nullInput_throwsException() {
        assertThrows(BBongException.class, () -> StringUtil.encode(null));
    }

    @Test
    public void decode_nullInput_throwsException() {
        assertThrows(BBongException.class, () -> StringUtil.encode(null));
    }
}
