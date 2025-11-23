package Mithrandir.util;

import java.util.HashMap;

public class FileParser {
    public static HashMap<String, String> parseFileContent(String fileContent) {
        String[] parsedContent = fileContent.split("\\|\\|", 3);
        HashMap<String, String> result = new HashMap<>();
        result.put("Type", parsedContent[0].strip());
        result.put("CompletionStatus", parsedContent[1].strip());
        result.put("Description", parsedContent[2].strip());
        return result;
    }
}
