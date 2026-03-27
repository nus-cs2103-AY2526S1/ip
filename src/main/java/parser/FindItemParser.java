package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindItemParser extends Parser {
    private String itemName;


    /**
     * Parses the user's input
     *
     * @param userInput the user's input
     */
    @Override
    public void parse(String userInput) throws Exception {
        String regex = "find (.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.find()) {
            throw new Exception("could not find the item number to delete");
        }

        this.itemName = matcher.group(1);
    }

    public String getItemName() {
        return this.itemName;
    }
}
