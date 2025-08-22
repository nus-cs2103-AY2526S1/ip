public class Todo extends Task {
    // Todos
    Todo(String name, Boolean completed) {
        super(name, completed);
    }

    public String print() {
        return "[T] [" + (this.completed ? "X" : " ") + "] " + this.name;
    }
}
