import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(String s) {
        this.list.add(s);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString().trim();
    }
}
