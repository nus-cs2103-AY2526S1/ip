package command;
import task.Task;
import java.util.List;

public interface Command {
    public String execute(List<Task> list);
}
