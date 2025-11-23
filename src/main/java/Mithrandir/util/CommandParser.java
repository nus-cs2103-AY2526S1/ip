package Mithrandir.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CommandParser {
    public CommandParser() {
    }

    public static HashMap<String, String> parse(String input) {
        HashMap<String, String> result = new HashMap<>();
        String[] tokens = input.split(" ");
        result.put("command word", tokens[0].toUpperCase());
        result.put("description", Arrays.stream(tokens, 1, tokens.length).collect(Collectors.joining(" ")));
        return result;
    }
}
