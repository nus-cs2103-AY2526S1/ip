package beebong.util;

import java.util.Base64;

import beebong.exception.BBongException;

public class StringUtil {
    // Idea taken from chatGPT on how to safely design my serialization of strings
    public static String encode(String str) throws BBongException {
        if (str == null) {
            throw new BBongException("String to Encode is NULL!");
        }
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String decode(String str) throws BBongException {
        if (str == null) {
            throw new BBongException("String to Decode is NULL!");
        }
        return new String(Base64.getDecoder().decode(str));
    }
}
