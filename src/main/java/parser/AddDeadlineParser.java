package parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import datetime.DateTime;

public class AddDeadlineParser extends Parser {
    private String todoName;
    private LocalDateTime dueDate;

    /**
     * Parses the user's input
     *
     * @param userInput the user's input
     */
    @Override
    public void parse(String userInput) throws Exception {
        String regex = "deadline (.*) /by (.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.find()) {
            throw new Exception("Could not find the name and/or deadline of the todo to add");
        }

        this.todoName = matcher.group(1);
        this.dueDate = DateTime.parseStringToDate(matcher.group(2));
    }

    public String getTodoName() {
        return this.todoName;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
}
