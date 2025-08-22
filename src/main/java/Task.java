class Task {
    String name;
    Boolean completed;

    // Todos
    Task(String name, Boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public void setCompleted(Boolean bool) {
        this.completed = bool;
    }

    public String print() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.name;
    }
}