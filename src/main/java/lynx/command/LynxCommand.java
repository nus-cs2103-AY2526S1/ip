package lynx.command;

import lynx.exception.LynxException;
import lynx.task.Task;

import java.util.List;
import java.util.regex.PatternSyntaxException;

public class LynxCommand {

    private final String[] commands;
    private int index = 0;
    private String date = "";
    private String keyword = "";
    private String id = "";
    private String status = "";
    private String type = "";
    private List<Task> searchResult;

    public void setDate(String date) throws LynxException {
        if (!this.date.isEmpty()) {
            throw new LynxException("Only one date can be supplied per search.");
        }
        this.date = String.format(" occurring on %s", date);
    }

    public void setKeyword(String keyword) {
        if (this.keyword.isEmpty()) {
            this.keyword = String.format(" containing keyword \"%s\"", keyword);
        } else {
            this.keyword = String.format("%s, \"%s\"", this.keyword, keyword);
        }
    }

    public void setId(String id) throws LynxException {
        if (!this.id.isEmpty()) {
            throw new LynxException("Only one id can be supplied per search.");
        }
        this.id = String.format(" with id %s", id);
    }

    public void setStatus(String status) {
        if (this.status.isEmpty()) {
            this.status = String.format(" %s", status);
        } else {
            this.status = String.format("%s, %s", this.status, status);
        }
    }

    public void setType(String type) {
        if (this.type.isEmpty()) {
            this.type = String.format(" %s", type);
        } else {
            this.type = String.format("%s, %s", this.type, type);
        }
    }

    public void setSearchResult(List<Task> searchResult) {
        this.searchResult = searchResult;
    }

    public List<Task> getSearchResult() {
        return searchResult;
    }

    public LynxCommand(String input) throws LynxException {
        if (input.isBlank()) {
            throw new LynxException("Command cannot be created from blank string.");
        } else {
            try {
                commands = input.trim().split("\\s+");
            } catch (PatternSyntaxException e) {
                throw new LynxException("Command must be of appropriate format.");
            }
        }
    }

    public String getNextCommand() {
        if (index >= commands.length) {
            return "";
        } else {
            String command = commands[index];
            index++;
            return command;
        }
    }

    public String getSearchString() {
        String searchString = String.format("all%s%s tasks%s%s%s:", status, type, date, keyword, id);
        if (searchString.length() > 100) {
            return "all matching tasks:";
        } else {
            return searchString;
        }
    }

}
