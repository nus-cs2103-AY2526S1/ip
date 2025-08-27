package beebong.util;

import java.util.Base64;

public class StringUtil {
    // Idea taken from chatGPT on how to safely design my serialization of strings
    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }
}
