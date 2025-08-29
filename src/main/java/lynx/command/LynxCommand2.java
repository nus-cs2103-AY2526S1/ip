package lynx.command;

import lynx.exception.LynxException;
import lynx.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public abstract class LynxCommand2 {

    private final String[] commands;
    private int index = -1;
    private String date = "";
    private String keyword = "";
    private String id = "";
    private String status = "";
    private String type = "";
    private List<Task> searchResult;

    public void setDate(String date) {
        this.date = String.format(" occurring on %s", date);
    }

    public void setKeyword(String keyword) {
        this.keyword = String.format(" containing keyword \"%s\"", keyword);
    }

    public void setId(String id) {
        this.id = String.format(" with id: %s", id);
    }

    public void setStatus(String status) {
        this.status = String.format(" %s", status);
    }

    public void setType(String type) {
        this.type = String.format(" %s", type);
    }

    public void setSearchResult(List<Task> searchResult) {
        this.searchResult = searchResult;
    }

    public List<Task> getSearchResult() {
        return searchResult;
    }

    public LynxCommand2(String input) throws LynxException {
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
            index++;
            return commands[index];
        }
    }

    public String getSearchString() {
        return String.format("all%s%s tasks%s%s%s:", status, type, date, keyword, id);
    }

}
