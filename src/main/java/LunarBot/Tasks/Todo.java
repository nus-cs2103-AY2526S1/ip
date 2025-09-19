package LunarBot.Tasks;

public class Todo extends Task {
    // Todos
    public Todo(String name, Boolean isCompleted) {
        super(name, isCompleted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String print() {
        return "[T] [" + (this.isCompleted ? "X" : " ") + "] " + this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsCsv() {
        return "T," + this.isCompleted.toString() + "," + this.name;
    }
}
