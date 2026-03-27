package parser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import datetime.DateTime;

public class AddEventParser extends Parser {
    private String todoName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Parses the user's input
     *
     * @param userInput the user's input
     */
    @Override
    public void parse(String userInput) throws Exception {
        String regex = "event (.*) /from (.*) /to (.*)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.find()) {
            throw new Exception("could not find the name, start date, and/or end date of the event to add");
        }

        this.todoName = matcher.group(1);
        this.startDate = DateTime.parseStringToDate(matcher.group(2));
        this.endDate = DateTime.parseStringToDate(matcher.group(3));
    }

    public String getTodoName() {
        return this.todoName;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }
}
