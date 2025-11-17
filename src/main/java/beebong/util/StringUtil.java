package beebong.util;

import java.util.Base64;

import beebong.exception.BBongException;


/**
 * Utility class for encoding and decoding strings in Base64 format.
 */
public class StringUtil {
    // Idea taken from chatGPT on how to safely design my serialization of strings
    /**
     * Returns a string encoded into its Base64 representation.
     *
     * @param str the input string to encode.
     * @return the Base64-encoded version of the string.
     * @throws BBongException if the input string is {@code null}.
     */
    public static String encode(String str) throws BBongException {
        if (str == null) {
            throw new BBongException("String to Encode is NULL!");
        }
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    /**
     * Returns a string decoded from its Base64 representation.
     *
     * @param str the input string to encode.
     * @return the decoded version of the Base64 string.
     * @throws BBongException If str is {@code null}.
     * @throws IllegalArgumentException If str is not a valid Base64 string.
     */
    public static String decode(String str) throws BBongException {
        if (str == null) {
            throw new BBongException("String to Decode is NULL!");
        }
        return new String(Base64.getDecoder().decode(str));
    }
}
