package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteItemParser extends Parser {
    private Integer itemNumber;

    /**
     * Parses the user's input
     *
     * @param userInput the user's input
     */
    @Override
    public void parse(String userInput) throws Exception {
        String regex = "delete ([0-9]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.find()) {
            throw new Exception("could not find the item number to delete");
        }

        this.itemNumber = Integer.parseInt(matcher.group(1));
    }

    public Integer getItemNumber() {
        return this.itemNumber;
    }
}
