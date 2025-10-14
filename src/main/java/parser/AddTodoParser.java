package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTodoParser extends Parser {
    private String todoName;


    /**
     * Parses the user's input
     *
     * @param userInput the user's input
     */
    @Override
    public void parse(String userInput) throws Exception {
        String regex = "todo (.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.find()) {
            throw new Exception("Could not find the name of the todo to add.");
        }

        this.todoName = matcher.group(1);
    }

    public String getTodoName() {
        return this.todoName;
    }
}
