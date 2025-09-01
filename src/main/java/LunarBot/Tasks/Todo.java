package LunarBot.Tasks;

public class Todo extends Task {
    // Todos
    public Todo(String name, Boolean completed) {
        super(name, completed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[T] [" + (this.completed ? "X" : " ") + "] " + this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "T," + this.completed.toString() + "," + this.name;
    }
}
